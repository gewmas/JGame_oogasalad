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
    private Collection<TowerFactory> myTowerFactory;
    private Collection<EnemyFactory> myEnemyFactory;
    
    
    public void setGameName(String gameName){
        myGameName = gameName;
    }
    
    public void setGold(String gold){
        
    }

}
