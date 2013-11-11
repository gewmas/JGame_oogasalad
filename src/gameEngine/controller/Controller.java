package gameEngine.controller;

import java.awt.Dimension;
import java.io.File;
import java.util.List;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.Model;
import gameEngine.model.tower.Tower;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrame;


public class Controller {

    Model model;
    View view;

    Dimension gameSize;

    public Controller () {

        gameSize = new Dimension(30, 30);
        view = new View(this);
        model = new Model();
    }

    public void newGame (File jsonFile) throws Exception {
        model.newGame(jsonFile); //will throw exception if fail
        view.startGame();
        // Model parses jsonFile and passes gameData to view
        // view.initialize(gameData);

        // view.showGame();
    }
    
    public void startGame(){
        model.startGame();
    }

    /**
     * Get All kinds of TowerFactory
     * However, can only return the basic property of the TowerFactory
     */
    public List<TowerFactory> getTowerFactory () {
        return model.getTowerFactory();  
    }

    /**
     * Sends a call to the model to purchase tower tower at position x,y
     * If position is invalid, do nothing for now
     */
    public void purchaseTower (int x, int y, String name) {
        model.purchaseTower(x, y, name);
    }

    /**
     * Sends a call to the model to update the monitored tower stats to the tower
     * at x,y. If the position is invalid, do nothing
     */
    public Tower getTowerInfo (int x, int y) {
        return model.getTowerInfo(x, y);
    }

    /**
     * Returns the size of the game in number of tiles
     */
    public Dimension getGameSize () {
        return gameSize;
    }

    /**
     * Returns the URL to the game's background image
     */
    public String getBGImage () {
        return "resources/space_background.jpg";
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

    /**
     * Return the score of the user
     * 
     * @author Yuhua Money is considered as the score
     * 
     */
    public int getScore () {
        //TODO Front-end guy, please delete this method
        return 0;
    }
    
    /*
     * 
     * 
     * public List<PathInfo> getPath(){
     * 
     * }
     * 
     * public String getPathImage(){
     * 
     * }
     * 
     * 
     * 
     * }
     */

}
