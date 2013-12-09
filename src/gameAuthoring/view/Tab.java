package gameAuthoring.view;

import gameEngine.parser.Parser;
import java.util.Observable;
import javax.swing.JPanel;


public abstract class Tab extends Observable {

    public Tab () {
    }

    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }

    public abstract void loadJSON (Parser p);

}
