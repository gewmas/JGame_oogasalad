package gameEngine.model.magic;

public interface ITMagicable extends IMagicable{
    public void upgrade(double factor);
    public void downgrade(double factor);
    public double changePercentRange(double percent);
    public double changeRange(double offset);
}
