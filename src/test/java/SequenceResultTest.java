import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceResultTest {

    static SequenceResult succeeded;
    static SequenceResult failed;
    @BeforeEach
    void setupResults() {
        succeeded = new SequenceResult(true, "Message 01");
        failed = new SequenceResult(false, "Message 02");
    }

    @Test
    @DisplayName("SequenceResult succeeded method correctly returns a boolean")
    void getSucceeded() {
        assertAll(() -> assertTrue(succeeded.succeeded()),
                () -> assertFalse(failed.succeeded())
        );
    }

    @Test
    @DisplayName("SequenceResult message method correctly returns a string")
    void getMessage() {
        assertAll(() -> assertEquals("Message 01", succeeded.message()),
                () -> assertEquals("Message 02", failed.message())
        );
    }
}