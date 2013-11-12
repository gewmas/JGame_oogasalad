package gameAuthoring;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class LevelDesignTab extends Tab {

    private JScrollPane myCreatedLevels;
    private JPanel myScrollPanel;
    private int numLevels;
    private Collection<Map<String, Integer>> myWaves =
            new ArrayList<Map<String, Integer>>();

    public LevelDesignTab (GameData gameData) {
        super(gameData);
        numLevels = 0;
    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 1"));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        JLabel title = new JLabel("Miscellaneous");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(title);
        LevelDesignPanel levelDesignPanel = new LevelDesignPanel(this);
        mainPanel.add(levelDesignPanel);
        myCreatedLevels = new JScrollPane(myScrollPanel);
        Border b = BorderFactory.createLoweredBevelBorder();
        myCreatedLevels.setBorder(b);
        myCreatedLevels.setPreferredSize(new Dimension(440, 300));
        mainPanel.add(myCreatedLevels);

        return mainPanel;
    }

    public void addLevel () {
        numLevels++;
        JButton levelButton = new JButton(numLevels + "");
        myScrollPanel.add(levelButton);
    }

    // public void

    public void addWave (Map<String, Integer> wave) {
        myWaves.add(wave);
    }
}
