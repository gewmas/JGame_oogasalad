package gameAuthoring;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFileChooser;


public class LevelDesignDialog extends JDialog {

    private LevelDesignTab myLevelDesignTab;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    private int myNumWaves;
    private Map<Integer, String> myEnemiesPerWave = new HashMap<Integer, String>();

    public LevelDesignDialog (LevelDesignTab levelDesignTab) {
        myLevelDesignTab = levelDesignTab;

    }

}
