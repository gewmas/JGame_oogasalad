package gameEngine.controller;

import gameEngine.model.Model;
import gameEngine.model.Tile;
import gameEngine.model.tower.Tower;
import gameEngine.model.tower.TowerInfo;
import gameEngine.view.View;
import java.awt.Dimension;
import java.io.File;
import java.util.List;


public class Controller {

    Model model;
    View view;


    Dimension gameSize;

    public Controller () {

        gameSize = new Dimension(20, 20);
        view = new View(this);
        model = new Model();
    }

    public void newGame (File jsonFile) throws Exception {
        model.newGame(jsonFile); // will throw exception if fail
        view.startGame();
        startGame();
        // Model parses jsonFile and passes gameData to view
        // view.initialize(gameData);
        // view.showGame();
    }

    public void startGame () {
        model.startGame();
    }

    /**
     * Get All kinds of TowerFactory
     * However, can only return the basic property of the TowerFactory
     */
    public List<TowerInfo> getTowerFactory () {
        return model.getAllTowerInfo();  
    }

    /**
     * Sends a call to the model to purchase tower tower at position x,y
     * If position is invalid, do nothing for now
     */
    public boolean purchaseTower (int x, int y, String name) {
        return model.purchaseTower(x, y, name);
    }
    
    /**
     * @author Yuhua
     * 
     * Sell Tower at (x,y)
     * Succeed, return true, added gold
     * No such tower, return false
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean sellTower(int x, int y){
        
        return false;
    }
    
    /**
     * @author Yuhua
     * 
     * Upgrade Tower at (x, y)
     * Succeed, return true, decrease gold
     * No such tower/Insufficient fund, return false
     * 
     * @param x
     * @param y
     * @return
     */
    public boolean upgradeTower(int x, int y){
        
        return false;
    }
    
    /**
     * Set Tower Attack Mode at (x, y)
     * 
     * AttackMode include 
     * 0 - shoot the closest enemy
     * 1 - shoot the farthest enemy
     * 2 - shoot weakest enemy with least life
     * 3 - shoot strongest enemy with most life
     *
     * Return true when set tower attackMode succeed
     * Return false when no such tower/already at that attackMode
     * 
     * @param x
     * @param y
     * @param attackMode
     * @return
     */
    public boolean setTowerAttackMode(int x, int y, int attackMode){
        
        return false;
    }
    
    /**
     * Sends a call to the model to update the monitored tower stats to the tower
     * at x,y. If the position is invalid, do nothing
     */
    public TowerInfo getTowerInfo (int x, int y) {
        TowerInfo towerinfo;
        try {
            towerinfo=model.getTowerInfo(x,y);
        } catch (Exception e) {
            towerinfo=null;
        }
        return towerinfo;
    }

    /**
     * Returns the size of the game in number of tiles
     */
    public Dimension getGameSize () {
        return model.getGameInfo().getDimension();
    }

    /**
     * Returns the URL to the game's background image
     */
    public String getBGImage () {
        return model.getGameInfo().getBGImage();
    }

    /**
     * Returns the amount of money in the game
     */
    public int getMoney () {
        return model.getGameInfo().getGold();
    }

    /**
     * Return the number of lives remaining
     */
    public int getLives () {
        return model.getGameInfo().getLife();
    }

    public List<Tile> getPath () {
        return model.getPathList();
    }

    // public String getPathImage(){
    // }


}
