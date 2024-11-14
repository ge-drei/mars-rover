import directions.Cardinal;
import directions.Relative;
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
                () -> assertThrows(IllegalArgumentException.class, () -> mission.makeSurface(new Coordinate(0, 0))));
    }

    @Test
    @DisplayName("makeSurface returns a surface if provided valid dimensions")
    void testMakeSurface_validDimensions() {
        var surface1 = mission.makeSurface(new Coordinate(5, 5));

        assertNotNull(surface1);
    }

    @Test
    @DisplayName("makeRover returns a valid rover that contains the parameters passed in")
    void testMakeRover_validParameters() {
        Rover rover = mission.makeRover(new Coordinate(0, 0), Cardinal.NORTH);

        assertAll(() -> assertEquals(new Coordinate(0, 0), rover.getPosition()),
                () -> assertEquals(Cardinal.NORTH, rover.getDirection()));
    }

    @Test
    @DisplayName("makeRover throws an IllegalArgumentException if the position or direction given is null")
    void testMakeRover_nullParameters() {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> mission.makeRover(null, Cardinal.NORTH)),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.makeRover(new Coordinate(0, 0), null)),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.makeRover(null,null)));
    }

    @Test
    @DisplayName("turnRover successfully makes rover turn")
    void testTurnRover() {
        Coordinate zeroZero = new Coordinate(0, 0);
        Rover rover = mission.makeRover(zeroZero, Cardinal.NORTH);
        mission.setRover(rover);

        mission.turnRover(Relative.LEFT);

        assertEquals(Cardinal.WEST, rover.getDirection());
    }

    @Test
    @DisplayName("isRoverStepValid returns false if rover would move to invalid location")
    void testIsRoverStepValid_invalidStep() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));

        Rover roverTopBound = mission.makeRover(new Coordinate(3,5), Cardinal.NORTH);
        Rover roverLeftBound = mission.makeRover(new Coordinate(0, 3), Cardinal.WEST);
        Rover roverBottomBound = mission.makeRover(new Coordinate(3, 0), Cardinal.SOUTH);
        Rover roverRightBound = mission.makeRover(new Coordinate(5, 3), Cardinal.EAST);

        assertAll(() -> {mission.setRover(roverTopBound);
                    assertFalse(mission.isRoverStepValid());},
                () -> {mission.setRover(roverLeftBound);
                    assertFalse(mission.isRoverStepValid());},
                () -> {mission.setRover(roverBottomBound);
                    assertFalse(mission.isRoverStepValid());},
                () -> {mission.setRover(roverRightBound);
                    assertFalse(mission.isRoverStepValid());});
    }

    @Test
    @DisplayName("isRoverStepValid returns true if rover would move to valid location")
    void testIsRoverStepValid_validStep() {
        mission.setSurface(mission.makeSurface(new Coordinate(5, 5)));

        Rover rover1 = mission.makeRover(new Coordinate(1,1), Cardinal.NORTH);
        Rover rover2 = mission.makeRover(new Coordinate(0, 0), Cardinal.NORTH);
        Rover rover3 = mission.makeRover(new Coordinate(5, 5), Cardinal.SOUTH);
        Rover rover4 = mission.makeRover(new Coordinate(5, 3), Cardinal.WEST);
        Rover rover5 = mission.makeRover(new Coordinate(4,4), Cardinal.EAST);

        assertAll(() -> {mission.setRover(rover1);
                    assertTrue(mission.isRoverStepValid());},
                () -> {mission.setRover(rover2);
                    assertTrue(mission.isRoverStepValid());},
                () -> {mission.setRover(rover3);
                    assertTrue(mission.isRoverStepValid());},
                () -> {mission.setRover(rover4);
                    assertTrue(mission.isRoverStepValid());},
                () -> {mission.setRover(rover5);
                    assertTrue(mission.isRoverStepValid());});
    }

    @Test
    @DisplayName("interpretCommand throws IllegalArgumentException if invalid character is passed")
    void testInterpretCommand_invalidChar() {
        assertAll( () -> assertThrows(IllegalArgumentException.class, () -> mission.interpretCommand('l')),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.interpretCommand('r')),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.interpretCommand('m')),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.interpretCommand('A')),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.interpretCommand('1')),
                () -> assertThrows(IllegalArgumentException.class, () -> mission.interpretCommand(' ')));
    }

    @Test
    @DisplayName("interpretCommand returns correct Command if valid character is passed")
    void testInterpretCommand_validChar() {
        assertAll(() -> assertEquals(Command.LEFT, mission.interpretCommand('L')),
                () -> assertEquals(Command.RIGHT, mission.interpretCommand('R')),
                () -> assertEquals(Command.MOVE, mission.interpretCommand('M')));
    }
}