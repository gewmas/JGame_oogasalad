package gameEngine.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		String getNameOfGame = parser.getValue("name");
		assertEquals(getNameOfGame, "Tower Destruction II");
	}
	
	@Test
	public void testGetNonexistenAttribute() {
		try {
			String getNameOfGame = parser.getValue("totalGold");
			assert(false);
		} catch (JSONException e) {
			assert(true);
		}
	}

}
