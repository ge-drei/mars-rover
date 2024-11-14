import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    @DisplayName("Return true if .equals method is called on itself")
    void testEquals_sameCoordinate() {
        var zeroZero = new Coordinate(0, 0);
        var minusOneZero = new Coordinate(-1, 0);

        assertAll(() -> assertEquals(zeroZero, zeroZero),
                () -> assertTrue(zeroZero.equals(zeroZero)),
                () -> assertTrue(zeroZero == zeroZero),

                () -> assertEquals(minusOneZero, minusOneZero),
                () -> assertTrue(minusOneZero.equals(minusOneZero)),
                () -> assertTrue(minusOneZero == minusOneZero));
    }

    @Test
    @DisplayName("Return true if .equals method is called on different Coordinate with same values")
    void testEquals_doesEqual() {
        var zeroZero_1 = new Coordinate(0, 0);
        var zeroZero_2 = new Coordinate(0, 0);
        var minusOneZero_1 = new Coordinate(-1, 0);
        var minusOneZero_2 = new Coordinate(-1, 0);

        assertAll(() -> assertEquals(zeroZero_1, zeroZero_2),
                () -> assertEquals(zeroZero_2, zeroZero_1),
                () -> assertTrue(zeroZero_1.equals(zeroZero_2)),
                () -> assertTrue(zeroZero_2.equals(zeroZero_1)),

                () -> assertEquals(minusOneZero_1, minusOneZero_2),
                () -> assertEquals(minusOneZero_2, minusOneZero_1),
                () -> assertTrue(minusOneZero_1.equals(minusOneZero_2)),
                () -> assertTrue(minusOneZero_2.equals(minusOneZero_1))
        );
    }

    @Test
    @DisplayName("Return false if .equals method is called on Coordinate with different values")
    void testEquals_doesNotEqual() {
        var zeroZero = new Coordinate(0, 0);
        var minusOneZero = new Coordinate(-1, 0);

        assertAll(() -> assertNotEquals(zeroZero, minusOneZero),
                () -> assertNotEquals(minusOneZero, zeroZero),
                () -> assertFalse(zeroZero.equals(minusOneZero)),
                () -> assertFalse(minusOneZero.equals(zeroZero))
        );
    }

    @Test
    @DisplayName("Return false if .equals method is called on non-Coordinate object")
    void testEquals_notCoordinate() {
        var zeroZero = new Coordinate(0, 0);
        var object = new Object();
        var list = new ArrayList<Coordinate>();
        var objectNull = (Object) null;

        assertAll(() -> assertFalse(zeroZero.equals(object)),
                () -> assertFalse(zeroZero.equals(list)),
                () -> assertFalse(zeroZero.equals(objectNull)));
    }
}