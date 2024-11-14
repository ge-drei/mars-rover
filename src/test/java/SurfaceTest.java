import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceTest {

    @Test
    @DisplayName("isValidCoordinate returns false if provided with invalid coordinates")
    void testIsValidCoordinate_invalidCoordinates() {
        Surface surface = new Surface(new Coordinate(5, 5));

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
        Surface surface = new Surface(new Coordinate(5, 5));

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
}