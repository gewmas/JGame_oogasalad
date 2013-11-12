package gameEngine.factory.enemyfactory;

import java.util.LinkedList;
import gameEngine.Constant.Constant;
import gameEngine.model.Model;
import gameEngine.model.Tile;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.tower.DefaultTower;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;


/**
 * @author Jiaran
 *         NormalEnemyFactory. Each type of enemy should have a certain
 *         factory based on the information read from JSON file.
 *         To add more fancier enemy(eg, boss, or enemy that has different
 *         ability), one can extend from this class.
 */
public class NormalEnemyFactory implements EnemyFactory {

    private String id;
    private String image;
    private double gold;
    private double life;
    private double speed;

    public NormalEnemyFactory (JSONObject enemyInfo) {
        this.id = enemyInfo.getString("id");
        this.image = enemyInfo.getString("image");

        this.gold = enemyInfo.getDouble("gold");
        this.life = enemyInfo.getDouble("life");
        this.speed = enemyInfo.getDouble("speed");

    }

    @Override
    public Enemy create (Model model) {
        return new Enemy(gold, life, speed, id, true, Constant.ENEMY_CID, image, model);

    }

}
