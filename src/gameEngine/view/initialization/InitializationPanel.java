package gameEngine.view.initialization;

import javax.swing.JFrame;
import javax.swing.JLabel;
import gameEngine.view.Button;
import gameEngine.view.EngineView;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.controller.*;


/**
 * @author Lalita Maraj
 *         Panel that contains the options to load a file
 *         to start a new game
 */

public class InitializationPanel extends Panel {

    public InitializationPanel (Controller controller,
                                JFrame initializationFrame,
                                EngineView engineView) {
        super();

        JLabel message = new JLabel(StyleConstants.myResources.getString("Welcome"));
        add(message);

        Button selectorButton =
                new FileSelectorButton(controller, engineView, initializationFrame);
        Button cancelButton = new CancelButton(initializationFrame);
        add(cancelButton);
        add(selectorButton);
    }

}
