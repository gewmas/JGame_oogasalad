package gameEngine.model.skill;

import jgame.impl.JGEngineInterface;

/**
 * Auto skill represents a type of skill the can be automatically casted.
 * It has its cooldown system. Every time an enemy finds its skill is
 * in cooldown. It will cast it.
 * @author Jiaran
 *
 */
abstract public class AutoSkill extends Skill{
    
    
    public AutoSkill (long cd, JGEngineInterface eng,String name) {
        super(cd,eng,name);
        
       
    }

    @Override
    public void update (int x, int y) {
        super.update(x, y);
        if(isCastable()){
            cast();
        }
        
    }   

}
