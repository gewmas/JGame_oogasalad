package gameEngine.model.skill;

import jgame.impl.JGEngineInterface;

abstract public class AutoSkill extends Skill{
    
    
    public AutoSkill (long cd, JGEngineInterface eng) {
        super(cd,eng);
       
    }

    @Override
    public void update (int x, int y) {
        super.update(x, y);
        if(isCastable()){
            cast();
        }
        
    }   

}
