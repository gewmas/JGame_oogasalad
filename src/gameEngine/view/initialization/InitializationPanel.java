package gameEngine.view.initialization;

import javax.swing.JLabel;
import gameEngine.view.Button;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.controller.*;


/**
 * @author lalitamaraj
 *         Panel that contains the options to load a file
 *         to start a new game
 */

public class InitializationPanel extends Panel {

    public InitializationPanel (Controller controller) {
        super();
        JLabel message = new JLabel(StyleConstants.myResources.getString("Welcome"));
        add(message);
        Button cancelButton = new Button(StyleConstants.myResources.getString("Cancel"));
        Button selectorButton =
                new FileSelectorButton(controller);
        add(cancelButton);
        add(selectorButton);
    }

}
