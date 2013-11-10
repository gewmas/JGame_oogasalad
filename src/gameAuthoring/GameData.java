package gameAuthoring;

import gameEngine.factory.enemyfactory.EnemyFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import java.util.Collection;


public class GameData {

    private String myGameName;
    private int myGold;
    private int myLives;

    private String mySplashImage;
    private int myNumLevels;
    private Collection<TowerFactory> myTowers;
    private Collection<EnemyFactory> myEnemies;

    public void setGameName (String gameName) {
        myGameName = gameName;
    }

    public void setGold (int gold) {
        myGold = gold;
    }

    public void setLives (int lives) {
        myLives = lives;
    }
    
    public void addTower(TowerFactory tower){
        myTowers.add(tower);      
    }
    
    public void addEnemy(EnemyFactory enemy){
        myEnemies.add(enemy);
    }

}
