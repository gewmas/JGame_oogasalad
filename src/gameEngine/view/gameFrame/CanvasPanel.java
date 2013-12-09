package gameEngine.view.gameFrame;

import java.util.Collection;
import javax.swing.JPanel;
import gameEngine.view.View;
import gameEngine.view.gameFrame.towerUpdrader.ItemOptionsDisplayer;


/**
 * @author Alex Zhu
 * Swing Panel that holds the JGame canvas
 */
@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {
    private Game game;

    public CanvasPanel (View view,
                        ItemPurchaser itemPurchaser,
                        ItemOptionsDisplayer utilities,
                        GameInitializable gameInitializerItems,
                        GameUpdatable gameUpdatables,
                        Collection<KeyActivationItem> keyActivationItems) {
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
