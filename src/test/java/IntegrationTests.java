import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTests {

    @Test
    @DisplayName("Program flow works with all methods when given valid instructions")
    void test_moveRoverFromStrings() {
        MarsMission mission = new MarsMission();
        InputHandler handler = new InputHandler();
        String[] instructions = {"5 5", "1 2 N", "LMLMLMLMM"};

        mission.setSurface(mission.makeSurface(handler.validateMaxSurfaceSize(instructions[0])));

        mission.setRover(mission.makeRover(handler.validateRoverPosition(instructions[1])));

        SequenceResult result = mission.executeCommandSequence(handler.interpretCommandSequence(handler.validateCommandSequence(instructions[2])));

        assertAll(() -> assertTrue(result.succeeded()),
                () -> assertEquals("1 3 N", result.message())
        );
    }

    @Test
    @DisplayName("Program flow works with all methods when given instructions that will fail")
    void test_moveRoverFromStrings_failSequence() {
        MarsMission mission = new MarsMission();
        InputHandler handler = new InputHandler();
        String[] instructions = {"5 5", "1 2 N", "MMMMMMMMMMMMMMMMMMMMM"};

        mission.setSurface(mission.makeSurface(handler.validateMaxSurfaceSize(instructions[0])));

        mission.setRover(mission.makeRover(handler.validateRoverPosition(instructions[1])));

        SequenceResult result = mission.executeCommandSequence(handler.interpretCommandSequence(handler.validateCommandSequence(instructions[2])));

        assertAll(() -> assertFalse(result.succeeded()),
                () -> assertEquals("Error in step 4: Rover went out of bounds from (1,5)", result.message())
        );
    }
}
