package gameEngine.test;

import static org.junit.Assert.*;
import gameEngine.factory.Factory;
import gameEngine.model.Model;
import gameEngine.parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import jgame.JGColor;
import jgame.platform.JGEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGrid extends JGEngine{

    @Before
    public void setUp () throws Exception {
        initGame();
        initCanvas();
        
    }

    @After
    public void tearDown () throws Exception {
    }

    @Test
    public void test () {
        fail("Not yet implemented");
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
//        new Model();

        try {
            File file = new File("src/gameEngine/test/testTowerEnemyBullet/mygame.json");
            Scanner scanner = new Scanner(file);
            Parser parser = new Parser(scanner);
            Factory factory = new Factory(parser);
            factory.grid().initialize();
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    public void doFrame () {
        moveObjects();
    }

    @Override
    public void paintFrame () {
    }

}
