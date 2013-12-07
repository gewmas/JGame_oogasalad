package gameEngine.model;

import gameEngine.factory.gridFactory.GridFactory;
import gameEngine.factory.temporaryBarrier.TemporaryBarrierFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.effect.CreateEffect;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
import gameEngine.model.tower.Tower;
import gameEngine.model.warehouse.EnemyWarehouse;
import gameEngine.model.warehouse.TemporaryBarrierWarehouse;
import gameEngine.model.warehouse.TowerWarehouse;
import gameEngine.parser.Parser;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import jgame.impl.JGEngineInterface;


public class Model {

    /**
     * @author Yuhua
     * 
     * warehouse - store different kinds of tower, enemy warehouse
     * 
     */
    
    private Scanner scanner;
    private Parser parser;
    private GameInfo gameInfo;
    private TowerWarehouse towerWarehouse;
    private EnemyWarehouse enemyWarehouse;
    private TemporaryBarrierWarehouse temporaryBarrierWarehouse;
    private GridFactory gridFactory;
    private LinkedList<Tile> path;
    private JGEngineInterface myEng;
    private Rule rule; // how each waves created, ruleStart, ruleStop
    private ArrayList<ArrayList<Tile>> grid;
    private ArrayList<Tile> barriers;
    private ArrayList<Enemy> spawnedEnemies;

    public Model () {
         
    }

    public void newGame (File jsonFile) throws Exception {        
        scanner = new Scanner(jsonFile);
        parser = new Parser(scanner);

        gridFactory = new GridFactory(parser);
        gridFactory.initialize();
        path = gridFactory.getPathList();
        grid = gridFactory.getGridList();
        barriers = gridFactory.getBarrierList();
        spawnedEnemies = new ArrayList<Enemy>();
        temporaryBarrierWarehouse = new TemporaryBarrierWarehouse(parser);
        towerWarehouse = new TowerWarehouse(parser);
        enemyWarehouse = new EnemyWarehouse(parser, this);
        rule = new Rule(1, enemyWarehouse);
        rule.readWaveFromJSon(parser.getJSONArray("wave"));
        gameInfo = new GameInfo(parser);
    }
    // Jiaran: now we can just read waves from JSon.
    public void startGame () {

        rule.ruleStart();
        CreateEffect c = new CreateEffect();
        c.Words(300, 300, "WAVE 1");
        c.blood(200, 200);

    }

    //Yuhua change it
    //    public List<Tile> getPathList () {
    public LinkedList<Tile> getPathList () {
        return path;
    }
    
    public ArrayList<Tile> getBarrierList () {
        return barriers;
    }

    /**
     * @author Yuhua
     * Tower Related Method
     */

    /**
     * return all kinds of TowerFactory
     */
    //edit by Jiaran to hold encapsulation by passing TowerInfo.
    public Map<String, List<PurchaseInfo>> getInventory () {
        Map<String, List<PurchaseInfo>> result = new HashMap<String, List<PurchaseInfo>>();
        
        //Tower Inventory
        List<PurchaseInfo> towerInventory= new ArrayList<PurchaseInfo>();
        List<TowerFactory> factoryList=towerWarehouse.getTowerFactory();
        for(int i=0; i< factoryList.size();i++){
            towerInventory.add(factoryList.get(i).getPurchaseInfo());
        }
        result.put("Tower", towerInventory);
                
        //TemporaryBarrier Inventory
        List<PurchaseInfo> temporaryBarrierInventory = new ArrayList<PurchaseInfo>();
        List<TemporaryBarrierFactory> barrierFactoryList = temporaryBarrierWarehouse.getTemporaryBarrierFactory();
        for(int i=0; i< barrierFactoryList.size();i++){
            temporaryBarrierInventory.add((PurchaseInfo)(barrierFactoryList.get(i)));
        }
        result.put("Temporary Barrier", temporaryBarrierInventory);
        return result;
    }

