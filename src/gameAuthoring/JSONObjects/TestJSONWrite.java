package gameAuthoring.JSONObjects;

import gameAuthoring.GameData;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;


public class TestJSONWrite {
    GameData myGameData;
    Writer writer;

    public TestJSONWrite () {
        myGameData = new GameData();
    }

    public void testWrite () {
        writer = new PrintWriter(System.out);

        JSONObject container = new JSONObject();

        EnemyJSONObject testEnemy = new EnemyJSONObject("test", 1, "imgsrc", 2, 3);
        EnemyJSONObject testEnemy2 = new EnemyJSONObject("test2", 1, "imgsrc2", 2, 3);

        JSONArray list = new JSONArray();

        list.put(testEnemy);
        list.put(testEnemy2);
        container.put("enemyType", list);

        System.out.println(container.toString(1));

        testEnemy.write(writer);
    }

    public static void main (String[] args) {
        TestJSONWrite x = new TestJSONWrite();
        x.testWrite();
    }

}
