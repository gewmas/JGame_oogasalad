package gameAuthoring.controllers;

import gameAuthoring.model.GameData;
import java.util.Observable;
import java.util.Observer;


/**
 * @author Rebecca Lai & Susan Zhang
 *         DesignController is the superclass for all individual design tab controllers.
 */
public class DesignController implements Observer {

    protected GameData myGameData;

    /**
     * Creates new GameData
     * 
     * @param gameData is GameData to which information is written
     */
    public DesignController (GameData gameData) {
        myGameData = gameData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {

    }

}
