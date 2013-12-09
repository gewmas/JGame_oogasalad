package gameEngine.model.skill;

import gameEngine.factory.magicFactory.MagicsFactory;
import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;
import java.util.List;
import jgame.impl.JGEngineInterface;


/**
 * The skills that cast magic to surrounding enemies such as Haste and Heal
 * 
 * @author Jiaran
 * 
 */
public class EnemyMagicSkill extends AutoSkill {

    public EnemyMagicSkill (long cd, JGEngineInterface eng, String skillName) {
        super(cd, eng, skillName);

    }

    @Override
    protected void cast (int x, int y) {
        Detector<Enemy> detector = new Detector<Enemy>(myEng, Enemy.class);
        List<Enemy> targets = detector.getTargetsInRange(x, y, 50);
        // myEng.playAudio(myName);
        for (int i = 0; i < targets.size(); i++) {
            MagicsFactory.getInstance().createMagics(targets.get(i), null, myName + "Magic");
        }

    }

}
