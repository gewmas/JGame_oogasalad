package gameEngine.factory.enemyfactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.Model;
import gameEngine.model.enemy.Enemy;
import gameEngine.parser.JSONLibrary.JSONObject;


/**
 * This is a factory that creates Enemy and initialize it according to
 * the JSON object. It hides the information how the program reads from
 * JSON and create the enemy.
 * 
 * @author Jiaran
 * 
 */
public class NormalEnemyFactory implements EnemyFactory {

    private String id;
    private String image;
    private double gold;
    private double life;
    private double speed;
    private String skill;
    private int specialty;
    private int damage;

    /**
     * @param enemyInfo: the JSONObject that contains an enemy type information.
     *        This method takes enemy Information and creates a enemy factory with
     *        the parameters specified in the JSON file.
     */
    public NormalEnemyFactory (JSONObject enemyInfo) {
        this.id = enemyInfo.getString("id");
        this.image = enemyInfo.getString("image");
        try{
            this.specialty = enemyInfo.getInt("specialty");
        }catch(Exception e){
            this.specialty = 0;
        }
        this.gold = enemyInfo.getDouble("gold");
        this.life = enemyInfo.getDouble("life");
        this.speed = enemyInfo.getDouble("speed");
        this.skill = enemyInfo.getString("skill");
        this.damage= enemyInfo.getInt("damage");
    }

    /**
     * this is the methods that actually creates one enemy.
     */
    @Override
    public Enemy create (Model model) {
        Enemy enemy =
                new Enemy(gold, life, speed, id, true, GameEngineConstant.ENEMY_CID, image,
                          specialty, model,damage);
        System.out.println("created");
        model.addEnemy(enemy);
        System.out.println(skill);
        enemy.setSkill(skill);
        return enemy;
    }

}
