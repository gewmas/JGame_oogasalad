package gameEngine.view.gameFrame;

import jgame.impl.JGEngineInterface;
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
        view.sendEngine(game);
    }

    public void placeTower (PurchaseInfo towerInfo) {
        game.placeTower(towerInfo);
    }

    public void quitGame () {
        game.destroy();
    }

    public void endGame () {
        game.endGame();
    }
}
