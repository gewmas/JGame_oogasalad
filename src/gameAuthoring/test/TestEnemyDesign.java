package gameAuthoring.test;

import static org.junit.Assert.assertTrue;
import gameAuthoring.JSONObjects.EnemyJSONObject;
import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.controllers.EnemyDesignController;
import gameAuthoring.view.EnemyDesignTab;
import gameEngine.parser.JSONLibrary.JSONArray;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestEnemyDesign {

    private static GameData myGameData;
    private static EnemyDesignTab myEnemyDesignTab;
    private static EnemyDesignController myEnemyDesignController;
    private static JSONArray myEnemies;

    @BeforeClass
    public static void setUpBeforeClass () throws Exception {
        myGameData = new GameData();
        myEnemyDesignTab = new EnemyDesignTab();
        myEnemyDesignController = new EnemyDesignController(myGameData);
        myEnemyDesignTab.addObserver(myEnemyDesignController);
        myEnemies = (JSONArray) myGameData.get("enemyType");
    }

    @Test
    public void testTabControllerCommunication () {
        EnemyJSONObject dummyEnemy = new EnemyJSONObject("name", 0, "id", 0, 0, 0, "skill");
        myEnemyDesignTab.notifyObservers(dummyEnemy);
        assertTrue(myEnemies.length() == 1);
    }

}
