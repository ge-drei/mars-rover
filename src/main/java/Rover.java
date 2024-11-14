public class Rover {
    private Coordinate position;
    private Direction direction;

    public Rover(Coordinate position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    public Coordinate getPosition() {
        return position;
    }
    public Direction getDirection() {
        return direction;
    }
}
