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
            case "Haste":
                return new EnemyMagicSkill(300,myEng, skillType);
            case "Armor":
                return new EnemyMagicSkill(500,myEng, skillType);
            case "Heal":
                return new EnemyMagicSkill(500,myEng, skillType);
            case "Storm":
                return new TowerMagicSkill(1000,myEng,skillType);
            default:
                return null;
        }
    }
}
