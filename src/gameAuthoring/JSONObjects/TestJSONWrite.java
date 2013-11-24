package gameAuthoring.JSONObjects;

import java.awt.List;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
        //Testing map writing
        Collection<Point2D> pathList = new ArrayList<Point2D>();
        pathList.add(new Point2D.Double(0, 0));
        pathList.add(new Point2D.Double(0, 1));
        pathList.add(new Point2D.Double(1, 1));
        
        game.setMap("path.jpg", pathList);
        
        game.addBarrier(5, 2, "rock.jpg");
        
        //Testing wave writing
//        game.addWave("1", 5, 0.5, 7);
//        game.addWave("2", 1, 0.5, 3);


        // Testing enemy writing
//        game.addEnemy("1", 5, "enemy1", 2, 0.5);
//        game.addEnemy("2", 10, "runRight", 3, 0.1);

        game.addListData();
        System.out.println(game.toString(1));

    }

    public static void main (String[] args) {
        TestJSONWrite x = new TestJSONWrite();
        x.testWrite();
    }

}
