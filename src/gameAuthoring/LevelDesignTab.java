package gameAuthoring;

import gameAuthoring.JSONObjects.LevelJSONObject;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
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
    private Map<JButton, LevelDesignPanel> myLevelDesignPanels =
            new HashMap<JButton, LevelDesignPanel>();
    private LevelDesignPanel myLevelDesignPanel;
    private JPanel myMainPanel;

    public LevelDesignTab (GameData gameData) {
        super(gameData);
        numLevels = 1;
    }

    @Override
    public JPanel getTab () {
        myMainPanel = new JPanel(new MigLayout("wrap 1"));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        JLabel title = new JLabel("Level Design");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        myMainPanel.add(title);
        myLevelDesignPanel = new LevelDesignPanel(this);
        myMainPanel.add(myLevelDesignPanel);
        myCreatedLevels = new JScrollPane(myScrollPanel);
        Border b = BorderFactory.createLoweredBevelBorder();
        myCreatedLevels.setBorder(b);
        myCreatedLevels.setPreferredSize(new Dimension(440, 300));
        myMainPanel.add(myCreatedLevels);

        return myMainPanel;
    }

    public void addLevel (LevelJSONObject level, LevelDesignPanel levelPanel) {
        numLevels++;
        JButton levelButton = new JButton("Level " + numLevels);
        myLevelDesignPanels.put(levelButton, levelPanel);
        levelButton.addMouseListener(createLevelButtonListener(levelButton));
        myScrollPanel.add(levelButton);
        myGameData.addLevel(level);
        myMainPanel.remove(myLevelDesignPanel);
        myMainPanel.remove(myCreatedLevels);
        myLevelDesignPanel = new LevelDesignPanel(this);
        myMainPanel.add(myLevelDesignPanel);
        myMainPanel.add(myCreatedLevels);
        myLevelDesignPanel.setVisible(true);
    }

    public MouseAdapter createLevelButtonListener (final JButton button) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                System.out.println("Clicked");
                LevelDesignPanel level = myLevelDesignPanels.get(button);
                // level.add(new JButton("hello"));
                myMainPanel.remove(myLevelDesignPanel);
                myMainPanel.remove(myCreatedLevels);
                myLevelDesignPanel = level;
                myMainPanel.add(myLevelDesignPanel);
                myMainPanel.add(myCreatedLevels);
                myLevelDesignPanel.setVisible(true);
            }
        };
        return listener;
    }

    public int getLevel () {
        return numLevels;
    }

}
