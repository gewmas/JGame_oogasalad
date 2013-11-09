package gameEngine.factory.enemyfactory;

import gameAuthoring.Writeable;
import gameEngine.Constant.Constant;
import gameEngine.model.enemy.Enemy;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.io.IOException;
import java.nio.CharBuffer;


/**
 * @author Jiaran
 *         NormalEnemyFactory. Each type of enemy should have a certain
 *         factory based on the information read from JSON file.
 *         To add more fancier enemy(eg, boss, or enemy that has different
 *         ability), one can extend from this class.
 */
public class NormalEnemyFactory implements EnemyFactory, Readable, Writeable {

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
    public Enemy create () {
        return new Enemy(gold, life, speed, id, true, 50, 120, Constant.ENEMY_CID, image);

    }

    @Override
    public int read (CharBuffer cb) throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

}
