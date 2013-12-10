package gameEngine.controller;

import gameEngine.model.GameInfo;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
import java.io.File;
import java.util.List;
import java.util.Map;
import jgame.impl.JGEngineInterface;

/**
 * This interface defines methods that the controller must have in order for 
 * the view to operate correctly. 
 * 
 * Written by Alex Zhu
 */
public interface ControllerToViewInterface {
    public void newGame (File jsonFile) throws Exception;
    public void startGame();
    public void setJGEngine(JGEngineInterface eng);
    public Map<String, List<PurchaseInfo>> getInventory ();
    public PurchaseInfo getTowerInfo (int x, int y);
    public boolean purchaseObject(int x, int y, PurchaseInfo purchaseInfo);
    public boolean sellTower(int x, int y);
    public boolean upgradeTower(int x, int y);
    public boolean setTowerAttackMode(int x, int y, int attackMode);
    public boolean activateCheat(String code);
    public GameInfo getGameInfo();
    public List<Tile> getPath ();
    public void stopWaves();
    public Map<String,String> getImageURL();
}
