package gameEngine.view;

import gameEngine.controller.Controller;
import gameEngine.model.TowerInfo;


public class CanvasPanel extends Panel {
    Game game;

    public CanvasPanel (View view) {
        game = new Game(view);
        this.add(game);
    }



    @Override
    public void placeTower (TowerInfo towerInfo) {
        game.purchaseTower(towerInfo.getName());

    }

}
