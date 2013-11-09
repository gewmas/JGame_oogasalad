package gameEngine.view.initialization;

import java.io.File;
import javax.swing.JFileChooser;
import gameEngine.view.Button;
import gameEngine.view.StyleConstants;
import gameEngine.controller.*;


/**
 * Button that allows initiates
 * File selection to begin a new game
 * 
 * @author Lalita Maraj
 * 
 */
public class FileSelectorButton extends Button {
    Controller controller;

    /**
     * @param controller facilitates communication between view and model
     */
    public FileSelectorButton (Controller controller) {
        super(StyleConstants.myResources.getString("SelectFile"));
        this.controller = controller;

    }

    @Override
    /** Populates a File Chooser
     * and sends string name of selected file to the controller
     *
     */
    protected void mouseClickAction () {
        JFileChooser chooser =
                new JFileChooser(System.getProperties().getProperty("user.dir"));

        int selected = chooser.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File file = new File(chooser.getSelectedFile().toString());
            // to do: check that it is a Json file
            controller.newGame(file);
        }

    }

}
