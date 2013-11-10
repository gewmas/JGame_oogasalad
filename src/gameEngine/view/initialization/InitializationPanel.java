package gameEngine.view.initialization;

import javax.swing.JLabel;
import gameEngine.view.Button;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.controller.*;


/**
 * @author Lalita Maraj
 *         Panel that contains the options to load a file
 *         to start a new game
 */

public class InitializationPanel extends Panel {

    public InitializationPanel (Controller controller, Button cancelButton) {
        super();

        JLabel message = new JLabel(StyleConstants.myResources.getString("Welcome"));
        add(message);

        Button selectorButton =
                new FileSelectorButton(controller);
        add(cancelButton);
        add(selectorButton);
    }

}
