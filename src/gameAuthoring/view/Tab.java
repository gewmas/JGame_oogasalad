package gameAuthoring.view;

import gameAuthoring.modifiedSwingComponents.GradientPanel;
import gameEngine.parser.Parser;
import java.util.Observable;
import javax.swing.JPanel;


/**
 * @author Rebecca Lai & Susan Zhang
 *         Tab is the superclass of each DesignTab. Allows for common elements in all tabs to be
 *         reused.
 */
public abstract class Tab extends Observable {

    protected JPanel myMainPanel = new GradientPanel();
    protected JPanel myContentPanel = new JPanel();

    /**
     * Creates new Tab
     */
    public Tab () {
    }

    /**
     * Retrieve tab that can be placed into the main game authoring frame
     * 
     * @return tab that can be placed in main game authoring frame
     */
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }

    public abstract void loadJSON (Parser p);

}
