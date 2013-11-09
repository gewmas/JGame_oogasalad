package gameAuthoring;

import javax.swing.JPanel;


public class TowerDesignTab extends Tab {

    private TowerDesignData myTowerDesignData;

    public TowerDesignTab (TowerDesignData towerDesignData) {
        myTowerDesignData = towerDesignData;
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }
}
