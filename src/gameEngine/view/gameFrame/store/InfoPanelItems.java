package gameEngine.view.gameFrame.store;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class InfoPanelItems extends JComponent {
    private String displayContent;
    private String infoContent;
    public InfoPanelItems(String content, String infoContent){
        this.displayContent=content;
        this.infoContent = infoContent;
        addButtonMouseListener ();
    }
    
    public String toString(){
        return displayContent;
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

    }

    /**
     * Defines the behavior that will occur when the button
     * is entered. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseEnteteredAction () {

    }

    /**
     * Defines the behavior that will occur when the button
     * is released. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseReleasedAction () {
       

    }

    /**
     * Defines the behavior that will occur when the button
     * is pressed. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mousePressedAction () {
        

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
