package gameEngine.model.warehouse;

import gameEngine.parser.Parser;

public class EnemyWarehouse implements Warehouse{

    private Parser parser;

    public EnemyWarehouse (Parser parser) {
        this.parser = parser;
    }

    @Override
    public void create (String name) {
        
    }

}
