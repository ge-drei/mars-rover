import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    @Test
    @DisplayName("Rover can be correctly initialised with a position and direction")
    void testGetPosition_GetDirection() {
        var rover_zeroZero_north = new Rover(new Coordinate(0, 0), Direction.NORTH);
        var rover_minusOneMinusOne_south = new Rover(new Coordinate(-1, -1), Direction.SOUTH);
        var coord_zeroZero = new Coordinate(0, 0);
        var coord_minusOneMinusOne = new Coordinate(-1, -1);


        assertAll(() -> assertEquals(coord_zeroZero, rover_zeroZero_north.getPosition()),
                () -> assertEquals(Direction.NORTH, rover_zeroZero_north.getDirection()),

                () -> assertEquals(coord_minusOneMinusOne, rover_minusOneMinusOne_south.getPosition()),
                () -> assertEquals(Direction.SOUTH, rover_minusOneMinusOne_south.getDirection()));
    }

}