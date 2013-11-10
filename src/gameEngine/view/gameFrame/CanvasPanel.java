package gameEngine.view.gameFrame;

import gameEngine.controller.Controller;
import gameEngine.model.TowerInfo;
import gameEngine.view.Panel;


public class CanvasPanel extends Panel {
    Game game;

    public CanvasPanel (GameFrame view) {
        game = new Game(view);
        this.add(game);
    }

    @Override
    public void placeTower (TowerInfo towerInfo) {
        game.placeTower(towerInfo.getName());

    }

}
