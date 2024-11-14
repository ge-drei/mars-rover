public class Surface {
    private int maxX;
    private int maxY;

    public Surface(int x, int y) {
        this.maxX = x;
        this.maxY = y;
    }

    public boolean isValidCoordinate(int x, int y) {
        return (x < 0 || x > maxX || y < 0 || y > maxY) ? false : true;
    }
}
