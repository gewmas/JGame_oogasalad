package gameAuthoring.view;

import gameAuthoring.modifiedSwingComponents.GradientPanel;
import gameEngine.parser.Parser;
import java.util.Observable;
import javax.swing.JPanel;


public abstract class Tab extends Observable {

    protected JPanel myMainPanel = new GradientPanel();
    protected JPanel myContentPanel = new JPanel();

    public Tab () {
    }

    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }

    public abstract void loadJSON (Parser p);

}
