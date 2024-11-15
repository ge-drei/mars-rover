package rover;

import spatial.*;

public interface Rover {
    public Coordinate getPosition();
    public Cardinal getDirection();

    public void setPosition(Coordinate position);
    public void setDirection(Cardinal direction);

    public Cardinal getTurnDirection(Relative relative);
    public Coordinate calculateNewPosition();
}
