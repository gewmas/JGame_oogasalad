package gameEngine.view.gameFrame.menu;

import gameEngine.view.ViewConstants;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


public class EndGameMenuItem extends AbstractAction {
    MenuActions menuActions;

    public EndGameMenuItem (MenuActions menuActions) {

        super(ViewConstants.resourceBundle.getString("EndGameFileMenu"));

        this.menuActions = menuActions;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        menuActions.endGame();

    }

}
