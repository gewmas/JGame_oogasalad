package gameEngine.factory;

import gameEngine.parser.Parser;

public class Factory {
	
	private Parser parser;
	private GridFactory gridFactory;
	public Factory(Parser parser) {
		this.parser = parser;
		gridFactory = new GridFactory(this.parser.getJSONObject("map"));
	}
	
	public void initialize() {
		gridFactory.initialize();
	}
	
	
}