    //For detector use
    public void setJGEngine(JGEngineInterface eng){
        this.myEng = eng;
        Resources r = new Resources(myEng);
        r.register(parser);
    }
    
    //Refractor method to check whether Tower exist at (x, y)
    public Tower checkTowerAtXY(int x, int y){
        int detectRange = 10;
        Detector<Tower> d= new Detector<Tower>(myEng,Tower.class);
        return d.getOneTargetInRange(x, y, detectRange);
    }

    //Jiaran: Im thinking maybe this should return a TowerInfo instead of Tower
    // Tower can implemetns Towerinfo which has getDescription,getDamage....
    // now it is not functional because no myEng, we need discussion on this.
    public PurchaseInfo getTowerInfo (int x, int y) {
        return checkTowerAtXY(x, y).getPurchaseInfo();
    }

    // Jiaran: purchase, get tower info. If something is wrong plz contact
    public boolean purchaseTower (int x, int y, String name) {
        Tile currentTile = getTile(x, y);
        if (currentTile.isEmpty() && !currentTile.hasPath()) {
            System.out.println(currentTile.isEmpty());
            currentTile.setTower();
            return towerWarehouse
                .create((int) currentTile.getX(), (int) currentTile.getY(), name, gameInfo);
            
        }
        return false;
    }

    public boolean sellTower(int x, int y){
        Tower tower = checkTowerAtXY(x, y);

        if(tower != null){
            tower.sell(gameInfo);
        }

        return false;
    }

    public boolean upgradeTower(int x, int y){
        Tower tower = checkTowerAtXY(x, y);

        if(tower != null){
            tower.upgrade(gameInfo);
        }

        return false;
    }

    public boolean setTowerAttackMode(int x, int y, int attackMode){
        Tower tower = checkTowerAtXY(x, y);

        if(tower != null){
            tower.setAttackMode(attackMode);
            return true;
        }

        return false;
    }

    public Tile getTile(int x, int y) {
        for(int k=0; k<grid.size(); k++) {
            ArrayList<Tile> tempArray = grid.get(k);
            for(int m=0; m<tempArray.size(); m++) {
                Tile tile = tempArray.get(m);
                if(tile.getX() <= x && tile.getEndX() >= x && tile.getY() <= y && tile.getEndY() >= y) {
                    return tile;
                }
            }
        }
        return null;
    }

    /**
     * @author Jiaran
     * GameInfo getter method
     * deleted by Jiaran based on Duvall's suggestion. Delete this when
     * every on is aware
     **/

    /*
     * Model Getter methods
     */

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    /**
     * @author Harris
     * Purchase temporary barrier
     **/
    public boolean purchaseTemporaryBarrier (int x, int y, String name) {
        Tile currentTile = getTile(x, y);
        if (currentTile.hasPath()) {
            currentTile.setTemporaryBarrier();
            return temporaryBarrierWarehouse
                .create((int) currentTile.getX(), (int) currentTile.getY(), name, gameInfo);
        }
        return false;
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
        String[] cheatArgs = code.split(" ");
        String cmd = cheatArgs[0];
        if(cmd.equals("add_gold")) {
            int amt = Integer.parseInt(cheatArgs[1]);
            gameInfo.addGold(amt);
        } else if(cmd.equals("add_lives")) {
            int amt = Integer.parseInt(cheatArgs[1]);
            gameInfo.addLife(amt);
        } else if(cmd.equals("kill_all")) {
            System.out.println("called kill_all and num emenies is: "+spawnedEnemies.size());
            for (int j = 0; j < spawnedEnemies.size(); j++) {
                Enemy enemy = spawnedEnemies.get(j);
                enemy.setLife(0);
                spawnedEnemies.remove(j);
            }
        } else if(cmd.equals("win_game")) {
            //TODO
        } else if (cmd.equals("lose_game")) {
            //TODO
        } else {
            return false;
        }
        return true;
    }
    
    public void addEnemy(Enemy enemy) {
        spawnedEnemies.add(enemy);
    }


}
