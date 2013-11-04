package gameAuthoring;

import javax.swing.JPanel;


public class BasicInfoTab extends Tab {

    public BasicInfoTab (GameData gameData) {
        super(gameData);
        // TODO Auto-generated constructor stub
    }

    // TO DO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }
}
