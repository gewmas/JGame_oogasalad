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
		gridFactory = new GridFactory(this.parser);
		towerFactory = new TowerFactory(this.parser.getJSONArray("towerType"), lvl);
		enemyFactory = new EnemyFactory(this.parser.getJSONArray("enemyType"), lvl);
	}
	
	public void initializeAll() {
	    towerFactory.initialize();
	    enemyFactory.initialize();
	    gridFactory.initialize();
	}
	
	public TowerFactory tower() 
	{
	    return towerFactory;
	}
	
	public EnemyFactory enemy() 
        {
            return enemyFactory;
        }
	
	public GridFactory grid() {
	    return gridFactory;
	}
	
}
