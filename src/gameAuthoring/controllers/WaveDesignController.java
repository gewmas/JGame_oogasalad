package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.WaveJSONObject;
import java.util.Observable;


public class WaveDesignController extends DesignController {

    public WaveDesignController (GameData gameData) {
        super(gameData);
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("WaveDesignController received update from WaveDesignTab");
        WaveJSONObject wave = (WaveJSONObject) arg;
        myGameData.addWave(wave);
    }

}
