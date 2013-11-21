package gameEngine.controller;

import gameEngine.model.Model;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
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
        view.startJGame();
//        startGame();
        // Model parses jsonFile and passes gameData to view
        // view.initialize(gameData);
        // view.showGame();
    }

    public void startGame () {
        model.startGame();
    }

    /**
     * @author Yuhua
     * Tower Related Method
     */
    
    /**
     * Get All kinds of TowerFactory
     * However, can only return the basic property of the TowerFactory
     */
    public List<PurchaseInfo> getTowerFactory () {
        return model.getAllTowerInfo();  
    }

    /**
     * Sends a call to the model to update the monitored tower stats to the tower
     * at x,y. If the position is invalid, do nothing
     */
    public PurchaseInfo getTowerInfo (int x, int y) {
        PurchaseInfo towerinfo;
        try {
            towerinfo=model.getTowerInfo(x,y);
        } catch (Exception e) {
            e.printStackTrace();
            towerinfo=null;
        }
        return towerinfo;
    }
    
    /**
     * @author Harris Osserman
     * 
     * Sends a call to the model to update the monitored barrier stats to the barrier
     * at x,y. If the position is invalid, do nothing
     */
//    public PurchaseInfo getTemporaryBarrierInfo (int x, int y) {
//        PurchaseInfo barrierInfo;
//        try {
//            barrierInfo=model.getBarrierInfo(x,y);
//        } catch (Exception e) {
//            barrierInfo=null;
//        }
//        return barrierInfo;
//    }
    
    /**
     * Sends a call to the model to purchase tower tower at position x,y
     * If position is invalid, do nothing for now
     */
    public boolean purchaseTower (int x, int y, String name) {
        return model.purchaseTower(x, y, name);
    }
    
    /**
     * Sends a call to the model to purchase temporary barrier at position x,y
     * If position is invalid, do nothing for now
     */
    public boolean purchaseTemporaryBarrier (int x, int y, String name) {
        return model.purchaseTemporaryBarrier(x, y, name);
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
        return model.sellTower(x, y);
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
        return model.upgradeTower(x, y);
    }
    
    /**
     * @author Yuhua
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
        return model.setTowerAttackMode(x, y, attackMode);
    }
    
    /**
     * @author Fabio
     * 
     * Activate input cheat
     * Succeed, return true
     * No such cheat, return false
     * 
     * @param code
     * @return bool
     */
    public boolean activateCheat(String code) {
        return model.activateCheat(code);
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

}
