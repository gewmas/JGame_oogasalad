package gameEngine.model.magic;
/*
 * wenxin shi
 */
public interface IMagicable {
    public double getX();
    public double getY();
    public int getImmuneMagics();
    public int getAttackMagics();
    public int getCurrentMagics();
    public void changeCurrentMagics(int magic);
    public void changeLife(double lifePercent);
    public void changeSpeed(double speedPercent);
    public void changeAttack(double attackPercent);
    //public void changeAttackSpeed(double offSet);
}
