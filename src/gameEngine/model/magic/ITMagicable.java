package gameEngine.model.magic;

public interface ITMagicable extends IMagicable{
    public void upgrade(double factor);
    public void downgrade(double factor);
}
