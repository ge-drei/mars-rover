package surface;

import spatial.Coordinate;

public class BasicSurface implements Surface{
    private Coordinate max;

    public BasicSurface(Coordinate max) {
        this.max = max;
    }

    @Override
    public Coordinate getMaxCoordinates() {
        return max;
    }

    @Override
    public boolean isValidCoordinate(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return x >= 0 && x <= max.getX() && y >= 0 && y <= max.getY();
    }
}
