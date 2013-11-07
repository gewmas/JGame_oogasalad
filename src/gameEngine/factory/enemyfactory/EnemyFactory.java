package gameEngine.factory.enemyfactory;

import java.util.HashMap;
import gameEngine.model.Enemy;
import gameEngine.model.Model;
import gameEngine.model.tower.Tower;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * @Author Fabio Berger
 */
public interface EnemyFactory {
    public Enemy create();
}
