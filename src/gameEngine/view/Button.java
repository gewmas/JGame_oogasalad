package gameEngine.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;


/**
 * @author lalitamaraj
 *         Button class that will be used to create
 *         instantiate buttons in GUI
 */
public class Button extends JButton {

    public Button (String label) {
        super(label);
        addButtonMouseListener();
    }

    /**
     * Defines the behavior that will occur when the button
     * is clicked. If Button class is extened,
     * this method should be overridden to add Mouse
     * Click behavior
     */
    protected void mouseClickAction () {
    }

    /**
     * Defines the behavior that will occur when the button
     * is exited. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseExitedAction () {
        // TODO Auto-generated method stub

    }

    /**
     * Defines the behavior that will occur when the button
     * is entered. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseEnteteredAction () {
        // TODO Auto-generated method stub

    }

    /**
     * Defines the behavior that will occur when the button
     * is released. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseReleasedAction () {
        // TODO Auto-generated method stub

    }

    /**
     * Defines the behavior that will occur when the button
     * is pressed. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mousePressedAction () {
        // TODO Auto-generated method stub

    }

    /**
     * Adds a mouse listener to the button and
     * defines behavior like mouseClickAction
     */
    private void addButtonMouseListener () {
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent e) {
                mouseClickAction();

            }

            @Override
            public void mousePressed (MouseEvent e) {
                mousePressedAction();
            }

            @Override
            public void mouseReleased (MouseEvent e) {
                mouseReleasedAction();
            }

            @Override
            public void mouseEntered (MouseEvent e) {
                mouseEnteteredAction();
            }

            @Override
            public void mouseExited (MouseEvent e) {
                mouseExitedAction();
            }

        });
    }

}
