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
}