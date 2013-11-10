package gameAuthoring;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class EnemyDesignTab extends Tab {

    private JScrollPane myCreatedEnemies;
    private JPanel myScrollPanel;

    public EnemyDesignTab () {

    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        mainPanel.setPreferredSize(new Dimension(500, 500));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        JButton button = new JButton("Create New Enemy Type");
        button.addMouseListener(createNewEnemyListener(this));
        mainPanel.add(button, "pad 0 20 0 20");
        myCreatedEnemies = new JScrollPane(myScrollPanel);
        myCreatedEnemies.setPreferredSize(new Dimension(200, 400));
        mainPanel.add(myCreatedEnemies, "pad 30 60 10 40");
        return mainPanel;
    }

    public MouseAdapter createNewEnemyListener (final EnemyDesignTab enemyDesignTab) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                EnemyDesignDialog enemyDesignDialog = new EnemyDesignDialog(enemyDesignTab);
                enemyDesignDialog.setSize(new Dimension(300, 350));
                enemyDesignDialog.setVisible(true);
            }
        };
        return listener;

    }

    // public MouseAdapter createNewTowerListener (final EnemyDesignTab towerDesignTab) {
    // MouseAdapter listener = new MouseAdapter() {
    // @Override
    // public void mouseClicked (MouseEvent e) {
    // TowerDesignDialog towerDesignDialog = new TowerDesignDialog(towerDesignTab);
    // towerDesignDialog.setSize(new Dimension(300, 350));
    // towerDesignDialog.setVisible(true);
    // }
    // };
    // return listener;
    // }

}
