package gameAuthoring;

import javax.swing.JPanel;


public class MapDesignTab extends Tab {

    public MapDesignTab () {

    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        Grid grid = new Grid(5, 5);
        panel.add(grid);
        return panel;
    }
}
