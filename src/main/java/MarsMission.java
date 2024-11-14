import directions.*;

public class MarsMission {
    private Surface surface;
    private Rover rover;

    public Surface makeSurface(Coordinate max) {
        if (max.getX() <= 0 || max.getY() <= 0) {
            throw new IllegalArgumentException("Max plateau co-ordinates must be greater than zero");
        } else {
            return new Surface(max);
        }
    }

    public Rover makeRover(Coordinate position, Cardinal direction) {
        if (position == null) {
            throw new IllegalArgumentException("Rover position cannot be null");
        }
        if (direction == null) {
            throw new IllegalArgumentException("Rover direction cannot be null");
        }

        return new Rover(position, direction);
    }

    public Command interpretCommand(char c) {
        return switch (c) {
            case 'L' -> Command.LEFT;
            case 'R' -> Command.RIGHT;
            case 'M' -> Command.MOVE;
            default -> throw new IllegalArgumentException("Invalid character");
        };
    }

    public void setSurface(Surface surface) {this.surface = surface;}
    public void setRover(Rover rover) {this.rover = rover;}

    public void turnRover(Relative relative) {
        rover.setDirection(rover.getTurnDirection(relative));
    }

    public boolean isRoverStepValid() {
        Coordinate destination = rover.calculateNewPosition();
        return surface.isValidCoordinate(destination);
    }
}