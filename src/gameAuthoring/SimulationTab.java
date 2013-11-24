package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * @author Lalita Maraj
 * 
 */
public class SimulationTab extends Tab {

    private GameData gameData;

    public SimulationTab (GameData gameData) {
        super(gameData);
        gameData = gameData;

    }

    @Override
    public void loadJSON (Parser p) {

    }

    @Override
    public JPanel getTab () {
        JPanel panel = new GradientPanel();
        JButton simmulate = new JButton("Simulate!");
        simmulate.addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent me) {
                gameData.createSimmulationFile();
            }

        });
        panel.add(simmulate);
        return panel;
    }

}
