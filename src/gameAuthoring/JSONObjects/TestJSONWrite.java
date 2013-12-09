package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;


public class TestJSONWrite {
    GameData myGameData;
    Writer writer;

    public TestJSONWrite () {
        myGameData = new GameData();
    }

    public void testWrite () {

        GameData game = new GameData();

        // Testing tower writing
        game.addTower(new TowerJSONObject("MultipleShootingTower", 
                                          "name",
                                          "image", 
                                          3, 
                                          3.0,
                                          2,
                                          5, 
                                          80, 
                                          300, 
                                          50, 
                                          "Test"));
        
        
        //Testing resource writing
        game.addImage("1", "grass.jpg");
        ArrayList<String> temp = new ArrayList<String>();
        temp.add("1");
        temp.add("2");
        game.addAnimation("run", temp);
        
        // Testing map writing
         Collection<Point2D> pathList = new ArrayList<Point2D>();
         pathList.add(new Point2D.Double(0, 0));
         pathList.add(new Point2D.Double(0, 1));
         pathList.add(new Point2D.Double(1, 1));
        
         game.setMap("path.jpg", pathList);
        
         game.addBarrier(5, 2, "rock.jpg");
         
         WaveJSONObject w = new WaveJSONObject("1", 5, 0.5, 7);

        // Testing wave writing
         //game.addWave("1", 5, 0.5, 7);
         game.addWave(w);
         game.addWave("2", 1, 0.5, 3);

        // Testing enemy writing
         game.addEnemy("1", 5, "enemy1", 2, 3, 0.5, "Haste");
         game.addEnemy("2", 10, "runRight", 3, 4, 0.1, "Light");

         System.out.println(game.toString(1));
    }

    public static void main (String[] args) {
        TestJSONWrite x = new TestJSONWrite();
        x.testWrite();

        
    }

}
