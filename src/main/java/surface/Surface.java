package surface;

import spatial.Coordinate;

public interface Surface {
    public Coordinate getMaxCoordinates();

    public boolean isValidCoordinate(Coordinate coordinate);
}
