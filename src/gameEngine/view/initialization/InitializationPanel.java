package gameEngine.view.initialization;

import javax.swing.JFrame;
import javax.swing.JLabel;
import gameEngine.view.Button;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrame;


/**
 * @author Lalita Maraj
 *         Panel that contains the options to load a file
 *         to start a new game
 */

public class InitializationPanel extends Panel {

    public InitializationPanel (JFrame initializationFrame, View view) {
        super();

        JLabel message = new JLabel(StyleConstants.resourceBundle.getString("Welcome"));
        add(message);

        Button selectorButton = new FileSelectorButton(view);
        Button cancelButton = new CancelButton(initializationFrame);
        add(cancelButton);
        add(selectorButton);
    }

}
