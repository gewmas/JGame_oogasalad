package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.EnemyJSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class EnemyWaveCommunicationController extends Observable implements Observer {

    private List<String> myCreatedEnemies = new ArrayList<String>();

    public EnemyWaveCommunicationController () {
    }

    @Override
    public void update (Observable o, Object arg) {
        System.out.println("EnemyWaveCommunicationController received update from EnemyDesignTab");
        EnemyJSONObject enemy = (EnemyJSONObject) arg;
        myCreatedEnemies.add((String) enemy.get("id"));
        setChanged();
        notifyObservers(myCreatedEnemies);
        clearChanged();
    }

}
