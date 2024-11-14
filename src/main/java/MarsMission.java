public class MarsMission {
    public Surface makeSurface(Coordinate max) {
        if (max.getX() <= 0 || max.getY() <= 0) {
            return null;
        } else {
            return new Surface(max);
        }
    }
}