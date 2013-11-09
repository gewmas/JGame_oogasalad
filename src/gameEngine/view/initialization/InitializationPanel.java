package gameEngine.view.initialization;

import java.util.ResourceBundle;
import javax.swing.JLabel;
import gameEngine.view.Button;
import gameEngine.view.Panel;
import gameEngine.controller.*;


/**
 * @author lalitamaraj
 *         Panel that
 */
public class InitializationPanel extends Panel {

    public InitializationPanel (Controller controller, ResourceBundle resources) {
        super();
        JLabel message = new JLabel(resources.getString("Welcome"));
        add(message);
        Button cancelButton = new Button(resources.getString("Cancel"));
        Button selectorButton =
                new FileSelectorButton(resources.getString("SelectFile"), controller);
        add(cancelButton);
        add(selectorButton);
    }

}
