package gameEngine.test;

import static org.junit.Assert.*;
import gameEngine.factory.Factory;
import gameEngine.model.GameInfo;
import gameEngine.model.Level;
import gameEngine.parser.Parser;
import java.io.File;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGrid {

    @Before
    public void setUp () throws Exception {
        File file = new File("src/gameEngine/test/testTowerEnemyBullet/mygame.json");
        Scanner scanner = new Scanner(file);
        Parser parser = new Parser(scanner);
        GameInfo gameinfo = new GameInfo(1,2,3);
        Level level = new Level(gameinfo, parser);
        Factory factory = new Factory(parser, level);
        factory.grid().initialize();
        
    }

    @After
    public void tearDown () throws Exception {
    }

    @Test
    public void test () {
        fail("Not yet implemented");
    }

}
