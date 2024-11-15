import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spatial.Cardinal;
import spatial.Coordinate;
import spatial.RoverPosition;

import static org.junit.jupiter.api.Assertions.*;

class RoverPositionTest {

    @Test
    @DisplayName("getCoordinates correctly retrieves a position")
    void getCoordinates() {
        RoverPosition zeroZero = new RoverPosition(new Coordinate(0, 0), Cardinal.NORTH);
        RoverPosition fiveFive = new RoverPosition(new Coordinate(5, 5), Cardinal.NORTH);

        assertAll(() -> assertEquals(new Coordinate(0, 0), zeroZero.getCoordinates()),
                () -> assertEquals(new Coordinate(5, 5), fiveFive.getCoordinates())
        );
    }

    @Test
    @DisplayName("getDirection correctly retrieves a direction")
    void getDirection() {
        RoverPosition zeroZeroNorth = new RoverPosition(new Coordinate(0, 0), Cardinal.NORTH);
        RoverPosition zeroZeroEast = new RoverPosition(new Coordinate(0, 0), Cardinal.EAST);
        RoverPosition zeroZeroSouth = new RoverPosition(new Coordinate(0, 0), Cardinal.SOUTH);
        RoverPosition zeroZeroWest = new RoverPosition(new Coordinate(0, 0), Cardinal.WEST);

        assertAll(() -> assertEquals(Cardinal.NORTH, zeroZeroNorth.getDirection()),
                () -> assertEquals(Cardinal.EAST, zeroZeroEast.getDirection()),
                () -> assertEquals(Cardinal.SOUTH, zeroZeroSouth.getDirection()),
                () -> assertEquals(Cardinal.WEST, zeroZeroWest.getDirection())
        );
    }
}