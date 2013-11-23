package gameEngine.model.magic;

public interface IMagicable {
    public boolean isAlive ();
    public double getX();
    public double getY();
    public int getCurrentMagics();
    public void setCurrentMagic(int magic);
}
