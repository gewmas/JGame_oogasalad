package gameEngine.model.skill;

import jgame.impl.JGEngineInterface;

public class SkillFactory {
    private JGEngineInterface myEng=null;
    public SkillFactory(JGEngineInterface eng){
        myEng=eng;
    }

    public Skill create (String skillType) {
        System.out.println(skillType);
        switch (skillType) {
            case "haste":
                return new EnemyMagicSkill(myEng);
            default:
                return null;
        }
    }
}
