package gameAuthoring;

import javax.swing.JPanel;


public class Tab {

    protected GameData myGameData;

    public Tab (GameData gameData) {
        myGameData = gameData;
    }

    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }

}
