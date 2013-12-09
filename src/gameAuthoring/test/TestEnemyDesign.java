package gameAuthoring.test;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.controllers.EnemyDesignController;
import gameAuthoring.view.EnemyDesignTab;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestEnemyDesign {

    private static GameData myGameData;
    private static EnemyDesignTab myEnemyDesignTab;
    private static EnemyDesignController myEnemyDesignController;

    @BeforeClass
    public static void setUpBeforeClass () throws Exception {
        myGameData = new GameData();
        myEnemyDesignTab = new EnemyDesignTab();
        myEnemyDesignController = new EnemyDesignController(myGameData);
        myEnemyDesignTab.addObserver(myEnemyDesignController);
    }

    @Test
    public void testTabControllerCommunication () {
    }

}
