package gameEngine.view.gameFrame;

import java.util.Collection;
import java.util.Map;
import jgame.impl.JGEngineInterface;
import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.Panel;
import gameEngine.view.View;


public class CanvasPanel extends Panel {
    Game game;

    public CanvasPanel (View view,  ItemPurchaser itemPurchaser, Utilities utilities, Collection<GameInitializable> gameInitializerItems, Collection<GameUpdatable> gameUpdatables,Map<String, KeyActivationItem> keyActivationItems) {
        game = new Game(view,itemPurchaser, utilities,gameInitializerItems,gameUpdatables, keyActivationItems);
        this.add(game);
        view.sendEngine(game);
    }


    public void quitGame () {
        game.destroy();
    }

    public void endGame () {
        game.endGame();
    }
}
