package gameEngine.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import gameEngine.Constant.Constant;
import gameEngine.factory.Factory;
import gameEngine.model.Model;
import gameEngine.model.tile.Tile;
import gameEngine.parser.Parser;
import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;

/**
 * 
 * @author Harris
 * 
 *         Test Grid
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
        Model model = new Model();
        try {
            model.newGame(new File("officialJSON.json"));
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        LinkedList<Tile> pathList = model.getPathList();
        for (Tile tile : pathList) {
            System.out.println("Path x=" + tile.getX() + " y=" + tile.getY());
            System.out.println("Center x=" + tile.getCenterX() + " center y=" + tile.getCenterY());
            System.out.println("img path is " + tile.getPathImage());
        }
        System.out.println("tile is on path? " + model.getTile(405,  165).hasPath());//should be on path
        System.out.println("tile is on path? " + model.getTile(405,  305).hasPath());//should not be on path
        
        System.out.println("tile has barrier? " + model.getTile(160, 90).hasStaticBarrier());//should have barrier
        System.out.println("tile has barrier? " + model.getTile(405,  305).hasStaticBarrier());//should not have barrier
    }

    @Override
    public void doFrame () {
        moveObjects();
    }

    @Override
    public void paintFrame () {
    }
}
