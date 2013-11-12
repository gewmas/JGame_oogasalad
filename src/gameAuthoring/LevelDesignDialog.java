package gameAuthoring;

import gameAuthoring.JSONObjects.EnemyJSONObject;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;


public class LevelDesignDialog extends JDialog {

    private LevelDesignTab myLevelDesignTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    private int myNumWaves;
    private Map<Integer, String> myEnemiesPerWave = new HashMap<Integer, String>();

    public LevelDesignDialog (LevelDesignTab levelDesignTab) {
        myLevelDesignTab = levelDesignTab;
        GameData gameData = myLevelDesignTab.getGameData();

        for (int i = 0; i < gameData.getEnemyList().length(); i++) {
            EnemyJSONObject enemy = (EnemyJSONObject) gameData.getEnemyList().get(i);
            String imagePath = enemy.getString("image");
            JLabel enemyIcon = new JLabel();
            Image enemyImage;
            try {
                enemyImage = ImageIO.read(getClass().getResource(imagePath));
                enemyIcon.setIcon(new ImageIcon(enemyImage));
                this.add(enemyIcon);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
