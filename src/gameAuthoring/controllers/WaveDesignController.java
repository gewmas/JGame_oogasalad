package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.WaveJSONObject;
import java.util.Observable;


/**
 * @author Rebecca Lai & Susan Zhang
 *         WaveDesignController acts as a controller for WaveDesignTab. When a new wave is created
 *         in WaveDesignTab, WaveDesignController is notified such that it can send the newly
 *         created wave to GameData.
 */
public class WaveDesignController extends DesignController {

    /**
     * Creates new WaveDesignController
     * 
     * @param gameData is GameData to which information is written
     */
    public WaveDesignController (GameData gameData) {
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
        System.out.println("WaveDesignController received update from WaveDesignTab");
        WaveJSONObject wave = (WaveJSONObject) arg;
        myGameData.addWave(wave);
    }

}
