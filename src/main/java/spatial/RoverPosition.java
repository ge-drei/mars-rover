package spatial;

public class RoverPosition {
    Coordinate coordinates;
    Cardinal direction;

    public RoverPosition(Coordinate coordinates, Cardinal direction) {
        this.coordinates = coordinates;
        this.direction = direction;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }
    public Cardinal getDirection() {
        return direction;
    }
}