package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.AnimationJSONObject;
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
        if (arg instanceof EnemyJSONObject) {
            System.out.println("EnemyDesignController added new enemy");
            EnemyJSONObject enemy = (EnemyJSONObject) arg;
            myGameData.addEnemy(enemy);
        }
        if (arg instanceof AnimationJSONObject) {
            System.out.println("EnemyDesignController added new animation");
            AnimationJSONObject animation = (AnimationJSONObject) arg;
            myGameData.addAnimation(animation);
        }
    }

}
