package gameEngine.test;

import static org.junit.Assert.*;
import gameEngine.constant.GameEngineConstant;
import gameEngine.controller.Controller;
import gameEngine.model.GameInfo;
import gameEngine.model.Model;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.View;
import gameEngine.view.gameFrame.CanvasPanel;
import gameEngine.view.gameFrame.Game;
import gameEngine.view.gameFrame.GameFrame;
import gameEngine.view.gameFrame.ItemPurchaser;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.lang.reflect.*;
import java.util.List;
import jgame.JGPoint;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

/**
 * Tests the pipeline between the model and the view by initializing the game, checking the
 * GameInfo and purchasing a tower and checking it's information.
 * @author Alex Zhu azz
 */
public class TestGamePipeline {
    private static final String JSONURL="/src/gameEngine/test/ViewTestJSON.json";
    
    View view;
    Model model;
    Controller controller;
    
    //Gets the specified child member variable from the parent class
    public Object getMemberVariable(Object parent, String child){
        Field field;
        Object childReturn=null;
        try {
            field = parent.getClass().getDeclaredField(child);
            field.setAccessible(true);
            childReturn=field.get(parent);
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return childReturn;
    }

    //Set up the game, extract the view and model
    @Before
    public void setUp () throws Exception {
        controller=new Controller();
        view=(View) getMemberVariable(controller,"view");
        model=(Model) getMemberVariable(controller,"model");
        
        File file = new File("");
        controller.newGame(new File(file.getAbsolutePath()+JSONURL));
    }

    @After
    public void tearDown () throws Exception {
        view.endGame();        
        view=null;
        controller=null;
        model=null;
    }



    // All tests need to be in one method, as each new test method will create a new
    // instance of jgame, but jgame is static so later tests will not work.
    @Test
    public void testTowerPurchase(){
        try {
            // Check that the JSON was parsed correctly
            GameInfo gameInfo=controller.getGameInfo();
            assertEquals(gameInfo.getMyName(),"Tower Destruction II");
            assertEquals(gameInfo.getGold(),500);
            assertEquals(gameInfo.getLife(),10);
            assertEquals(gameInfo.getMyGoldName(),"Money");
            assertEquals(gameInfo.getMyLivesName(),"Lives");
            assertEquals(gameInfo.getBGImage(),"woods_background");
            assertEquals(gameInfo.getIsWin(),false);
            
            //Get the necessary child objects
            GameFrame gameFrame=(GameFrame) getMemberVariable(view,"gameFrame");
            ItemPurchaser itemPurchaser=(ItemPurchaser) getMemberVariable(gameFrame,"itemPurchaser");
            CanvasPanel canvasPanel=(CanvasPanel) getMemberVariable(gameFrame,"canvasPanel");
            Game game=(Game) getMemberVariable(canvasPanel,"game");

            //Wait for the game to start
            Robot robot = new Robot();
            robot.delay(200);
            
            game.startGame();

            //Test purchasing a tower
            PurchaseInfo defaultInfo=new PurchaseInfo("DefaultTower", "DefaultTower1", "tower1","",50);
            itemPurchaser.selectPurchaseTower(defaultInfo);
            boolean bought=itemPurchaser.purchaseTower(new JGPoint(100,73));
            assertEquals(bought,true);

            //Wait for jgame to process the new object
            robot.delay(200);
            
            //Test checking tower status
            PurchaseInfo boughtTower=controller.getTowerInfo(100,73);
            assertEquals(boughtTower.getInfo().get(GameEngineConstant.PURCHASE_INFO_NAME),"DefaultTower1");
        }
        catch (SecurityException | IllegalArgumentException | AWTException e) {
            e.printStackTrace();
        }
    }

}
