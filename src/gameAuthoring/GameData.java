package gameAuthoring;

import gameEngine.factory.enemyfactory.EnemyFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import java.util.Collection;


public class GameData {

    private String myGameName;
    private String mySplashImage;
    private int myGold;
    private int myLives;
    private int myNumLevels;
    private Collection<TowerFactory> myTowerFactory;
    private Collection<EnemyFactory> myEnemyFactory;

}
