package gameEngine.view.gameFrame;

import java.util.Collection;
import java.util.Map;
import javax.swing.JPanel;
import gameEngine.view.View;
import gameEngine.view.gameFrame.towerUpdater.TowerUpgrader;


/**
 * @author Alex Zhu
 * Swing Panel that holds the JGame canvas
 */
public class CanvasPanel extends JPanel {
    private Game game;

    public CanvasPanel (View view,
                        ItemPurchaser itemPurchaser,
                        TowerUpgrader utilities,
                        Collection<GameInitializable> gameInitializerItems,
                        Collection<GameUpdatable> gameUpdatables,
                        Map<String, KeyActivationItem> keyActivationItems) {
        game =
                new Game(view, itemPurchaser, utilities, gameInitializerItems, gameUpdatables,
                         keyActivationItems);
        this.add(game);
        view.sendEngine(game);
    }

    public void quitGame () {
        game.destroy();
    }

    public void endGame () {
        game.loseGame();
    }
}
