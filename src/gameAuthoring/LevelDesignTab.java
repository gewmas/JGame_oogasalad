package gameAuthoring;

import javax.swing.JPanel;


public class LevelDesignTab extends Tab {

    public LevelDesignTab (GameData gameData) {
        super(gameData);
    }

    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }
}
