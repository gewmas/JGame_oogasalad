package gameEngine.view.initialization;

import javax.swing.JFrame;
import gameEngine.view.Button;
import gameEngine.view.StyleConstants;


public class CancelButton extends Button {
    JFrame frame;

    public CancelButton (JFrame frame) {
        super(StyleConstants.resourceBundle.getString("Cancel"));
        this.frame = frame;

    }

    protected void mouseClickAction () {
        frame.dispose();
    }

}
