package gameEngine.controller;

import gameEngine.model.GameInfo;
import gameEngine.model.Model;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
import gameEngine.view.View;
import java.awt.Dimension;
import java.io.File;
import java.util.List;
import java.util.Map;
import jgame.impl.JGEngineInterface;


public class Controller {

    Model model;
    View view;
    Dimension gameSize;
    private static final String TEMPORARY_BARRIER = "Temporary Barrier";
    private static final String NAME = "Name";
    private static final String TYPE  = "Type";

    public Controller () {

        gameSize = new Dimension(20, 20);
        view = new View(this);
        model = new Model();
    }

    public void promptForFile(){
        view.promptForFile();
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
     * For Detector in Model to detect JGObjects in range
     * @param eng
     */
    public void setJGEngine(JGEngineInterface eng){
        model.setJGEngine(eng);
    }
    
    /**
     * @author Yuhua
     * Tower Related Method
     */
    
    /**
     * Get All kinds of TowerFactory, and Barriers
     * However, can only return the basic property of the TowerFactory
     */
    public Map<String, List<PurchaseInfo>> getInventory () {
        return model.getInventory();  
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
//            e.printStackTrace();
            towerinfo=null;
        }
        return towerinfo;
    }
    
    /**
     * @author Harris
     * A more generic way to purchase an object
     * GUI uses this when object is purchased
     **/
    public boolean purchaseObject(int x, int y, PurchaseInfo purchaseInfo) {
        if(purchaseInfo.getInfo().get(TYPE).equals(TEMPORARY_BARRIER)) {
            return purchaseTemporaryBarrier(x, y, purchaseInfo.getInfo().get(NAME));
        } 
        return purchaseTower(x, y, purchaseInfo.getInfo().get(NAME));

        
    }
    
    /**
     * Sends a call to the model to purchase tower tower at position x,y
     * If position is invalid, do nothing for now
     */
    public boolean purchaseTower (int x, int y, String name) {
        return model.purchaseTower(x, y, name);
    }
    
    /**
     * @author Harris
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
    
    public GameInfo getGameInfo(){
        return model.getGameInfo();
    }
    public List<Tile> getPath () {
        return model.getPathList();
    }

}
