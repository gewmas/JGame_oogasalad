package gameEngine.test.testTowerEnemyBullet;

import static org.junit.Assert.*;
import gameEngine.model.Bullet;
import gameEngine.model.Enemy;
import gameEngine.model.Tower;
import jgame.JGColor;
import jgame.platform.JGEngine;
import org.junit.Test;

public class testTowerEnemyBullet extends JGEngine{

//	@Test
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
		Enemy enemy = new Enemy("enemy", false, 150.0, 150.0, 2, "enemy");
		Bullet bullet = new Bullet(enemy, "bullet", false, 50.0, 50.0, 3, "bullet");
	}

	@Override
	public void doFrame( ){
	    moveObjects();
	}

	@Override
	public void paintFrame( )
	{
		// nothing to do
		// the objects paint themselves
	}
}
