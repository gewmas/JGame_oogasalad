package gameAuthoring;

import gameAuthoring.JSONObjects.EnemyJSONObject;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


public class WaveDesignDialog extends JDialog {

    private LevelDesignTab myLevelDesignTab;
    private LevelDesignPanel myLevelDesignPanel;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    private Map<String, Integer> myEnemiesPerWave = new HashMap<String, Integer>();
    private Map<JTextField, EnemyJSONObject> myTextFields =
            new HashMap<JTextField, EnemyJSONObject>();

    public WaveDesignDialog (LevelDesignTab levelDesignTab, LevelDesignPanel levelDesignPanel) {
        myLevelDesignTab = levelDesignTab;
        myLevelDesignPanel = levelDesignPanel;
        this.setLayout(new MigLayout("wrap 2, align center"));
        JButton createWaveButton = new JButton("Create Wave");
        JLabel enemyTypeTitle = new JLabel("Enemy Type");
        JLabel enemyNumberTitle = new JLabel("Number");
        this.add(enemyTypeTitle);
        this.add(enemyNumberTitle, "pad 0 30 10 40");
        GameData gameData = myLevelDesignTab.getGameData();
        for (int i = 0; i < gameData.getEnemyList().length(); i++) {
            EnemyJSONObject enemy = (EnemyJSONObject) gameData.getEnemyList().get(i);
            String imagePath = enemy.getString("image");
            JLabel enemyIcon = new JLabel();
            enemyIcon.setPreferredSize(new Dimension(50, 50));
            JTextField enemyNumber = new JTextField();
            enemyNumber.setPreferredSize(new Dimension(50, 20));
            myTextFields.put(enemyNumber, enemy);
            Image enemyImage;
            try {
                enemyImage =
                        ImageIO.read(new File(System.getProperties().getProperty("user.dir") +
                                              imagePath));
                enemyIcon.setIcon(new ImageIcon(enemyImage));
                this.add(enemyIcon);
                this.add(enemyNumber, "pad 0 30 10 40");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            this.add(createWaveButton, "span 2");
        }
        createWaveButton.addMouseListener(createWaveDesignListener(this));
    }

    public MouseAdapter createWaveDesignListener (final WaveDesignDialog levelDesignDialog) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                for (JTextField field : myTextFields.keySet()) {
                    EnemyJSONObject enemy = myTextFields.get(field);
                    myEnemiesPerWave.put(enemy.getString("id"), Integer.parseInt(field.getText()));
                    levelDesignDialog.setVisible(false);
                }
                myLevelDesignPanel.addWave(myEnemiesPerWave);
            }
        };
        return listener;
    }
}
