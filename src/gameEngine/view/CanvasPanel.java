package gameEngine.view;

import gameEngine.model.TowerInfo;


public class CanvasPanel extends Panel implements Colleague {
    Game game;

    public CanvasPanel () {
        game = new Game();
        this.add(game);
    }

    @Override
    public void addMediator (Mediator mediator) {
        // TODO Auto-generated method stub

    }

    @Override
    public void placeTower (TowerInfo towerInfo) {
        game.purchaseTower(towerInfo.getName());

    }

}
