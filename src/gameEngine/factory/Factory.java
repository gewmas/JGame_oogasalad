package gameEngine.factory;

import gameEngine.model.Level;
import gameEngine.parser.Parser;

public class Factory {
    /**
     * 
     * @author Harris
     * 
     * General factory for initializing the environment.  
     * Every model object will have its own factory.
     * This factory will call the initialize() method on all of the model factories
     * ex. tower, enemy, bullet, and grid will have factory classes
     *
     */
	private Parser parser;
	private GridFactory gridFactory;
	private TowerFactory towerFactory;
	private EnemyFactory enemyFactory;
	
	public Factory(Parser parser, Level lvl) {
		this.parser = parser;
		towerFactory = new TowerFactory(this.parser.getJSONObject("towerType"), lvl);
		enemyFactory = new EnemyFactory(this.parser.getJSONObject("enemyType"), lvl);
		gridFactory = new GridFactory(this.parser);
	}
	
	public TowerFactory tower() 
	{
	    return towerFactory;
	}
	
	public EnemyFactory enemy() 
        {
            return enemyFactory;
        }
	
}
