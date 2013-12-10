package gameEngine.view.gameFrame.menu;

import gameAuthoring.MainPanel;
import gameEngine.controller.Controller;
import gameEngine.view.gameFrame.GameFrame;

public class MenuActionsCreator implements MenuActionsFactory {
    
    public MenuActionsCreator(){
        
    }
    @Override
    public MenuAction createMenuAction (String menuOption) {
        
        if (menuOption.equals("endGame")) { return new MenuAction() {

            @Override
            public void executeAction () {
                gameFrame.endGame();

            }

        }; }
        if (menuOption.equals("selectNewGame")) { return new MenuAction() {

            @Override
            public void executeAction () {
                controller.startGame();
                gameFrame.endGame();
                gameFrame.quitGame();
                gameFrame.dispose();
                gameFrame = new GameFrame(controller, view);
                controller = new Controller();
                initializationFrame.showFrame();

            }

        }; }
        if (menuOption.equals("mainMenu")) { return new MenuAction() {

            @Override
            public void executeAction () {
                controller.startGame();
                gameFrame.endGame();
                gameFrame.quitGame();
                gameFrame.dispose();
                gameFrame = new GameFrame(controller, view);

                MainPanel panel = new MainPanel();
                panel.show();

            }

        }; }
        return new MenuAction() {

            @Override
            public void executeAction () {

            }

        };
    }

}
