package gameEngine.view.initialization;

import javax.swing.JFrame;
import gameEngine.view.Button;
import gameEngine.view.StyleConstants;


/**
 * Used to close iniitalization Frame
 * 
 * @author Lalita Maraj
 * 
 */
public class CancelButton extends Button {
    JFrame frame;

    public CancelButton (JFrame frame) {
        super(StyleConstants.resourceBundle.getString("Cancel"));
        this.frame = frame;

    }
    @Override
    /**
     * Closes initialization Frame when button is clicked
     */
    protected void mouseClickAction () {
        frame.dispose();
    }

}
