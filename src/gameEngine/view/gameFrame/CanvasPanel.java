package gameEngine.view.gameFrame;

import java.util.Collection;
import javax.swing.JPanel;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.view.gameFrame.towerUpdrader.ItemOptionsDisplayer;


/**
 * @author Alex Zhu
 * Swing Panel that holds the JGame canvas
 */
@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {
    private Game game;

    public CanvasPanel (ControllerToViewInterface controller,
                        ItemPurchaser itemPurchaser,
                        ItemOptionsDisplayer utilities,
                        GameInitializable gameInitializerItems,
                        GameUpdatable gameUpdatables,
                        Collection<KeyActivationItem> keyActivationItems) {
        game =
                new Game(controller, itemPurchaser, utilities, gameInitializerItems, gameUpdatables,
                         keyActivationItems);
        this.add(game);
        controller.setJGEngine(game);
    }

    public void quitGame () {
        game.destroy();
    }

    public void endGame () {
        game.loseGame();
    }
}
