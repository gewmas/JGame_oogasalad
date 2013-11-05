package gameAuthoring;

import javax.swing.JPanel;


public class LevelDesignTab extends Tab {

    private LevelDesignData myLevelDesignData;

    public LevelDesignTab (LevelDesignData levelDesignData) {
        myLevelDesignData = levelDesignData;
    }

    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }
}
