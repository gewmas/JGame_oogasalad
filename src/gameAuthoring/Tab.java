package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import java.util.Observable;
import javax.swing.JPanel;


public abstract class Tab extends Observable {

    protected GameData myGameData;

    public Tab (GameData gameData) {
        myGameData = gameData;
    }

    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }

    public GameData getGameData () {
        return myGameData;
    }

    public abstract void loadJSON (Parser p);

}
