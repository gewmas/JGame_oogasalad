package gameAuthoring;

import javax.swing.JPanel;


public class EnemyDesignTab extends Tab {

    private EnemyDesignData myEnemyDesignData;

    public EnemyDesignTab (EnemyDesignData enemyDesignData) {
        myEnemyDesignData = enemyDesignData;
    }

    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }

}
