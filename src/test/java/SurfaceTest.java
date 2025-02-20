import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spatial.Coordinate;
import surface.*;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceTest {

    @Test
    @DisplayName("isValidCoordinate returns false if provided with invalid coordinates")
    void testIsValidCoordinate_invalidCoordinates() {
        Surface surface = new BasicSurface(new Coordinate(5, 5));

        assertAll(() -> assertFalse(surface.isValidCoordinate(new Coordinate(-1, -1))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(-1, 0))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(-1, 3))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(-1, 5))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(-1, 6))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(0, -1))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(0, 6))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(3, -1))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(3, 6))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(5, -1))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(5, 6))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(6, -1))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(6, 0))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(6, 3))),
                () -> assertFalse(surface.isValidCoordinate(new Coordinate(6, 6))));
    }

    @Test
    @DisplayName("isValidCoordinate returns true if provided with valid coordinates")
    void testIsValidCoordinate_validCoordinates() {
        Surface surface = new BasicSurface(new Coordinate(5, 5));

        assertAll(() -> assertTrue(surface.isValidCoordinate(new Coordinate(0, 0))),
                () -> assertTrue(surface.isValidCoordinate(new Coordinate(3, 0))),
                () -> assertTrue(surface.isValidCoordinate(new Coordinate(5, 0))),
                () -> assertTrue(surface.isValidCoordinate(new Coordinate(0, 3))),
                () -> assertTrue(surface.isValidCoordinate(new Coordinate(3, 3))),
                () -> assertTrue(surface.isValidCoordinate(new Coordinate(5, 3))),
                () -> assertTrue(surface.isValidCoordinate(new Coordinate(0, 5))),
                () -> assertTrue(surface.isValidCoordinate(new Coordinate(3, 5))),
                () -> assertTrue(surface.isValidCoordinate(new Coordinate(5, 5))));
    }

    @Test
    @DisplayName("getMaxCoordinates returns coordinate at top-right of valid grid")
    void testGetMaxCoordinates_getsMaxCoordinates() {
        Surface surface_fiveFive = new BasicSurface(new Coordinate(5, 5));
        Surface surface_zeroZero = new BasicSurface(new Coordinate(0, 0));

        assertAll(() -> assertEquals(new Coordinate(5, 5), surface_fiveFive.getMaxCoordinates()),
                () -> assertEquals(new Coordinate(0, 0), surface_zeroZero.getMaxCoordinates()));
    }
}