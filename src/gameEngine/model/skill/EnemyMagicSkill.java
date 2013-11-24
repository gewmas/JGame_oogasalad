package gameEngine.model.skill;

import gameEngine.factory.magicFactory.MagicsFactory;
import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;
import java.util.List;
import jgame.impl.JGEngineInterface;

public class EnemyMagicSkill extends AutoSkill {
    
    public EnemyMagicSkill(JGEngineInterface eng){
        super(6000,eng);
    }

    @Override
    protected void cast (int x, int y) {
       Detector<Enemy> detector= new Detector<Enemy>(myEng,Enemy.class);
       List<Enemy> l=detector.getTargetsInRange(x, y, 200);
       for(int i=0; i< l.size();i++){
           MagicsFactory.getInstance().createMagics(l.get(i), null,1 , -1);
       }
       
        
    }
    
  
}
