package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.EnemyJSONObject;
import gameAuthoring.JSONObjects.GameData;
import java.util.Observable;


public class EnemyDesignController extends DesignController {

    public EnemyDesignController (GameData gameData) {
        super(gameData);
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("EnemyDesignController received update from EnemyDesignTab");
        EnemyJSONObject enemy = (EnemyJSONObject) arg;
    }

}
