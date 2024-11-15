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
            case N -> (relative == Relative.LEFT) ? Cardinal.W : Cardinal.E;
            case E -> (relative == Relative.LEFT) ? Cardinal.N : Cardinal.S;
            case S -> (relative == Relative.LEFT) ? Cardinal.E : Cardinal.W;
            case W -> (relative == Relative.LEFT) ? Cardinal.S : Cardinal.N;
        };
    }

    public Coordinate calculateNewPosition() {
        return switch (direction) {
            case N -> new Coordinate(position.getX(), position.getY() + 1);
            case E -> new Coordinate(position.getX() + 1, position.getY());
            case S -> new Coordinate(position.getX(), position.getY() - 1);
            case W -> new Coordinate(position.getX() - 1, position.getY());
        };
    }
}
