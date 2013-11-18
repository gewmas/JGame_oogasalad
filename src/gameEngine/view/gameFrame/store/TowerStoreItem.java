package gameEngine.view.gameFrame.store;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class TowerStoreItem extends JLabel {
    private static final Color HOVER_BUTTON_COLOR = Color.BLUE;
    private static final Color HOVER_TEXT_COLOR = Color.RED;
    private static final Color HOVER_EXIT_TEXT_COLOR = Color.BLACK;

    TowerStoreItem () {
        super();
        this.setSize(32, 32);
        this.setBorder(BorderFactory.createBevelBorder(1));
        ImageIcon icon = new ImageIcon("src/gameEngine/view/gameFrame/store/smile.png");
        this.setIcon(icon);
        addButtonMouseListener();
    }

    /**
     * Defines the behavior that will occur when the button
     * is clicked. If Button class is extened,
     * this method should be overridden to add Mouse
     * Click behavior
     */
    protected void mouseClickAction () {
        System.out.println("she clicked");
    }

    /**
     * Defines the behavior that will occur when the button
     * is exited. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseExitedAction () {

    }

    /**
     * Defines the behavior that will occur when the button
     * is entered. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseEnteteredAction () {
        this.setBackground(HOVER_BUTTON_COLOR);
        this.setForeground(HOVER_TEXT_COLOR);
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
