package gameAuthoring.controllers;

import java.util.Observable;
import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.TemporaryBarrierJSONObject;

public class TempBarrierDesignController extends DesignController{

    public TempBarrierDesignController (GameData gameData) {
        super(gameData);
    }
    
    @Override
    public void update (Observable o, Object arg) {
        System.out.println("TempBarrierDesignController received update from EnemyDesignTab");
        TemporaryBarrierJSONObject temporaryBarrier = (TemporaryBarrierJSONObject) arg;
        myGameData.addBarrier(temporaryBarrier);
        
    }

}
