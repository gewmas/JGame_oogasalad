package gameEngine.model.buff;
/*
 * wenxin shi
 */
public interface IBuffable {
public double getX();
public double getY();
public int getImmuneBuffs();
public int getAttackBuffs();
public int getCurrentBuffs();
public void changeCurrentBuffs(int buff);
public void changeLife(double lifePercent);
public void changeSpeed(double speedPercent);
public void changeAttack(double attackPercent);
//public void changeAttackSpeed(double offSet);
}
