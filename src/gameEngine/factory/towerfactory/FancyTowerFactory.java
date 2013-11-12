package gameEngine.factory.towerfactory;

import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;


public class FancyTowerFactory extends DefaultTowerFactory {

    public FancyTowerFactory (JSONObject currTower) {
        super(currTower);
    }

    @Override
    public Tower create () {
        return null;
    }
}
