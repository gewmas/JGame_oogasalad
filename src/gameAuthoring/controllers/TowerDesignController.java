package gameAuthoring.controllers;

import java.util.Observable;
import gameAuthoring.JSONObjects.GameData;
import gameAuthoring.JSONObjects.TowerJSONObject;

public class TowerDesignController extends DesignController {

    public TowerDesignController (GameData gameData) {
        super(gameData);
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("TowerDesignController received update from EnemyDesignTab");
        TowerJSONObject tower = (TowerJSONObject) arg;
        myGameData.addTower(tower);
    }

}
