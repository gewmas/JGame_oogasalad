package gameEngine.view.gameFrame;

import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.Panel;
import gameEngine.view.View;


public class CanvasPanel extends Panel {
    Game game;

    public CanvasPanel (View view, GameFrameMediator mediator) {
        game = new Game(view, mediator);
        this.add(game);
    }

    public void placeTower (PurchaseInfo towerInfo) {
        game.placeTower(towerInfo.getItemName());
    }

    public void endGame () {
        game.destroy();
    }

}
