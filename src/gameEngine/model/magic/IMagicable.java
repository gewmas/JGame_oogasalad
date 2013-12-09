package gameEngine.model.magic;
/**
 * 
 * @author wenxin
 *
 */
public interface IMagicable {
    public boolean isAlive ();
    public double getX();
    public double getY();
    public int getCurrentMagics();
    public void setCurrentMagic(int magic);
    public double changePercentSpeed(double percent);
    public double changeSpeed(double offset);
}
