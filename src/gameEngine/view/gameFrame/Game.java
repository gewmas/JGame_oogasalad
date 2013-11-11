package gameEngine.view.gameFrame;

import gameEngine.controller.Controller;
import gameEngine.model.Tile;
import gameEngine.model.tower.Tower;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import jgame.Highscore;
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

    private int WIDTH = 600;
    private int HEIGHT = 600;

    private GameFrame gameFrame;
    private boolean purchasing;
    private String towerToPurchase;

    public Game (GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        initEngineComponent(WIDTH, HEIGHT);
    }

    @Override
    public void initCanvas () {
        Dimension size = gameFrame.getGameSize();
        setCanvasSettings(size.width, size.height, WIDTH / size.width,
                          HEIGHT / size.height, null, JGColor.white, null);
    }

    @Override
    public void initGame () {
        setFrameRate(30, 2);
        initial_lives = gameFrame.getLives();
        lives = gameFrame.getLives();
        score = gameFrame.getMoney();
        String bgImage = "space_background.jpg";
        defineImage("background", "bg", 256, bgImage, "-",300,300);
        setBGImage("background");
        purchasing = false;
        setHighscores(10, new Highscore(0, "aaa"), 3);
        startgame_ingame = true;
        List<Tile> pathList=gameFrame.getPath();
        int tileCount=0;
        for (Tile tile:pathList){
            defineImage("tile"+String.valueOf(tileCount),"#"+String.valueOf(tileCount),256,tile.getPathImage(),"-");
            JGPoint tilePos=getTileIndex(tile.getX(),tile.getY());
            setTile(tilePos.x,tilePos.y,"#"+String.valueOf(tileCount));
            tileCount++;
        }
    }

    public void doFrameInGame () {
        checkCollision(0, 0);
        checkUserInteractions();
        updateGameStats();
        gameFrame.updateStoreStatus();
        if (getKey(KeyEsc)) {
            clearKey(KeyEsc);
            lives = 0;
        }
    }

    /**
     * Checks if the user has clicked on the screen, and if so, either tries to
     * buy a tower at the clicked tile position or tries to get the info of
     * the tower at the clicked tile position
     */
    public void checkUserInteractions () {
        if (getMouseButton(1) && getMouseInside()) {
            clearMouseButton(1);
            JGPoint mousePosition = getMousePos();
            JGPoint tilePosition = getTileIndex(mousePosition.x, mousePosition.y);
            if (purchasing) {
                gameFrame.buyTower(tilePosition.x, tilePosition.y, towerToPurchase);
                purchasing = false;
                System.out.format("Buying tower at: %d,%d\n", tilePosition.x, tilePosition.y);
            }
            else {
                Tower tower=gameFrame.getTowerInfo(tilePosition.x, tilePosition.y);
                if (tower==null) System.out.println("No tower here");
                System.out.format("Checking tower at: %d,%d\n", tilePosition.x, tilePosition.y);
            }
        }
    }

    /**
     * Updates the number of lives and money remaining in the game
     */
    public void updateGameStats () {
        lives = gameFrame.getLives();
        score = gameFrame.getMoney();
    }

    @Override
    public void paintFrame () {
        super.paintFrame();
//        drawString("Money " + String.valueOf(sc), pfWidth() - 10, 10, 1);
    }

    /**
     * Indicates that the user wants to buy a tower
     */
    public void placeTower (String tower) {
        // setBGColor(JGColor.red);
        System.out.println("User wants to purchase " + tower);
        purchasing = true;
        towerToPurchase = tower;
    }

}
