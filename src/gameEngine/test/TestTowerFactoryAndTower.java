package gameEngine.test;

import static org.junit.Assert.*;
import gameEngine.constant.GameEngineConstant;
import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.DefaultTowerFactory;
import gameEngine.model.GameInfo;
import gameEngine.model.Model;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tower.Tower;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
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
import java.util.Map;
import java.util.Scanner;

import jgame.JGPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

/**
 * Tests TowerFacotry and how it create a Tower according to JSON file
 * @author Yuhua Mai
 * 
 * JSON file
 * "Tower": [
	{
	 "Type": "DefaultTower",
	 "Name" : "DefaultTower1",
	 "Image": "tower1",
	 "Damage": 3,
	 "Attack Speed": 0.8,
	 "Attack Mode": 1,
	 "Range": 100,	
	 "Cost": 50, 
	 "Sell Price":  50,
	 "Description": "Default tower will shoot enemy, Surprise!",
	 "Specialty" : 0,
	}
]
 */

public class TestTowerFactoryAndTower {
    private static final String jsonFile="/src/gameEngine/test/TestTowerJSON.json";
    
    private DefaultTowerFactory defaultTowerFactory;
    
    @Before
    public void setUp () throws Exception {
    	File file = new File("");
    	Scanner scanner = new Scanner(new File(file.getAbsolutePath()+jsonFile));
    	Parser parser = new Parser(scanner);
    	JSONArray jsonArray = parser.getJSONArray(GameEngineConstant.TOWER_TYPE);
    	JSONObject currTower = jsonArray.getJSONObject(0);
    	defaultTowerFactory = new DefaultTowerFactory(currTower);
    }

    @After
    public void tearDown () throws Exception {
       defaultTowerFactory = null;
    }

    @Test
    public void testTowerPurchase(){
    	int x = 0;
    	int y = 0;
    	Tower tower = defaultTowerFactory.create(x, y);
    	Map<String, String> infoMap = tower.getPurchaseInfo().getInfo();
    	assertEquals(infoMap.get("Name"), "DefaultTower1");
    }

}
