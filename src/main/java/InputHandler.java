import directions.Cardinal;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void promptMaxSurfaceSize() {
        System.out.println("Input max surface size: (Format \"NUM NUM\")");
    }

    public void promptRoverPosition() {
        System.out.println("Input initial rover position: (Format \"NUM NUM N/E/S/W\")");
    }

    public void promptCommandSequence() {
        System.out.println("Input command sequence for rover: (Valid characters L, R, M)");
    }

    public Coordinate validateMaxSurfaceSize(String input) {
        if (input == null) {
            throw new NullPointerException("Input cannot be null");
        }

        Pattern surfaceSize = Pattern.compile("^\\d+ \\d+$");
        Matcher matcher = surfaceSize.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Input does not match format \"NUM NUM\"");
        }

        String[] coords = input.split(" ");
        return new Coordinate(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
    }

    public Coordinate getMaxSurfaceSize() {
        return validateMaxSurfaceSize(getInput());
    }

    public Rover.RoverPosition validateRoverPosition(String input) {
        if (input == null) {
            throw new NullPointerException("Input cannot be null");
        }

        Pattern roverPosition = Pattern.compile("^\\d+ \\d+ [NESW]$");
        Matcher matcher = roverPosition.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Input does not match format \"NUM NUM N/E/S/W\"");
        }

        String[] positionData = input.split(" ");
        Coordinate coordinate = new Coordinate(Integer.parseInt(positionData[0]), Integer.parseInt(positionData[1]));
        Cardinal direction = switch (positionData[2]) {
            case "N" -> Cardinal.NORTH;
            case "E" -> Cardinal.EAST;
            case "S" -> Cardinal.SOUTH;
            case "W" -> Cardinal.WEST;
            default -> throw new IllegalArgumentException("Direction must be one of N,E,S,W");
        };
        return new Rover.RoverPosition(coordinate, direction);
    }

    public Rover.RoverPosition getRoverPosition() {
        return validateRoverPosition(getInput());
    }

    public String validateCommandSequence(String input) {
        if (input == null) {
            throw new NullPointerException("Input cannot be null");
        }

        Pattern commandSequence = Pattern.compile("^[LRM]+$");
        Matcher matcher = commandSequence.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Command sequence can only consist of L, R, M");
        }
        return input;
    }

    public Command[] getCommandSequence() {
        String commandSequence = validateCommandSequence(getInput());
        int length = commandSequence.length();
        Command[] commands = new Command[length];
        for (int i = 0; i < length; i++) {
            commands[i] = interpretCommand(commandSequence.charAt(i));
        }
        return commands;
    }

    public Command interpretCommand(char c) {
        return switch (c) {
            case 'L' -> Command.LEFT;
            case 'R' -> Command.RIGHT;
            case 'M' -> Command.MOVE;
            default -> throw new IllegalArgumentException("Invalid character");
        };
    }

    public void out(String out) {
        System.out.println(out);
    }
}
