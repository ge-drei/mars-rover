public class MarsMission {
    public Surface makeSurface(int x, int y) {
        if (x <= 0 || y <= 0) {
            return null;
        } else {
            return new Surface(x, y);
        }
    }
}