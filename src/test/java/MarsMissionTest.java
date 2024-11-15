import spatial.Cardinal;
import spatial.Coordinate;
import spatial.Relative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarsMissionTest {

    static MarsMission mission;

    @BeforeEach
    void missionSetup() {
        mission = new MarsMission();
    }

    @Test
    @DisplayName("makeSurface throws an IllegalArgumentException if provided with any dimensions <= 0")
    void testMakeSurface_invalidDimensions() {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> mission.makeSurface(new Coordinate(0, 5))),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.makeSurface(new Coordinate(5, 0))),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.makeSurface(new Coordinate(0, 0)))
        );
    }

    @Test
    @DisplayName("makeSurface returns a surface with correct dimensions if provided valid coordinate")
    void testMakeSurface_validDimensions() {
        var surface1 = mission.makeSurface(new Coordinate(5, 5));

        assertAll(() -> assertNotNull(surface1),
                () -> assertEquals(new Coordinate(5, 5), surface1.getMaxCoordinates())
        );
    }

    @Test
    @DisplayName("makeRover returns a valid rover that contains the parameters passed in")
    void testMakeRover_validParameters() {
        Rover rover = mission.makeRover(new Coordinate(0, 0), Cardinal.N);

        assertAll(() -> assertEquals(new Coordinate(0, 0), rover.getPosition()),
                () -> assertEquals(Cardinal.N, rover.getDirection())
        );
    }

    @Test
    @DisplayName("makeRover throws an IllegalArgumentException if the position or direction given is null")
    void testMakeRover_nullParameters() {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> mission.makeRover(null, Cardinal.N)),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.makeRover(new Coordinate(0, 0), null)),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.makeRover(null,null))
        );
    }

    @Test
    @DisplayName("setSurface throws an IllegalArgumentException if the surface given is null")
    void testSetSurface_nullSurface() {
        Surface surface = null;

        assertThrows(IllegalArgumentException.class, () -> mission.setSurface(surface));
    }

    @Test
    @DisplayName("setSurface correctly sets new surface")
    void testSetSurface_validSurface() {
        Rover rover = new Rover(new Coordinate(5, 5), Cardinal.N);
        Surface surface1 = new Surface(new Coordinate(6, 6));
        Surface surface2 = new Surface(new Coordinate(5, 5));
        mission.setRover(rover);

        mission.setSurface(surface1);
        boolean surface1_sixSix_valid = mission.isRoverStepValid();

        mission.setSurface(surface2);
        boolean surface2_sixSix_valid = mission.isRoverStepValid();

        assertAll(() -> assertTrue(surface1_sixSix_valid),
                () -> assertFalse(surface2_sixSix_valid)
        );
    }

    @Test
    @DisplayName("setRover throws an IllegalArgumentException if the rover given is null")
    void testSetRover_nullRover() {
        Rover rover = null;

        assertThrows(IllegalArgumentException.class, () -> mission.setRover(rover));
    }

    @Test
    @DisplayName("setRover correctly sets new rover")
    void testSetRover_validRover() {
        Surface surface = new Surface(new Coordinate(5, 5));
        Rover rover1 = new Rover(new Coordinate(4, 4), Cardinal.N);
        Rover rover2 = new Rover(new Coordinate(5, 5), Cardinal.N);
        mission.setSurface(surface);

        mission.setRover(rover1);
        boolean rover1_stepValid = mission.isRoverStepValid();

        mission.setRover(rover2);
        boolean rover2_stepValid = mission.isRoverStepValid();

        assertAll(() -> assertTrue(rover1_stepValid),
                () -> assertFalse(rover2_stepValid)
        );
    }

    @Test
    @DisplayName("turnRover successfully makes rover turn")
    void testTurnRover() {
        Coordinate zeroZero = new Coordinate(0, 0);
        Rover rover = mission.makeRover(zeroZero, Cardinal.N);
        mission.setRover(rover);

        mission.turnRover(Relative.LEFT);

        assertEquals(Cardinal.W, rover.getDirection());
    }

    @Test
    @DisplayName("turnRover throws IllegalStateException if called when rover is uninitialised")
    void testTurnRover_noRover() {
        assertAll(() -> assertThrows(IllegalStateException.class, () -> mission.turnRover(Relative.LEFT)),
                () -> assertThrows(IllegalStateException.class, () -> mission.turnRover(Relative.RIGHT))
        );
    }

    @Test
    @DisplayName("isRoverStepValid returns false if rover would move to invalid location")
    void testIsRoverStepValid_invalidStep() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));

        Rover roverTopBound = mission.makeRover(new Coordinate(3,5), Cardinal.N);
        Rover roverLeftBound = mission.makeRover(new Coordinate(0, 3), Cardinal.W);
        Rover roverBottomBound = mission.makeRover(new Coordinate(3, 0), Cardinal.S);
        Rover roverRightBound = mission.makeRover(new Coordinate(5, 3), Cardinal.E);

        assertAll(() -> {mission.setRover(roverTopBound);
                    assertFalse(mission.isRoverStepValid());},
                () -> {mission.setRover(roverLeftBound);
                    assertFalse(mission.isRoverStepValid());},
                () -> {mission.setRover(roverBottomBound);
                    assertFalse(mission.isRoverStepValid());},
                () -> {mission.setRover(roverRightBound);
                    assertFalse(mission.isRoverStepValid());}
        );
    }

    @Test
    @DisplayName("isRoverStepValid returns true if rover would move to valid location")
    void testIsRoverStepValid_validStep() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));

        Rover rover1 = mission.makeRover(new Coordinate(1,1), Cardinal.N);
        Rover rover2 = mission.makeRover(new Coordinate(0, 0), Cardinal.N);
        Rover rover3 = mission.makeRover(new Coordinate(5, 5), Cardinal.S);
        Rover rover4 = mission.makeRover(new Coordinate(5, 3), Cardinal.W);
        Rover rover5 = mission.makeRover(new Coordinate(4,4), Cardinal.E);

        assertAll(() -> {mission.setRover(rover1);
                    assertTrue(mission.isRoverStepValid());},
                () -> {mission.setRover(rover2);
                    assertTrue(mission.isRoverStepValid());},
                () -> {mission.setRover(rover3);
                    assertTrue(mission.isRoverStepValid());},
                () -> {mission.setRover(rover4);
                    assertTrue(mission.isRoverStepValid());},
                () -> {mission.setRover(rover5);
                    assertTrue(mission.isRoverStepValid());}
        );
    }

    @Test
    @DisplayName("isRoverStepValid throws IllegalStateException if called when rover and/or surface is uninitialised")
    void testIsRoverStepValid_illegalStates() {
        Surface surface = new Surface(new Coordinate(0, 0));
        MarsMission surfaceNoRover = new MarsMission();
        surfaceNoRover.setSurface(surface);

        Rover rover = new Rover(new Coordinate(0, 0), Cardinal.N);
        MarsMission roverNoSurface = new MarsMission();
        roverNoSurface.setRover(rover);

        assertAll(() -> assertThrows(IllegalStateException.class, () -> mission.isRoverStepValid()),
                () -> assertThrows(IllegalStateException.class, () -> surfaceNoRover.isRoverStepValid()),
                () -> assertThrows(IllegalStateException.class, () -> roverNoSurface.isRoverStepValid())
        );
    }

    @Test
    @DisplayName("executeRoverStep throws IllegalStateException if called when rover and/or surface is uninitialised")
    void testExecuteRoverStep_illegalStates() {
        Surface surface = new Surface(new Coordinate(0, 0));
        MarsMission surfaceNoRover = new MarsMission();
        surfaceNoRover.setSurface(surface);

        Rover rover = new Rover(new Coordinate(0, 0), Cardinal.N);
        MarsMission roverNoSurface = new MarsMission();
        roverNoSurface.setRover(rover);

        assertAll(() -> assertThrows(IllegalStateException.class, () -> mission.executeRoverStep(Command.LEFT)),
                () -> assertThrows(IllegalStateException.class, () -> surfaceNoRover.executeRoverStep(Command.RIGHT)),
                () -> assertThrows(IllegalStateException.class, () -> roverNoSurface.executeRoverStep(Command.MOVE))
        );

    }

    @Test
    @DisplayName("executeRoverStep returns true when given valid commands")
    void testExecuteRoverStep_validCommand() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));
        mission.setRover(mission.makeRover(new Coordinate(0, 0), Cardinal.N));

        assertAll(() -> assertTrue(mission.executeRoverStep(Command.LEFT)),
                () -> assertTrue(mission.executeRoverStep(Command.RIGHT)),
                () -> assertTrue(mission.executeRoverStep(Command.MOVE))
        );
    }

    @Test
    @DisplayName("executeRoverStep returns false when given command that would take rover out of bounds")
    void testExecuteRoverStep_invalidCommand() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));
        mission.setRover(mission.makeRover(new Coordinate(5, 5), Cardinal.N));

        assertFalse(mission.executeRoverStep(Command.MOVE));
    }

    @Test
    @DisplayName("executeRoverStep throws IllegalArgumentException if command is null")
    void testExecuteRoverStep_nullCommand() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));
        mission.setRover(mission.makeRover(new Coordinate(5, 5), Cardinal.N));

        assertThrows(IllegalArgumentException.class, () -> mission.executeRoverStep(null));
    }

    @Test
    @DisplayName("executeCommandSequence throws IllegalArgumentException if command sequence is null")
    void testExecuteCommandSequence_nullSequence() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));
        mission.setRover(mission.makeRover(new Coordinate(5, 5), Cardinal.N));

        assertThrows(IllegalArgumentException.class, () -> mission.executeCommandSequence(null));
    }

    @Test
    @DisplayName("executeCommandSequence returns success and does not move rover if sequence is empty")
    void testExecuteCommandSequence_emptySequence() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));
        mission.setRover(mission.makeRover(new Coordinate(5, 5), Cardinal.N));

        Command[] sequence = new Command[0];
        SequenceResult result = mission.executeCommandSequence(sequence);

        assertAll(() -> assertTrue(result.succeeded()),
                () -> assertEquals("5 5 N", result.message())
        );
    }

    @Test
    @DisplayName("executeCommandSequence returns false and more detailed message if rover goes out of bounds")
    void testExecuteCommandSequence_failedSequence() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));
        mission.setRover(mission.makeRover(new Coordinate(5, 5), Cardinal.N));

        Command[] sequence = {Command.MOVE};
        SequenceResult result = mission.executeCommandSequence(sequence);

        assertAll(() -> assertFalse(result.succeeded()),
                () -> assertEquals("Error in step 1: Rover went out of bounds from (5,5)", result.message())
        );
    }

    @Test
    @DisplayName("executeCommandSequence returns true and correct coordinates if valid sequence executed")
    void testExecuteCommandSequence_validSequence() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));
        mission.setRover(mission.makeRover(new Coordinate(5, 5), Cardinal.N));

        Command[] sequence = {Command.LEFT, Command.LEFT, Command.MOVE, Command.RIGHT, Command.MOVE};
        SequenceResult result = mission.executeCommandSequence(sequence);

        assertAll(() -> assertTrue(result.succeeded()),
                () -> assertEquals("4 4 W", result.message())
        );
    }
}