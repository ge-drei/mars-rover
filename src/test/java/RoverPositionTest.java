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
        RoverPosition zeroZero = new RoverPosition(new Coordinate(0, 0), Cardinal.N);
        RoverPosition fiveFive = new RoverPosition(new Coordinate(5, 5), Cardinal.N);

        assertAll(() -> assertEquals(new Coordinate(0, 0), zeroZero.getCoordinates()),
                () -> assertEquals(new Coordinate(5, 5), fiveFive.getCoordinates())
        );
    }

    @Test
    @DisplayName("getDirection correctly retrieves a direction")
    void getDirection() {
        RoverPosition zeroZeroNorth = new RoverPosition(new Coordinate(0, 0), Cardinal.N);
        RoverPosition zeroZeroEast = new RoverPosition(new Coordinate(0, 0), Cardinal.E);
        RoverPosition zeroZeroSouth = new RoverPosition(new Coordinate(0, 0), Cardinal.S);
        RoverPosition zeroZeroWest = new RoverPosition(new Coordinate(0, 0), Cardinal.W);

        assertAll(() -> assertEquals(Cardinal.N, zeroZeroNorth.getDirection()),
                () -> assertEquals(Cardinal.E, zeroZeroEast.getDirection()),
                () -> assertEquals(Cardinal.S, zeroZeroSouth.getDirection()),
                () -> assertEquals(Cardinal.W, zeroZeroWest.getDirection())
        );
    }
}