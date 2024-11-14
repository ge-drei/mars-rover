import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarsMissionTest {

    @Test
    @DisplayName("makeSurface returns null if provided with any dimensions <= 0")
    void testMakeSurface_invalidDimensions() {
        MarsMission mission = new MarsMission();
        var surface1 = mission.makeSurface(0, 5);
        var surface2 = mission.makeSurface(5, 0);
        var surface3 = mission.makeSurface(0, 0);

        assertAll(() -> assertNull(surface1),
                () -> assertNull(surface2),
                () -> assertNull(surface3));
    }

    @Test
    @DisplayName("makeSurface returns a surface if provided valid dimensions")
    void testMakeSurface_validDimensions() {
        MarsMission mission = new MarsMission();
        var surface1 = mission.makeSurface(5, 5);

        assertNotNull(surface1);
    }
}