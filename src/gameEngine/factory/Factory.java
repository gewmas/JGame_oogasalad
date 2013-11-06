package gameEngine.factory;

import gameEngine.model.Level;
import gameEngine.parser.Parser;

public class Factory {
	
	private Parser parser;
	private GridFactory gridFactory;
	private TowerFactory towerFactory;
	private EnemyFactory enemyFactory;
	
	public Factory(Parser parser, Level lvl) {
		this.parser = parser;
		gridFactory = new GridFactory(this.parser.getJSONObject("map"));
		towerFactory = new TowerFactory(this.parser.getJSONObject("towerType"), lvl);
		enemyFactory = new EnemyFactory(this.parser.getJSONObject("enemyType"), lvl);
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
