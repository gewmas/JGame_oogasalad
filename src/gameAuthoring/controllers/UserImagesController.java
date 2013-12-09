package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.ResourceJSONObject;
import java.util.Observable;


public class UserImagesController extends DesignController {

    public UserImagesController (GameData gameData) {
        super(gameData);
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("UserImagesController received update from UserImagesTab");
        ResourceJSONObject image = (ResourceJSONObject) arg;
        myGameData.addImage(image);
    }
}
