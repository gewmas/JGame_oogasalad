package helpers;
/**
 * 
 * @author Harris
 * 
 * Create coordinate and allows of comparision of coordinates
 * Used in factory of grid class
 *
 */
public class Coordinate {
    private int x, y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean equals(Object o) {
        System.out.println("using the coordinate equals method");
        if(o instanceof Coordinate) {
            Coordinate coordinate = (Coordinate)o;
            if(x == coordinate.x && y == coordinate.y) {
                return true;
            }
        }
        return false;
    }
}
