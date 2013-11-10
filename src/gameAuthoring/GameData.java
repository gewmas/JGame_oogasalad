package gameAuthoring;

import java.util.Collection;


public class GameData {

    private String myGameName;
    private int myGold;
    private int myLives;

    private String mySplashImage;
    private int myNumLevels;
    private Collection<TowerJSON> myTowers;
    private Collection<EnemyJSON> myEnemies;

    public void setGameName (String gameName) {
        myGameName = gameName;
    }

    public void setGold (int gold) {
        myGold = gold;
    }

    public void setLives (int lives) {
        myLives = lives;
    }

    public void addTower (TowerJSON towerJSON) {
        myTowers.add(towerJSON);
    }

    public void addEnemy (EnemyJSON enemyJSON) {
        myEnemies.add(enemyJSON);
    }

}
