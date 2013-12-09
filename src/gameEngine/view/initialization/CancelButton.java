package gameEngine.view.initialization;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import gameEngine.view.ViewConstants;


/**
 * Used to close iniitalization Frame
 * 
 * @author Lalita Maraj
 * 
 */
public class CancelButton extends JButton {
    JFrame frame;

    public CancelButton (JFrame frame) {
        super(ViewConstants.resourceBundle.getString("Cancel"));
        this.frame = frame;
        addMouseAdapter();

    }

    /**
     * Creates and assings a mouse Adapter for button
     */
    private void addMouseAdapter () {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent me) {
                frame.dispose();
            }

        });

    }

}
