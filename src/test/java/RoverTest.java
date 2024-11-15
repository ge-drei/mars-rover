import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import rover.BasicRover;
import spatial.*;
import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    @DisplayName("Rover can be correctly initialised with a position and direction from coordinate constructor")
    void testGetPosition_GetDirection_coordinateConstructor() {
        var rover_zeroZero_north = new BasicRover(new Coordinate(0, 0), Cardinal.N);
        var rover_minusOneMinusOne_south = new BasicRover(new Coordinate(-1, -1), Cardinal.S);
        var coord_zeroZero = new Coordinate(0, 0);
        var coord_minusOneMinusOne = new Coordinate(-1, -1);


        assertAll(() -> assertEquals(coord_zeroZero, rover_zeroZero_north.getPosition()),
                () -> assertEquals(Cardinal.N, rover_zeroZero_north.getDirection()),

                () -> assertEquals(coord_minusOneMinusOne, rover_minusOneMinusOne_south.getPosition()),
                () -> assertEquals(Cardinal.S, rover_minusOneMinusOne_south.getDirection())
        );
    }

    @Test
    @DisplayName("Rover can be correctly initialised with a position and direction from roverPosition constructor")
    void testGetPosition_GetDirection_roverPositionConstructor() {
        var position_zeroZero_north = new RoverPosition(new Coordinate(0, 0), Cardinal.N);
        var position_minusOneMinusOne_south = new RoverPosition(new Coordinate(-1, -1), Cardinal.S);
        var rover_zeroZero_north = new BasicRover(position_zeroZero_north);
        var rover_minusOneMinusOne_south = new BasicRover(position_minusOneMinusOne_south);
        var coord_zeroZero = new Coordinate(0, 0);
        var coord_minusOneMinusOne = new Coordinate(-1, -1);


        assertAll(() -> assertEquals(coord_zeroZero, rover_zeroZero_north.getPosition()),
                () -> assertEquals(Cardinal.N, rover_zeroZero_north.getDirection()),

                () -> assertEquals(coord_minusOneMinusOne, rover_minusOneMinusOne_south.getPosition()),
                () -> assertEquals(Cardinal.S, rover_minusOneMinusOne_south.getDirection())
        );
    }

    @Test
    @DisplayName("Rover can calculate new direction after turning both left and right")
    void testGetTurnDirection() {
        var zeroZero = new Coordinate(0, 0);
        var roverNorth = new BasicRover(zeroZero, Cardinal.N);
        var roverEast = new BasicRover(zeroZero, Cardinal.E);
        var roverSouth = new BasicRover(zeroZero, Cardinal.S);
        var roverWest = new BasicRover(zeroZero, Cardinal.W);

        assertAll(() -> assertEquals(Cardinal.W, roverNorth.getTurnDirection(Relative.LEFT)),
                () -> assertEquals(Cardinal.E, roverNorth.getTurnDirection(Relative.RIGHT)),

                () -> assertEquals(Cardinal.N, roverEast.getTurnDirection(Relative.LEFT)),
                () -> assertEquals(Cardinal.S, roverEast.getTurnDirection(Relative.RIGHT)),

                () -> assertEquals(Cardinal.E, roverSouth.getTurnDirection(Relative.LEFT)),
                () -> assertEquals(Cardinal.W, roverSouth.getTurnDirection(Relative.RIGHT)),

                () -> assertEquals(Cardinal.S, roverWest.getTurnDirection(Relative.LEFT)),
                () -> assertEquals(Cardinal.N, roverWest.getTurnDirection(Relative.RIGHT))
        );
    }

    @Test
    @DisplayName("Rover can calculate new position after taking forwards step in any direction")
    void testCalculateNewPosition() {
        var zeroZero = new Coordinate(0, 0);
        var roverNorth = new BasicRover(zeroZero, Cardinal.N);
        var roverEast = new BasicRover(zeroZero, Cardinal.E);
        var roverSouth = new BasicRover(zeroZero, Cardinal.S);
        var roverWest = new BasicRover(zeroZero, Cardinal.W);

        var northStep = roverNorth.calculateNewPosition();
        var eastStep = roverEast.calculateNewPosition();
        var southStep = roverSouth.calculateNewPosition();
        var westStep = roverWest.calculateNewPosition();

        assertAll(() -> assertEquals(new Coordinate(0, 1), northStep),
                () -> assertEquals(new Coordinate(1, 0), eastStep),
                () -> assertEquals(new Coordinate(0, -1), southStep),
                () -> assertEquals(new Coordinate(-1, 0), westStep)
        );
    }

    @Test
    @DisplayName("Rover can correctly set new direction")
    void testSetDirection(){
        var zeroZero = new Coordinate(0, 0);
        var roverNorth = new BasicRover(zeroZero, Cardinal.N);

        assertAll(() -> {
            roverNorth.setDirection(Cardinal.E);
            assertEquals(Cardinal.E, roverNorth.getDirection());
        },
                () -> {
            roverNorth.setDirection(Cardinal.S);
            assertEquals(Cardinal.S, roverNorth.getDirection());
        },
                () -> {
            roverNorth.setDirection(Cardinal.W);
            assertEquals(Cardinal.W, roverNorth.getDirection()
            );
        }
        );
    }

    @Test
    @DisplayName("Rover can correctly set new position")
    void testSetPosition() {
        var zeroZero = new Coordinate(0, 0);
        var roverZeroZero = new BasicRover(zeroZero, Cardinal.N);

        assertAll(() -> {
            roverZeroZero.setPosition(new Coordinate(1, 1));
            assertEquals(new Coordinate(1, 1), roverZeroZero.getPosition());
        },
                () -> {
            roverZeroZero.setPosition(new Coordinate(0, 0));
            assertEquals(new Coordinate(0, 0), roverZeroZero.getPosition());
        },
                () -> {
            roverZeroZero.setPosition(new Coordinate(5, 5));
            assertEquals(new Coordinate(5, 5), roverZeroZero.getPosition());
        }
        );
    }
}