import spatial.*;

public class Rover {
    private Coordinate position;
    private Cardinal direction;

    public Rover(Coordinate position, Cardinal direction) {
        this.position = position;
        this.direction = direction;
    }

    public Rover(RoverPosition position) {
        this.position = position.getCoordinates();
        this.direction = position.getDirection();
    }

    public Coordinate getPosition() {
        return position;
    }
    public Cardinal getDirection() {
        return direction;
    }

    public void setDirection(Cardinal direction) {
        this.direction = direction;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public Cardinal getTurnDirection(Relative relative) {
        return switch (direction) {
            case NORTH -> (relative == Relative.LEFT) ? Cardinal.WEST : Cardinal.EAST;
            case EAST -> (relative == Relative.LEFT) ? Cardinal.NORTH : Cardinal.SOUTH;
            case SOUTH -> (relative == Relative.LEFT) ? Cardinal.EAST : Cardinal.WEST;
            case WEST -> (relative == Relative.LEFT) ? Cardinal.SOUTH : Cardinal.NORTH;
        };
    }

    public Coordinate calculateNewPosition() {
        return switch (direction) {
            case NORTH -> new Coordinate(position.getX(), position.getY() + 1);
            case EAST -> new Coordinate(position.getX() + 1, position.getY());
            case SOUTH -> new Coordinate(position.getX(), position.getY() - 1);
            case WEST -> new Coordinate(position.getX() - 1, position.getY());
        };
    }
}
