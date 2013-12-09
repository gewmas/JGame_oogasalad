package gameAuthoring.controllers;

import java.util.Observable;
import gameAuthoring.JSONObjects.GameData;

public class TempBarrierDesignController extends DesignController{

    public TempBarrierDesignController (GameData gameData) {
        super(gameData);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void update (Observable o, Object arg) {
        System.out.println("TempBarrierDesignController received update from EnemyDesignTab");
        
    }

}
