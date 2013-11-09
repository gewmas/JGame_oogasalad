package gameEngine.controller;

import java.io.File;
import java.util.List;
import gameEngine.model.Model;
import gameEngine.model.TowerInfo;
import gameEngine.view.View;


public class Controller {

    Model model;
    View view;

    public Controller () {
        view = new View(this);
    }

    public void newGame (File jsonFile) {
        // Model parses jsonFile and passes gameData to view
        // view.initialize(gameData);
        view.showGame();

    }

    public List<TowerInfo> getTowers () {
        return null;

    }

    /*
     * public int getMoney(){
     * 
     * }
     * public int getLife(){
     * 
     * }
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
     * public String getBackgroundImage(){
     * 
     * }
     */
}
