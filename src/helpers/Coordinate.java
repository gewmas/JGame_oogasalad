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
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Coordinate) {
            Coordinate coordinate = (Coordinate)o;
            if(x == coordinate.x && y == coordinate.y) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        Integer xInt = x;
        Integer yInt = y;
        return xInt.hashCode() + "-".hashCode() + yInt.hashCode();
    }
}
