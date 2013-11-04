package gameAuthoring;

import javax.swing.JPanel;


public class EnemyDesignTab extends Tab {

    public EnemyDesignTab (GameData gameData) {
        super(gameData);
    }

    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }

}
