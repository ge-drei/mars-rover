import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import spatial.*;
import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    @DisplayName("Rover can be correctly initialised with a position and direction")
    void testGetPosition_GetDirection() {
        var rover_zeroZero_north = new Rover(new Coordinate(0, 0), Cardinal.NORTH);
        var rover_minusOneMinusOne_south = new Rover(new Coordinate(-1, -1), Cardinal.SOUTH);
        var coord_zeroZero = new Coordinate(0, 0);
        var coord_minusOneMinusOne = new Coordinate(-1, -1);


        assertAll(() -> assertEquals(coord_zeroZero, rover_zeroZero_north.getPosition()),
                () -> assertEquals(Cardinal.NORTH, rover_zeroZero_north.getDirection()),

                () -> assertEquals(coord_minusOneMinusOne, rover_minusOneMinusOne_south.getPosition()),
                () -> assertEquals(Cardinal.SOUTH, rover_minusOneMinusOne_south.getDirection()));
    }

    @Test
    @DisplayName("Rover can calculate new direction after turning both left and right")
    void testGetTurnDirection() {
        var zeroZero = new Coordinate(0, 0);
        var roverNorth = new Rover(zeroZero, Cardinal.NORTH);
        var roverEast = new Rover(zeroZero, Cardinal.EAST);
        var roverSouth = new Rover(zeroZero, Cardinal.SOUTH);
        var roverWest = new Rover(zeroZero, Cardinal.WEST);

        assertAll(() -> assertEquals(Cardinal.WEST, roverNorth.getTurnDirection(Relative.LEFT)),
                () -> assertEquals(Cardinal.EAST, roverNorth.getTurnDirection(Relative.RIGHT)),

                () -> assertEquals(Cardinal.NORTH, roverEast.getTurnDirection(Relative.LEFT)),
                () -> assertEquals(Cardinal.SOUTH, roverEast.getTurnDirection(Relative.RIGHT)),

                () -> assertEquals(Cardinal.EAST, roverSouth.getTurnDirection(Relative.LEFT)),
                () -> assertEquals(Cardinal.WEST, roverSouth.getTurnDirection(Relative.RIGHT)),

                () -> assertEquals(Cardinal.SOUTH, roverWest.getTurnDirection(Relative.LEFT)),
                () -> assertEquals(Cardinal.NORTH, roverWest.getTurnDirection(Relative.RIGHT)));
    }

    @Test
    @DisplayName("Rover can calculate new position after taking forwards step in any direction")
    void testCalculateNewPosition() {
        var zeroZero = new Coordinate(0, 0);
        var roverNorth = new Rover(zeroZero, Cardinal.NORTH);
        var roverEast = new Rover(zeroZero, Cardinal.EAST);
        var roverSouth = new Rover(zeroZero, Cardinal.SOUTH);
        var roverWest = new Rover(zeroZero, Cardinal.WEST);

        var northStep = roverNorth.calculateNewPosition();
        var eastStep = roverEast.calculateNewPosition();
        var southStep = roverSouth.calculateNewPosition();
        var westStep = roverWest.calculateNewPosition();

        assertAll(() -> assertEquals(new Coordinate(0, 1), northStep),
                () -> assertEquals(new Coordinate(1, 0), eastStep),
                () -> assertEquals(new Coordinate(0, -1), southStep),
                () -> assertEquals(new Coordinate(-1, 0), westStep));
    }

}