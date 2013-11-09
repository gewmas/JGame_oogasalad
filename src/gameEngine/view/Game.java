package gameEngine.view;

import gameEngine.controller.Controller;
import gameEngine.model.Grid;
import java.util.ArrayList;
import jgame.JGColor;
import jgame.platform.JGEngine;


/**
 * @author lalitamaraj
 *         Displays the Game setting using JGEngine to facilitate
 *         graphics rendering
 */
public class Game extends JGEngine {

    View view;
    boolean purchasing;
    
    public Game (View view) {
        initEngineComponent(600, 600);
        this.view=view;
    }

    @Override
    public void initCanvas () {
        //gameMap=controller.getGrid();
        //int width=gameMap.size();
        //int height=gameMap.get(0).size();
        int width=30;
        int height=30;
        setCanvasSettings(width, height, 10,
                          10, null, JGColor.black, null);
    }

    @Override
    public void initGame () {
        setFrameRate(30, 2);
        purchasing=false;
    }

    @Override
    public void doFrame () {
        if (purchasing && getMouseButton(1) && getMouseInside()) {
            view.PurchaseTower(getMousePosition)
        }
    }

    @Override
    public void paintFrame () {
        super.paintFrame();

    }

    public void purchaseTower (String tower) {
        setBGColor(JGColor.red);
        System.out.println("User wants to purchase " + tower);
        purchasing=true;
    }

}
