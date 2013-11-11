package gameEngine.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import gameEngine.model.Tile;
import gameEngine.Constant.Constant;
import gameEngine.factory.Factory;
import gameEngine.model.Model;
import gameEngine.parser.Parser;
import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;


/**
 * 
 * @author Harris
 * 
 * Test Grid
 * 
 */
public class TestGrid extends JGEngine {
    
    public static void main (String[] args) {
        new TestGrid(new JGPoint(960, 700));
    }
    
    /** Application constructor. */
    public TestGrid (JGPoint size) {
        initEngine(size.x, size.y);
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
        
      try {
          File file = new File("src/gameEngine/test/mygame.json");
          Scanner scanner = new Scanner(file);
          Parser parser = new Parser(scanner);
          Factory factory = new Factory(parser);
          factory.grid().initialize();
          LinkedList<Tile> pathList = factory.grid().getPathList();
          for(Tile tile: pathList) {
              System.out.println("Path x=" + tile.getX() + " y=" + tile.getY());
          }
          
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
