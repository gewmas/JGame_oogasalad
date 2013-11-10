package gameEngine.view.initialization;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import gameEngine.view.Button;
import gameEngine.view.EngineView;
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
    private Controller controller;
    private EngineView engineView;
    private JFrame frame;

    /**
     * @param controller facilitates communication between view and model
     */
    public FileSelectorButton (Controller controller, EngineView engineView, JFrame frame) {
        super(StyleConstants.myResources.getString("SelectFile"));
        this.controller = controller;
        this.engineView = engineView;
        this.frame = frame;

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
           if (controller.newGame(file)){
               engineView.showGame();
               frame.dispose();
           }
        }

    }

}
