import io.Command;
import io.InputHandler;
import spatial.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spatial.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class InputHandlerTest {

    static InputHandler handler;

    @BeforeEach
    void makeHandler() {
        handler = new InputHandler();
    }

    @Test
    @DisplayName("validateMaxSurfaceSize throws NullPointerException if handed null")
    void testValidateMaxSurfaceSize_null() {
        assertThrows(NullPointerException.class, () -> handler.validateMaxSurfaceSize(null));
    }

    @Test
    @DisplayName("validateMaxSurfaceSize throws IllegalArgumentException if handed empty string")
    void testValidateMaxSurfaceSize_emptyString() {
        assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize(""));
    }

    @Test
    @DisplayName("validateMaxSurfaceSize throws IllegalArgumentException if handed improperly formatted strings")
    void testValidateMaxSurfaceSize_invalidString() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize(" ")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("a")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("15")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1a 5a")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize(" 1 5")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1 5 ")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1a5")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1,5")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1, 5"))
        );
    }

    @Test
    @DisplayName("validateMaxSurfaceSize throws IllegalArgumentException if handed string of negative numbers")
    void testValidateMaxSurfaceSize_invalidNegativeNumbers() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("-1 -5")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1 -5")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("-1 5"))
        );
    }

    @Test
    @DisplayName("validateMaxSurfaceSize returns a valid coordinate if handed correctly formatted strings")
    void testValidateMaxSurfaceSize_validString() {
        assertAll(
                () -> assertEquals(new Coordinate(0, 0), handler.validateMaxSurfaceSize("0 0")),
                () -> assertEquals(new Coordinate(5, 5), handler.validateMaxSurfaceSize("5 5")),
                () -> assertEquals(new Coordinate(10, 10), handler.validateMaxSurfaceSize("10 10"))
        );
    }

    @Test
    @DisplayName("validateRoverPosition throws NullPointerException if handed null")
    void testValidateRoverPosition_null() {
        assertThrows(NullPointerException.class, () -> handler.validateRoverPosition(null));
    }

    @Test
    @DisplayName("validateRoverPosition throws IllegalArgumentException if handed empty string")
    void testValidateRoverPosition_emptyString() {
        assertThrows(IllegalArgumentException.class, () -> handler.validateRoverPosition(""));
    }

    @Test
    @DisplayName("validateRoverPosition throws IllegalArgumentException if handed improperly formatted strings")
    void testValidateRoverPosition_invalidString() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize(" ")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("a")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("15 15 a")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1a 5a a")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("N")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("E")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1 1 Q")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1 1 N ")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize(" 1 1 N")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize(" 1 1 N ")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1 1N")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1 1 n")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("a a N")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("a 1a E")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1a a S")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("-5 5 W")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1,1,W")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateMaxSurfaceSize("1 1W"))
        );
    }

    @Test
    @DisplayName("validateRoverPosition returns a valid rover position if handed correctly formatted strings")
    void testValidateRoverPosition_validString() {
        RoverPosition zeroZeroNorth = handler.validateRoverPosition("0 0 N");
        RoverPosition zeroOneNorth = handler.validateRoverPosition("0 1 N");
        RoverPosition tenTenNorth = handler.validateRoverPosition("10 10 N");
        RoverPosition oneOneEast = handler.validateRoverPosition("1 1 E");
        RoverPosition oneOneSouth = handler.validateRoverPosition("1 1 S");
        RoverPosition oneOneWest = handler.validateRoverPosition("1 1 W");

        assertAll(
                () -> {assertEquals(new Coordinate(0, 0), zeroZeroNorth.getCoordinates());
                    assertEquals(Cardinal.N, zeroZeroNorth.getDirection());},

                () -> {assertEquals(new Coordinate(0, 1), zeroOneNorth.getCoordinates());
                    assertEquals(Cardinal.N, zeroOneNorth.getDirection());},

                () -> {assertEquals(new Coordinate(10, 10), tenTenNorth.getCoordinates());
                    assertEquals(Cardinal.N, zeroZeroNorth.getDirection());},

                () -> {assertEquals(new Coordinate(1, 1), oneOneEast.getCoordinates());
                    assertEquals(Cardinal.E, oneOneEast.getDirection());},

                () -> {assertEquals(new Coordinate(1, 1), oneOneSouth.getCoordinates());
                    assertEquals(Cardinal.S, oneOneSouth.getDirection());},

                () -> {assertEquals(new Coordinate(1, 1), oneOneWest.getCoordinates());
                    assertEquals(Cardinal.W, oneOneWest.getDirection());}
        );
    }

    @Test
    @DisplayName("validateCommandSequence throws IllegalArgumentException if handed null or empty string")
    void testValidateCommandSequence_stringNullOrEmpty() {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence(""))
        );
    }

    @Test
    @DisplayName("validateCommandSequence throws IllegalArgumentException if handed improperly formatted strings")
    void testValidateCommandSequence_invalidString() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence("lrm")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence(" LRM")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence("LRM ")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence("L,R,M")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence("L R M")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence("LRm")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence("L5M2")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence("L5 M2")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.validateCommandSequence("L RM"))
        );
    }

    @Test
    @DisplayName("validateCommandSequence returns a valid coordinate if handed correctly formatted strings")
    void testValidateCommandSequence_validString() {
        assertAll(
                () -> assertEquals("LRLRM", handler.validateCommandSequence("LRLRM")),
                () -> assertEquals("MMMMMMMMMMMMM", handler.validateCommandSequence("MMMMMMMMMMMMM")),
                () -> assertEquals("L", handler.validateCommandSequence("L"))
        );
    }

    @Test
    @DisplayName("interpretCommand throws IllegalArgumentException if invalid character is passed")
    void testInterpretCommand_invalidChar() {
        assertAll( () -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommand('l')),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommand('r')),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommand('m')),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommand('A')),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommand('1')),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommand(' '))
        );
    }

    @Test
    @DisplayName("interpretCommand returns correct Command if valid character is passed")
    void testInterpretCommand_validChar() {
        assertAll(() -> assertEquals(Command.LEFT, handler.interpretCommand('L')),
                () -> assertEquals(Command.RIGHT, handler.interpretCommand('R')),
                () -> assertEquals(Command.MOVE, handler.interpretCommand('M'))
        );
    }

    @Test
    @DisplayName("interpretCommandSequence throws IllegalArgumentException if null string is passed")
    void testInterpretCommandSequence_nullString() {
        assertThrows(IllegalArgumentException.class, () -> handler.interpretCommandSequence(null));
    }

    @Test
    @DisplayName("interpretCommandSequence throws IllegalArgumentException if invalid character in string")
    void testInterpretCommandSequence_invalidString() {
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommandSequence("lrm")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommandSequence("L R M")),
                () -> assertThrows(IllegalArgumentException.class, () -> handler.interpretCommandSequence("LlRM"))
        );
    }

    @Test
    @DisplayName("interpretCommandSequence returns correct Command array if passed valid string")
    void testInterpretCommandSequence_validString() {
        Command[] expectedResult = {Command.LEFT, Command.RIGHT, Command.MOVE};

        assertArrayEquals(expectedResult, handler.interpretCommandSequence("LRM"));
    }
}