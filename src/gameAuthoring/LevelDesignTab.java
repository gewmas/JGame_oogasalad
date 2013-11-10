package gameAuthoring;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class LevelDesignTab extends Tab {

    private JScrollPane myCreatedLevels;
    private JPanel myScrollPanel;
    private int numLevels;

    public LevelDesignTab (GameData gameData) {
        super(gameData);
        numLevels = 0;
    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        LevelDesignPanel levelDesignPanel = new LevelDesignPanel(this);
        mainPanel.add(levelDesignPanel);
        myCreatedLevels = new JScrollPane(myScrollPanel);
        myCreatedLevels.setPreferredSize(new Dimension(200, 400));
        mainPanel.add(myCreatedLevels);

        return mainPanel;
    }

    
    public void addLevel (int numWaves){
        numLevels++;
        JPanel levelPanel = new JPanel(new MigLayout("wrap 2"));
        JLabel level = new JLabel(numLevels + "");
        levelPanel.add(level);
        myCreatedLevels.add(levelPanel);
    }
}
