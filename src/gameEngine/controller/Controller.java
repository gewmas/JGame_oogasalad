package gameEngine.controller;

import java.io.File;
import gameEngine.model.Model;
import gameEngine.view.View;

public class Controller {

    Model model;
    View view;
    
    public Controller(){
        view = new View(this);
    }

    public void newGame(File jsonFile){
        //Model parses jsonFile and passes gameData  to view
        //view.initialize(gameData);
        view.showGame();

    }
    /*
    public void setMoney(int money){
        
    }
    
    public int getMoney(){
        
    }

    public int getLife(){
        
    }

    public void setTower(Type type, Position pos){
        
    }
    public List<Tower> getTowers(){
        
    }

    public List<Enemy> getEnemies(){
        
    }
    public List<Bullet> getBullets(){
        
    }

    public List<Path> getPath(){
        
    }

    public Image getPathImage(){
        
    }
    public Image getBackgroundImage(){
        
    }*/
}
