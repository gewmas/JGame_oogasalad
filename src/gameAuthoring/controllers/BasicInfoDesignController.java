package gameAuthoring.controllers;

import java.util.Observable;
import gameAuthoring.JSONObjects.GameData;

public class BasicInfoDesignController extends DesignController {

    public BasicInfoDesignController (GameData gameData) {
        super(gameData);
    }
    
    @Override
    public void update (Observable o, Object arg) {
        System.out.println("BasicInfoController received update from EnemyDesignTab");
        
    }

}
