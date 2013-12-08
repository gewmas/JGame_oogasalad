package gameEngine.view.initialization;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import gameEngine.view.StyleConstants;


/**
 * Used to close iniitalization Frame
 * 
 * @author Lalita Maraj
 * 
 */
public class CancelButton extends JButton {
    JFrame frame;

    public CancelButton (JFrame frame) {
        super(StyleConstants.resourceBundle.getString("Cancel"));
        this.frame = frame;
        addMouseAdapter();

    }


    /**
     * Creates and assings a mouse Adapter for button
     */
    private void addMouseAdapter(){
        addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent me) { 
                frame.dispose();
            } 
         
          }); 

    }

}
