package gameEngine.test.testTowerEnemyBullet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;
import gameEngine.factory.Factory;
import gameEngine.model.Model;
import gameEngine.parser.Parser;
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
        
        Scanner scanner;
        try {
            scanner = new Scanner(new File(System.getProperty("user.dir") + "/gameEngine/test/testTowerEnemyBullet/mygame.json"));
            Parser parser = new Parser(scanner);
            new Model(parser);
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
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
