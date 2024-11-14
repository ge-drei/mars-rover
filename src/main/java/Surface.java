public class Surface {
    private Coordinate max;

    public Surface(Coordinate max) {
        this.max = max;
    }

    public boolean isValidCoordinate(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();
        return (x < 0 || x > max.getX() || y < 0 || y > max.getY()) ? false : true;
    }
}
