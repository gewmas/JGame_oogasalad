package gameAuthoring;

import gameAuthoring.JSONObjects.LevelJSONObject;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class LevelDesignPanel extends JPanel {
    private LevelDesignTab myLevelDesignTab;
    private List<Map<String, Integer>> myWaves =
            new ArrayList<Map<String, Integer>>();
    private JScrollPane myCreatedWaves;
    private JPanel myScrollPanel;
    private int numWaves = 0;

    public LevelDesignPanel (LevelDesignTab levelDesignTab) {
        myLevelDesignTab = levelDesignTab;
        this.setLayout(new MigLayout("wrap 2"));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        myCreatedWaves = new JScrollPane(myScrollPanel);
        myCreatedWaves.setPreferredSize(new Dimension(300, 300));
        Border b = BorderFactory.createLoweredBevelBorder();
        this.setBorder(b);
        this.add(createLevelPanel());
        this.add(myCreatedWaves);
    }

    private JPanel createLevelPanel () {
        JPanel panel = new JPanel(new MigLayout("wrap 1"));
        JButton createWaveButton = new JButton("Add Wave");
        createWaveButton.addMouseListener(createWaveListener(myLevelDesignTab, this));
        JButton createLevelButton = new JButton("Create Level");
        createLevelButton.addMouseListener(createLevelListener(this));
        panel.add(createWaveButton);
        panel.add(createLevelButton);
        return panel;
    }

    public void addWave (Map<String, Integer> wave) {
        numWaves++;
        myScrollPanel.add(new JButton("Wave " + numWaves));
        myWaves.add(wave);
    }

    private MouseAdapter createLevelListener (final LevelDesignPanel levelDesignPanel) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                LevelJSONObject newLevel =
                        new LevelJSONObject(myLevelDesignTab.getLevel(),
                                            numWaves, myWaves);
                myLevelDesignTab.addLevel(newLevel);
                myWaves.clear();
                numWaves = 0;
                myScrollPanel.removeAll();
            }
        };
        return listener;
    }

    private MouseAdapter createWaveListener (final LevelDesignTab levelDesignTab,
                                             final LevelDesignPanel levelDesignPanel) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                WaveDesignDialog levelDesignDialog =
                        new WaveDesignDialog(levelDesignTab, levelDesignPanel);
                levelDesignDialog.setSize(new Dimension(300, 350));
                levelDesignDialog.setVisible(true);
            }
        };
        return listener;
    }

}
