package gameEngine.model.warehouse;

import gameEngine.factory.enemyfactory.EnemyFactory;
import gameEngine.factory.enemyfactory.NormalEnemyFactory;
import gameEngine.model.Model;
import gameEngine.model.Tile;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class EnemyWarehouse extends Warehouse {

    private JSONArray jsonArray;
    private Model model;
    private Map<String, EnemyFactory> myEnemyFactories;

    public EnemyWarehouse (Parser parser, Model model) {
        this.jsonArray = parser.getJSONArray("enemyType");
        this.model = model;
        this.myEnemyFactories = new HashMap<String, EnemyFactory>();

        // loop through all kinds of enemies
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject allEnemyType = jsonArray.getJSONObject(i);
            String name = allEnemyType.getString("id");

            /*
             * actually, JSon file should also have enemy type instead of ID if
             * we have more than one type of enemy
             */
            EnemyFactory ef = (EnemyFactory) new NormalEnemyFactory(allEnemyType);

            myEnemyFactories.put(name, ef);
        }
    }

    public void create (String name) {
        EnemyFactory enemyFactory = myEnemyFactories.get(name);
        enemyFactory.create(model);
    }
}
