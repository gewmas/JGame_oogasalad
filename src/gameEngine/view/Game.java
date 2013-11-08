package gameEngine.view;

import jgame.JGColor;
import jgame.platform.JGEngine;


/**
 * @author lalitamaraj
 *         Displays the Game setting using JGEngine to facilitate
 *         graphics rendering
 */
public class Game extends JGEngine implements Colleague {

    public Game () {
        initEngineComponent(600, 600);

    }

    @Override
    public void initCanvas () {
        setCanvasSettings(30, 30, 10,
                          10, null, JGColor.black, null);
    }

    @Override
    public void initGame () {
        setFrameRate(30, 2);

    }

    @Override
    public void doFrame () {

    }

    @Override
    public void paintFrame () {
        super.paintFrame();

    }

    @Override
    public void addMediator (Mediator mediator) {
        // TODO Auto-generated method stub

    }

    @Override
    public void purchaseTower (String tower) {
        setBGColor(JGColor.red);
        System.out.println("User wants to purchase " + tower);

    }

    @Override
    public void displayInfo (String displayInfo) {
        // TODO Auto-generated method stub

    }

}
