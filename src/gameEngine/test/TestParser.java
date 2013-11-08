package gameEngine.test;

import static org.junit.Assert.*;
import java.io.File;
import java.util.Scanner;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONException;
import gameEngine.parser.JSONLibrary.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Harris
 * 
 * Test JSON parser
 *
 */
public class TestParser {
	private Parser parser;
	@Before
	public void setUp() throws Exception {
		Scanner sc = new Scanner(new File("src/gameEngine/test/TestJSON.json"));
		parser = new Parser(sc);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetNameOfGame() {
		String getNameOfGame = parser.getString("name");
		assertEquals(getNameOfGame, "Tower Destruction II");
	}
	
	@Test
	public void testGetNonexistenAttribute() {
		try {
			String getNameOfGame = parser.getString("totalGold");
			assert(false);
		} catch (JSONException e) {
			assert(true);
		}
	}
	
	@Test
	public void testGetBackgroundImage() {
			JSONObject map = parser.getJSONObject("map");
			String backgroundImage = (String) map.get("BGImage");
			assertEquals(backgroundImage, "image1.png");
	}
	
	@Test
	public void testGetPathLength() {
			JSONObject map = parser.getJSONObject("map");
			JSONArray array = map.getJSONArray("path");
			assertEquals(array.length(), 2);
	}
	
	@Test
	public void testGetPathElem1() {
			JSONObject map = parser.getJSONObject("map");
			JSONArray array = map.getJSONArray("path");
			JSONObject elem1 = array.getJSONObject(1);
			assertEquals(elem1.get("x"), 4);
	}

}
