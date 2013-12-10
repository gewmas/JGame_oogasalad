package gameAuthoring.controllers;

import gameAuthoring.JSONObjects.EnemyJSONObject;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * @author Rebecca Lai & Susan Zhang
 *         EnemyWaveCommunicationController acts as a liason between EnemyDesignTab and
 *         WaveDesignTab. It observes EnemyDesignTab and is observed in turn by WaveDesignTab. When
 *         a new enemy is created by EnemyDesignTab, EnemyWaveCommunicationController is notified.
 *         In turn, it notifies the WaveDesignTab so that WaveDesignTab is aware of which enemies
 *         have been created.
 */
public class EnemyWaveCommunicationController extends Observable implements Observer {

    private List<String> myCreatedEnemies = new ArrayList<String>();

    /**
     * Creates new EnemyWaveCommunicationController
     */
    public EnemyWaveCommunicationController () {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update (Observable o, Object arg) {
        try {
            EnemyJSONObject enemy = (EnemyJSONObject) arg;
            myCreatedEnemies.add((String) enemy.get("id"));
            setChanged();
            notifyObservers(myCreatedEnemies);
            clearChanged();
        }
        catch (ClassCastException c) {
            JSONObject enemy = (JSONObject) arg;
            myCreatedEnemies.add((String) enemy.get("id"));
            setChanged();
            notifyObservers(myCreatedEnemies);
            clearChanged();
        }
    }

}
