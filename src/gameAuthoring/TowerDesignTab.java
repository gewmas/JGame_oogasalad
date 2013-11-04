package gameAuthoring;

import javax.swing.JPanel;


public class TowerDesignTab extends Tab {

    public TowerDesignTab (GameData gameData) {
        super(gameData);
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }
}
