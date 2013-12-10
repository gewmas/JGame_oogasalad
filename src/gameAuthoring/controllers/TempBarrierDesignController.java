package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.TemporaryBarrierJSONObject;
import java.util.Observable;


/**
 * @author Rebecca Lai & Susan Zhang
 *         TempBarrierDesignController acts as a controller for TempBarrierTab. When a temporary
 *         barrier is made in TempBarrierTab, a TempBarrierJSONObject is sent through the Observer
 *         interface to the controller, which then adds it to GameData.
 */
public class TempBarrierDesignController extends DesignController {

    /**
     * Creates new TempBarrierDesignController
     * 
     * @param gameData is the GameData to which information is written
     */
    public TempBarrierDesignController (GameData gameData) {
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
        TemporaryBarrierJSONObject temporaryBarrier = (TemporaryBarrierJSONObject) arg;
        myGameData.addBarrier(temporaryBarrier);

    }

}
