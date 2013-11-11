package gameAuthoring;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class LevelDesignPanel extends JPanel {
    private LevelDesignTab myLevelDesignTab;
    private JScrollPane myCreatedWaves;
    private JPanel myScrollPanel;

    public LevelDesignPanel (LevelDesignTab levelDesignTab) {
        myLevelDesignTab = levelDesignTab;
        this.setLayout(new MigLayout("wrap 2"));
        myScrollPanel = new JPanel(new MigLayout("wrap 2, align center"));
        myCreatedWaves = new JScrollPane(myScrollPanel);
        myCreatedWaves.setPreferredSize(new Dimension(200, 300));
        this.add(createLevelPanel());
        this.add(myCreatedWaves);
    }

    private JPanel createLevelPanel () {
        JPanel panel = new JPanel(new MigLayout("wrap 1"));

        JButton createWaveButton = new JButton("Add Wave");
        createWaveButton.addMouseListener(createWaveListener());

        JButton createLevelButton = new JButton("Create Level");
        createLevelButton.addMouseListener(createLevelListener());

        panel.add(createWaveButton);
        panel.add(createLevelButton);
        return panel;
    }

    private MouseAdapter createLevelListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myScrollPanel.removeAll();
            }
        };
        return listener;
    }

    private MouseAdapter createWaveListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {

                myScrollPanel.add(new JLabel("test"));
                myScrollPanel.repaint();

            }
        };
        return listener;
    }

}
