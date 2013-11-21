package gameEngine.model.magic;
/*
 * wenxin shi
 */
public interface IEMagicable extends IMagicable{

    public int getCurrentMagics();
    public void changeCurrentMagics(int magic);
    public void changeLife(double lifePercent);
    public void changeSpeed(double speedPercent);

//    public int getImmuneMagics();
//    public int getAttackMagics();
//    public void changeAttack(double attackPercent);
//    public void changeAttackSpeed(double offSet);

}
