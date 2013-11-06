package gameEngine.test.testTowerEnemyBullet;

import org.junit.Test;
import gameEngine.model.Model;
import jgame.JGColor;
import jgame.platform.JGEngine;


/**
 * 
 * @author Yuhua
 * 
 *         Test Tower Enemy and Bullet
 * 
 */
public class testTowerEnemyBullet extends JGEngine {

    @Test
    public void test(){
        
    }
    
    @Override
    public void initCanvas () {
        setCanvasSettings(
                          1, // width of the canvas in tiles
                          1, // height of the canvas in tiles
                          displayWidth(), // width of one tile
                          displayHeight(), // height of one tile
                          null,// foreground colour -> use default colour white
                          JGColor.white,// background colour -> use default colour black
                          null // standard font -> use default font
        );
    }

    @Override
    public void initGame () {
        defineMedia("mygame.tbl");
        setFrameRate(60, 2);

        new Model();
    }

    @Override
    public void doFrame () {
        moveObjects();

        checkCollision(3, 2);
        checkCollision(2, 3);
    }

    @Override
    public void paintFrame () {
    }
}
