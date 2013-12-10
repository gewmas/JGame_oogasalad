package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.TowerJSONObject;
import java.util.Observable;


/**
 * @author Rebecca Lai & Susan Zhang
 *         TowerDesignController acts as a controller for TowerDesignTab. When all fields in
 *         TowerDesignTab are completed, it notifies TowerDesignController by passing a
 *         TowerJSONObject, which can then be added to GameData.
 */
public class TowerDesignController extends DesignController {

    /**
     * Creates new TowerDesignController
     * 
     * @param gameData is GameData to which information is written
     */
    public TowerDesignController (GameData gameData) {
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
        TowerJSONObject tower = (TowerJSONObject) arg;
        myGameData.addTower(tower);
    }

}
