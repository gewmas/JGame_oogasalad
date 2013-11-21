package gameEngine.model.magic;

public interface ITMagicable extends IMagicable{
    public void downgrade(double factor);
    public void upgrade(double factor);
}
