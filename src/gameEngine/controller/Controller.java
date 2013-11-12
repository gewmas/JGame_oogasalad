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
//=======
//    public List<TowerFactory> getTowerFactory () {
//        return model.getTowerFactory();
//>>>>>>> master
    }

    /**
     * Sends a call to the model to purchase tower tower at position x,y
     * If position is invalid, do nothing for now
     */
    public boolean purchaseTower (int x, int y, String name) {
        return model.purchaseTower(x, y, name);
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
        return model.getGameSize();
    }

    /**
     * Returns the URL to the game's background image
     */
    public String getBGImage () {
        return model.getBGImage();
    }

    /**
     * Returns the amount of money in the game
     */
    public int getMoney () {
        return model.getMoney();
    }

    /**
     * Return the number of lives remaining
     */
    public int getLives () {
        return model.getLife();
    }

    public List<Tile> getPath () {
        return model.getPathList();
    }

    // public String getPathImage(){
    // }


}
