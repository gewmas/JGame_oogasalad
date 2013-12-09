package gameEngine.test.testTowerEnemyBullet;

import java.io.File;
import gameEngine.constant.GameEngineConstant;
import gameEngine.model.Model;
import jgame.JGColor;
import jgame.JGPoint;
import jgame.platform.JGEngine;


/**
 * 
 * @author Yuhua
 * 
 * This test play an important part for BackEnd to test visually when FrontEnd hadn't finished View.
 * Now this test is deprecated.
 * 
 */
public class testTowerEnemyBullet extends JGEngine {

    // @Test
    // public void test(){
    //
    // }

    // public static void main(String[] args) {new testTowerEnemyBullet();}
    //
    // public testTowerEnemyBullet(){initEngineApplet(); }
    // public testTowerEnemyBullet(JGPoint size){initEngine(size.x,size.y); }

    // Jiaran edited. Using this, one can run it as application instead of applet.
    public static void main (String[] args) {
        new testTowerEnemyBullet(new JGPoint(960, 700));
    }

    /** Application constructor. */
    public testTowerEnemyBullet (JGPoint size) {
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
                          JGColor.green,// background colour -> use default colour black
                          null // standard font -> use default font
        );
    }

    @Override
    public void initGame () {
        defineMedia("mygame.tbl");
        setFrameRate(60, 2);

        Model model = new Model();
        try {
            //Yuhua, change of newGame signature
//            model.newGame(new File(System.getProperty("user.dir") +
//                                   "/src/gameEngine/test/testTowerEnemyBullet/mygame.json"));
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.startGame();
    }

    @Override
    public void doFrame () {
        moveObjects();

        checkCollision(GameEngineConstant.BULLET_CID, GameEngineConstant.ENEMY_CID);
        checkCollision(GameEngineConstant.ENEMY_CID, GameEngineConstant.BULLET_CID);
    }

    @Override
    public void paintFrame () {
    }
}
