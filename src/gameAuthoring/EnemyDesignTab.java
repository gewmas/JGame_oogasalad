package gameAuthoring;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;


public class EnemyDesignTab extends Tab {

    public EnemyDesignTab () {

    }

    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        JButton button = new JButton("Create New Enemy Type");
        return panel;
    }
    
    public MouseAdapter createNewEnemyListener(final EnemyDesignTab enemyDesignTab){
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
            
            }
        };
        return listener;
        
    }

}
