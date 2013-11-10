package gameEngine.view.menu;

import gameEngine.controller.Controller;
import gameEngine.view.StyleConstants;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


public class RestartGameMenuItem extends AbstractAction {
    private Controller controller;

    public RestartGameMenuItem (Controller controller) {

        super(StyleConstants.resourceBundle.getString("RestartGameFileMenu"));
        this.controller = controller;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        // controller.restart();

    }

}
