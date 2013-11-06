package gameEngine.test.testTowerEnemyBullet;

import static org.junit.Assert.*;
import java.util.List;
import gameEngine.model.Bullet;
import gameEngine.model.Enemy;
import gameEngine.model.Model;
import gameEngine.model.Tower;
import jgame.JGColor;
import jgame.platform.JGEngine;
import org.junit.Test;

/**
 * 
 * @author Yuhua
 * 
 * Test Tower Enemy and Bullet
 *
 */
public class testTowerEnemyBullet extends JGEngine{

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
		
		new Model();
	}

	@Override
	public void doFrame( ){
	    moveObjects();
	    
	    checkCollision(3, 2);
	    checkCollision(2, 3);
	}

	@Override
	public void paintFrame(){}
}
