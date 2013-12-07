package gameEngine.model.skill;

import jgame.impl.JGEngineInterface;

abstract public class AutoSkill extends Skill{
    
    
    public AutoSkill (long cd, JGEngineInterface eng,String name) {
        super(cd,eng);
        myName=name;
       
    }

    @Override
    public void update (int x, int y) {
        super.update(x, y);
        if(isCastable()){
            cast();
        }
        
    }   

}
