package gameEngine.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class Button extends JButton {

    public Button (String label) {
        super(label);
        addButtonMouseListener();
    }
    protected void mouseClickAction () {
        
        
    }
    private void addButtonMouseListener () {
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {
               mouseClickAction();

            }



            



            @Override
            public void mousePressed (MouseEvent e) {

            }

            @Override
            public void mouseReleased (MouseEvent e) {

            }

            @Override
            public void mouseEntered (MouseEvent e) {

            }

            @Override
            public void mouseExited (MouseEvent e) {

            }

        });
    }

}
