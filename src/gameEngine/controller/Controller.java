package gameEngine.controller;

import java.awt.Dimension;
import java.io.File;
import java.util.List;
import gameEngine.model.Model;
import gameEngine.model.TowerInfo;
import gameEngine.view.View;
import gameEngine.view.GameFrame;


public class Controller {

    Model model;
    View view;

    Dimension gameSize;
    int money;
    int lives;
    int score;

    public Controller () {
        lives = 10;
        money = 100;
        score = 0;
        gameSize = new Dimension(30, 30);
        view = new View(this);
    }

    public boolean newGame (File jsonFile) {
        // Model parses jsonFile and passes gameData to view
        // view.initialize(gameData);
        view.startGame();
        return true;
        // view.showGame();

    }

    public List<TowerInfo> getTowers () {
        return null;

    }

    /**
     * Sends a call to the model to purchase tower tower at position x,y
     * If position is invalid, do nothing for now
     */
    public void purchaseTower (int x, int y, String tower) {

    }

    /**
     * Sends a call to the model to update the monitored tower stats to the tower
     * at x,y. If the position is invalid, do nothing
     */
    public void getTowerInfo (int x, int y) {

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
        return money;
    }

    /**
     * Return the number of lives remaining
     */
    public int getLives () {
        return lives;
    }

    /**
     * Return the score of the user
     */

    public int getScore () {
        return score;
    }
    /*
     * public void placeTower(String name, Position pos){
     * 
     * }
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
