package gameEngine.view;

import gameEngine.model.TowerInfo;


public class CanvasPanel extends Panel {
    Game game;

    public CanvasPanel () {
        game = new Game();
        this.add(game);
    }



    @Override
    public void placeTower (TowerInfo towerInfo) {
        game.placeTower(towerInfo.getName());

    }

}
