package gameEngine.model.skill;

import jgame.impl.JGEngineInterface;

/**
 * @author Jiaran
 * Facilitate the user to create skills. Here I use the most simple way to do factory.
 * It has lots of drawback to use string to do factory but in this case it is fine
 * because the creation itself is simple(just one line). Also, we don't have many skills at all.
 *  Factory Method or Abstract Factory should be an overkill.
 *
 */
public class SkillFactory {
    private JGEngineInterface myEng=null;
    public SkillFactory(JGEngineInterface eng){
        myEng=eng;
    }

    public Skill create (String skillType) {
        
        switch (skillType) {
            case "Haste":
                return new EnemyMagicSkill(300,myEng, skillType);
            case "Armour":
                return new EnemyMagicSkill(500,myEng, skillType);
            case "Heal":
                return new EnemyMagicSkill(500,myEng, skillType);
            case "Light":
                return new TowerMagicSkill(100,myEng,skillType);
            default:
                return null;
        }
    }
}
