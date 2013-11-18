package gameEngine.view.gameFrame.menu;

import gameEngine.controller.Controller;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


public class RestartGameMenuItem extends AbstractAction {
    private View view;

    public RestartGameMenuItem (View view) {

        super(StyleConstants.resourceBundle.getString("RestartGameFileMenu"));
        this.view = view;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        // controller.restart();

    }

}
