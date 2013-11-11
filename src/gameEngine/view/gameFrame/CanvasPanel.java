package gameEngine.view.gameFrame;

import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.view.Panel;


public class CanvasPanel extends Panel {
    Game game;

    public CanvasPanel (GameFrame view) {
        game = new Game(view);
        this.add(game);
    }

    @Override
    public void placeTower (TowerFactory towerInfo) {
        game.placeTower(towerInfo.getName());

    }

}
