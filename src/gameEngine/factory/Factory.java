package gameEngine.factory;

import gameEngine.parser.Parser;


public class Factory {
    /**
     * 
     * @author Harris
     * 
     *         General factory for initializing the environment.
     *         Every model object will have its own factory.
     *         This factory will call the initialize() method on all of the model factories
     *         ex. tower, enemy, bullet, and grid will have factory classes
     * 
     */

    private Parser parser;
    private GridFactory gridFactory;

    public Factory (Parser parser) {
        // Note, this was only used to test the GridFactory class
        this.parser = parser;
        gridFactory = new GridFactory(this.parser);
    }

    public void initializeAll () {
        gridFactory.initialize();
    }

    public GridFactory grid () {
        return gridFactory;
    }

}
