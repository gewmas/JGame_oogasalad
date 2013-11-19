package gameAuthoring;

import gameEngine.parser.Parser;
import javax.swing.JPanel;


public abstract class Tab {

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
    
    public abstract void loadJSON(Parser p);
        
    
}
