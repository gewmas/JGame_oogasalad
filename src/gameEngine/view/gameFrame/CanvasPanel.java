package gameEngine.view.gameFrame;

import java.util.Collection;
import javax.swing.JPanel;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.view.gameFrame.towerUpgrader.ItemOptionsDisplayer;


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
    
    // When we want to close the swing window, we must actively destroy the jgame object
    public void quitGame () {
        game.destroy();
    }
    
    // Simply stops the game but keeps the jgame window alive
    public void endGame () {
        game.loseGame();
    }
}
