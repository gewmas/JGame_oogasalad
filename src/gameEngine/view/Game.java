package gameEngine.view;

import gameEngine.controller.Controller;
import gameEngine.model.Grid;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;
import jgame.platform.StdGame;


/**
 * @author lalitamaraj alexzhu
 *         Displays the Game setting using JGEngine to facilitate
 *         graphics rendering
 */
public class Game extends StdGame {

    private int WIDTH=600;
    private int HEIGHT=600;
    
    private View view;
    private boolean purchasing;
    private String towerToPurchase;
//    private int lives;
    private int money; 
    
    public Game (View view) {
        this.view=view;
        initEngineComponent(WIDTH, HEIGHT);
    }

    @Override
    public void initCanvas () {
        Dimension size=view.getGameSize();
        System.out.println(size.width);
        System.out.println(size.height);
        setCanvasSettings(size.width, size.height, WIDTH/size.width,
                          HEIGHT/size.height, null, JGColor.white, null);
    }

    @Override
    public void initGame () {
        setFrameRate(30, 2);
        initial_lives=view.getLives();
        String bgImage=view.getBGImage();
        defineImage("background","bg",256,bgImage,"-");
        setBGImage("background");
        purchasing=false;
    }

//    @Override
    public void doFrameInGame() {
        checkCollision(0,0);
        checkUserInteractions();
        updateGameStats();
    }
    
    /**
     * Checks if the user has clicked on the screen, and if so, either tries to
     * buy a tower at the clicked tile position or tries to get the info of
     * the tower at the clicked tile position
     */
    public void checkUserInteractions(){
        if (getMouseButton(1) && getMouseInside()) {
            clearMouseButton(1);
            JGPoint mousePosition=getMousePos();
            JGPoint tilePosition=getTileIndex(mousePosition.x,mousePosition.y);
            if (purchasing){
                view.buyTower(tilePosition.x,tilePosition.y,towerToPurchase);
                purchasing=false;
                System.out.format("Buying tower at: %d,%d\n",tilePosition.x,tilePosition.y);
            } else {
                view.getTowerInfo(tilePosition.x,tilePosition.y);
                System.out.format("Checking tower at: %d,%d\n",tilePosition.x,tilePosition.y);
            }
        }
    }
    
    /**
     * Updates the number of lives and money remaining in the game
     */
    public void updateGameStats(){
        lives=view.getLives();
        System.out.println(lives);
        money=view.getMoney();
        if (lives==0){
            gameOver();
        }
    }

//    @Override
    public void paintFrameInGame () {
        super.paintFrame();
        drawString("Money:"+String.valueOf(money),pfWidth()-10,10,1);
    }

    public void placeTower (String tower) {
//        setBGColor(JGColor.red);
        System.out.println("User wants to purchase " + tower);
        purchasing=true;
        towerToPurchase=tower;
    }

}
