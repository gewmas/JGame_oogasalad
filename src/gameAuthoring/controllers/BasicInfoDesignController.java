package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.ResourceJSONObject;
import gameAuthoring.view.BasicInformation;
import java.util.Observable;


public class BasicInfoDesignController extends DesignController {

    public BasicInfoDesignController (GameData gameData) {
        super(gameData);
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("BasicInfoController received update from EnemyDesignTab");
        if (arg instanceof BasicInformation) {
            System.out.println("Added basic information to game data");
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
            myGameData.addImage("bullet", basicInformation.getBulletName());
        }
        if (arg instanceof ResourceJSONObject) {
            System.out.println("Added splash image to game data");
            ResourceJSONObject splashImage = (ResourceJSONObject) arg;
            myGameData.addImage(splashImage);
        }
    }
}
