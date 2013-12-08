package gameEngine.model.skill;

import gameEngine.factory.magicFactory.MagicsFactory;
import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;
import jgame.impl.JGEngineInterface;

/**
 * Skills that only be casted once
 * @author Jiaran
 * 
 *
 */
public class OneTimeSkill extends Skill{
    private boolean isCasted=false;
    public OneTimeSkill (long cd, JGEngineInterface eng,String name) {
        super(cd, eng, name);
        
    }

    @Override
    protected void cast (int x, int y) {
        Detector<Enemy> detector = new Detector<Enemy>(myEng, Enemy.class);
        Enemy target = detector.getOneTargetInRange(x, y, 5);

        MagicsFactory.getInstance().createMagics(target, null, myName + "Magic");

    }
    
    @Override
    public void update (int x, int y) {
        super.update(x, y);
        if(isCastable()&& ! isCasted){
            cast();
            isCasted=true;
        }
        
    }   

}
