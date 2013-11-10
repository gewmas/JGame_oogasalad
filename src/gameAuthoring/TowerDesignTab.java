package gameAuthoring;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;


public class TowerDesignTab extends Tab {

    public TowerDesignTab () {
        getTab();
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        JButton button = new JButton("Create New Tower Type");
        button.addMouseListener(createNewTowerListener(this));
        panel.add(button);
        return panel;
    }

    public MouseAdapter createNewTowerListener (final TowerDesignTab towerDesignTab) {

        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                TowerDesignDialog towerDesignDialog = new TowerDesignDialog(towerDesignTab);
                towerDesignDialog.setSize(new Dimension(300, 350));
                towerDesignDialog.setVisible(true);
            }
        };
        return listener;
    }
}
