package test;

import static org.junit.Assert.*;
import gameEngine.model.Tower;
import jgame.JGColor;
import jgame.platform.JGEngine;

import org.junit.Test;

public class testModel extends JGEngine{

	@Test
	public void test() {
		//		fail("Not yet implemented");
	}

	@Override
	public void initCanvas() {
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
	public void initGame() {
		defineMedia("mygame.tbl");

		setFrameRate( 60, 2 );

		Tower tower = new Tower(1,1,1,1,1, "tower", false, 100.0, 100.0, 1, "tower");
	}

	@Override
	public void doFrame( ){

	}

	@Override
	public void paintFrame( )
	{
		// nothing to do
		// the objects paint themselves
	}
}
