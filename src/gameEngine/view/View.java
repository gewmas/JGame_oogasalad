package gameEngine.view;

import java.io.File;
import javax.swing.JOptionPane;
import gameAuthoring.MainPanel;
import gameEngine.controller.Controller;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.view.gameFrame.GameFrame;
import gameEngine.view.gameFrame.menu.MenuAction;
import gameEngine.view.gameFrame.menu.MenuActionFactory;
import gameEngine.view.initialization.InitializationFrame;


/**
 * The main view class that orchestrates the sequence of events
 * for selecting a game, starting a game, selecting a new game, and ending a game.
 * This class serves as the interface to the controller as the front end.
 * No other front end elements are exposed to the rest of the game Engine.
 * 
 * 
 * Implements the MenuActionFactory interface to define the methods the
 * different menu items will call on
 * @author Lalita Maraj, Alex Zhu
 * 
 */
public class View implements MenuActionFactory {
    private GameFrame gameFrame;
    private InitializationFrame initializationFrame;
    private ControllerToViewInterface controller;

    public View (ControllerToViewInterface controller) {
        this.controller = controller;
        this.gameFrame = new GameFrame(controller, this);
        this.initializationFrame = new InitializationFrame(this);

    }

    /**
     * Prompts user for file to be read to start game
     */
    public void promptForFile () {
        initializationFrame.showFrame();
    }

    /**
     * Used to start the game
     */
    public void startJGame () {
        gameFrame.showGame();
    }

    public void newGame (File file) {
        try {
            controller.newGame(file);
            initializationFrame.setVisible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                                          ViewConstants.resourceBundle.getString("FileReadError"));
        }
    }

    public void endGame () {
        gameFrame.endGame();
    }

    private void selectNewGame () {
        controller.startGame();
        gameFrame.endGame();
        gameFrame.quitGame();
        gameFrame.dispose();
        gameFrame = new GameFrame(controller, this);
        controller = new Controller();
        initializationFrame.showFrame();
    }

    private void goToMainMenu () {
        controller.startGame();
        gameFrame.endGame();
        gameFrame.quitGame();
        gameFrame.dispose();
        gameFrame = new GameFrame(controller, this);

        MainPanel panel = new MainPanel();
        panel.show();
    }

    @Override
    public MenuAction createMenuAction (String menuAction) {
        if (menuAction.equals(MenuActionFactory.END_GAME)) { return new MenuAction() {

            @Override
            public void executeAction () {
                endGame();

            }

        }; }
        if (menuAction.equals(MenuActionFactory.SELECT_NEW_GAME)) { return new MenuAction() {

            @Override
            public void executeAction () {
                selectNewGame();

            }

        }; }
        if (menuAction.equals(MenuActionFactory.MAIN_MENU)) { return new MenuAction() {

            @Override
            public void executeAction () {
                goToMainMenu();

            }

        }; }
        return new MenuAction() {

            @Override
            public void executeAction () {

            }

        };
    }

}
