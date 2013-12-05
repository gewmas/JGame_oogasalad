package gameEngine.view.gameFrame;

import java.util.Collection;
import jgame.impl.JGEngineInterface;
import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.Panel;
import gameEngine.view.View;


public class CanvasPanel extends Panel {
    Game game;

    public CanvasPanel (View view,  ItemPurchaser itemPurchaser, Utilities utilities, Collection<GameInitializable> gameInitializerItems, Collection<GameUpdatable> gameUpdatables) {
        game = new Game(view,itemPurchaser, utilities,gameInitializerItems,gameUpdatables);
        this.add(game);
        view.sendEngine(game);
    }
//
//    public void placeTower (PurchaseInfo towerInfo) {
//        game.placeTower(towerInfo);
//    }

    public void quitGame () {
        game.destroy();
    }

    public void endGame () {
        game.endGame();
    }
}
