package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import java.util.Observable;
import java.util.Observer;


public class DesignController implements Observer {

    protected GameData myGameData;

    public DesignController (GameData gameData) {
        myGameData = gameData;
    }

    @Override
    public void update (Observable o, Object arg) {

    }

}
