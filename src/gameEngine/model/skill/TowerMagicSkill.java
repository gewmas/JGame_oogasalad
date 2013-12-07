package gameEngine.model.skill;

import gameEngine.factory.magicFactory.MagicsFactory;
import gameEngine.model.Detector;
import gameEngine.model.tower.Tower;
import java.util.List;
import jgame.impl.JGEngineInterface;

public class TowerMagicSkill extends AutoSkill {

    public TowerMagicSkill ( long cd,JGEngineInterface eng,String name) {
        super(cd, eng,name);
        
    }

    @Override
    protected void cast (int x, int y) {
        Detector<Tower> detector= new Detector<Tower>(myEng,Tower.class);
        List<Tower> targets=detector.getTargetsInRange(x, y, 300);
        
        //myEng.playAudio(myName);
        for (int i = 0; i < targets.size()&& i< 2; i++) {
            MagicsFactory.getInstance().createMagics(targets.get(i), null, myName+"Magic");
        } 

    }

   

}
