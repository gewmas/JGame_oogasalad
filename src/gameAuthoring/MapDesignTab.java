package gameAuthoring;

import javax.swing.JPanel;


public class MapDesignTab extends Tab {

    private MapDesignData myMapDesignData;

    public MapDesignTab (MapDesignData mapDesignData) {
        myMapDesignData = mapDesignData;
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }
}
