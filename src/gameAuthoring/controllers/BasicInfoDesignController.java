package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.ResourceJSONObject;
import gameAuthoring.view.BasicInformation;
import java.util.Observable;


/**
 * @author Rebecca Lai & Susan Zhang
 *         BasicInfoDesignController acts as the controller for the BasicInfoDesignTab.
 *         BasicInfoDesignController observes BasicInfoTab such that when it receives an update from
 *         BasicInfoTab, it can send the information to GameData.
 */
public class BasicInfoDesignController extends DesignController {

    private static final String BULLET = "bullet";

    /**
     * Creates new BasicInfoDesignController
     * 
     * @param gameData is GameData instance to which information must be written
     */
    public BasicInfoDesignController (GameData gameData) {
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
        if (arg instanceof BasicInformation) {
            BasicInformation basicInformation = (BasicInformation) arg;
            myGameData.setGold(basicInformation.getGold());
            myGameData.setLives(basicInformation.getLives());
            myGameData.setSplashImage(basicInformation.getSplashImage());
            myGameData.setGameName(basicInformation.getGameName());
            myGameData.addBGAudio(basicInformation.getBackgroundAudio().getID());
            myGameData.addAudio(basicInformation.getBackgroundAudio().getID(),
                                basicInformation.getBackgroundAudio()
                                        .getAudioFile().getName());
            myGameData.setGoldName(basicInformation.getGoldName());
            myGameData.setLivesName(basicInformation.getLivesName());
            myGameData.addImage(BULLET, basicInformation.getBulletName());
        }
        if (arg instanceof ResourceJSONObject) {
            ResourceJSONObject splashImage = (ResourceJSONObject) arg;
            myGameData.addImage(splashImage);
        }
    }
}
