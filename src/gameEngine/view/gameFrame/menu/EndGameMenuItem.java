package gameEngine.view.gameFrame.menu;


import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


public class EndGameMenuItem extends AbstractAction {
    private View view;

    public EndGameMenuItem (View view) {

        super(StyleConstants.resourceBundle.getString("EndGameFileMenu"));
        this.view = view;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        view.endGame();

    }

}
