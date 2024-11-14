import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurfaceTest {

    @Test
    @DisplayName("isValidCoordinate returns false if provided with invalid coordinates")
    void testIsValidCoordinate_invalidCoordinates() {
        Surface surface = new Surface(5, 5);

        assertAll(() -> assertFalse(surface.isValidCoordinate(-1, -1)),
                () -> assertFalse(surface.isValidCoordinate(-1, 0)),
                () -> assertFalse(surface.isValidCoordinate(-1, 3)),
                () -> assertFalse(surface.isValidCoordinate(-1, 5)),
                () -> assertFalse(surface.isValidCoordinate(-1, 6)),
                () -> assertFalse(surface.isValidCoordinate(0, -1)),
                () -> assertFalse(surface.isValidCoordinate(0, 6)),
                () -> assertFalse(surface.isValidCoordinate(3, -1)),
                () -> assertFalse(surface.isValidCoordinate(3, 6)),
                () -> assertFalse(surface.isValidCoordinate(5, -1)),
                () -> assertFalse(surface.isValidCoordinate(5, 6)),
                () -> assertFalse(surface.isValidCoordinate(6, -1)),
                () -> assertFalse(surface.isValidCoordinate(6, 0)),
                () -> assertFalse(surface.isValidCoordinate(6, 3)),
                () -> assertFalse(surface.isValidCoordinate(6, 6)));
    }

    @Test
    @DisplayName("isValidCoordinate returns true if provided with valid coordinates")
    void testIsValidCoordinate_validCoordinates() {
        Surface surface = new Surface(5, 5);

        assertAll(() -> assertTrue(surface.isValidCoordinate(0, 0)),
                () -> assertTrue(surface.isValidCoordinate(3, 0)),
                () -> assertTrue(surface.isValidCoordinate(5, 0)),
                () -> assertTrue(surface.isValidCoordinate(0, 3)),
                () -> assertTrue(surface.isValidCoordinate(3, 3)),
                () -> assertTrue(surface.isValidCoordinate(5, 3)),
                () -> assertTrue(surface.isValidCoordinate(0, 5)),
                () -> assertTrue(surface.isValidCoordinate(3, 5)),
                () -> assertTrue(surface.isValidCoordinate(5, 5)));
    }
}