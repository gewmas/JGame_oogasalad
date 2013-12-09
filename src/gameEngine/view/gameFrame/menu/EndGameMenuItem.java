package gameEngine.view.gameFrame.menu;

import gameEngine.view.StyleConstants;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


public class EndGameMenuItem extends AbstractAction {
    MenuActions menuActions;

    public EndGameMenuItem (MenuActions menuActions) {

        super(StyleConstants.resourceBundle.getString("EndGameFileMenu"));

        this.menuActions = menuActions;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        menuActions.endGame();

    }

}
