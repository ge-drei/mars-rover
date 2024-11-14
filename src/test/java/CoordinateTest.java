import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    @DisplayName("Coordinates can correctly store and retrieve integers")
    void test_getX_getY() {
        var zeroZero = new Coordinate(0, 0);
        var zeroMinusOne = new Coordinate(0, -1);
        var minusOneZero = new Coordinate(-1, 0);
        var zeroOne = new Coordinate(0, 1);
        var oneZero = new Coordinate(1, 0);
        var oneOne = new Coordinate(1, 1);

        assertAll(() -> assertEquals(0, zeroZero.getX()),
                () -> assertEquals(0, zeroZero.getY()),

                () -> assertEquals(0, zeroMinusOne.getX()),
                () -> assertEquals(-1, zeroMinusOne.getY()),

                () -> assertEquals(-1, minusOneZero.getX()),
                () -> assertEquals(0, minusOneZero.getY()),

                () -> assertEquals(0, zeroOne.getX()),
                () -> assertEquals(1, zeroOne.getY()),

                () -> assertEquals(1, oneZero.getX()),
                () -> assertEquals(0, oneZero.getY()),

                () -> assertEquals(1, oneOne.getX()),
                () -> assertEquals(1, oneOne.getY()));
    }
}