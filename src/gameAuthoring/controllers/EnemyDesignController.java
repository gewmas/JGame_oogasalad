package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.AnimationJSONObject;
import gameAuthoring.JSONObjects.EnemyJSONObject;
import gameAuthoring.model.GameData;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.Observable;


/**
 * @author Rebecca Lai & Susan Zhang
 *         EnemyDesignController acts as a controller for EnemyDesignTab. EnemyDesignController
 *         observes EnemyDesignTab such that when it receives an update from EnemyDesignTab, it can
 *         send the information to GameData.
 */
public class EnemyDesignController extends DesignController {

    /**
     * Creates new EnemyDesignController
     * 
     * @param gameData is GameData instance to which information must be written
     */
    public EnemyDesignController (GameData gameData) {
        super(gameData);
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameAuthoring.controllers.DesignController#update(java.util.Observable,
     * java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {
        if (arg instanceof EnemyJSONObject) {
            EnemyJSONObject enemy = (EnemyJSONObject) arg;
            myGameData.addEnemy(enemy);
        }
        else if (arg instanceof JSONObject) {
            JSONObject enemy = (JSONObject) arg;
            myGameData.addEnemy(enemy);
        }
        if (arg instanceof AnimationJSONObject) {
            AnimationJSONObject animation = (AnimationJSONObject) arg;
            myGameData.addAnimation(animation);
        }
    }

}
