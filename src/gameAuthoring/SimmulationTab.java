package gameAuthoring;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import gameEngine.parser.Parser;

/**
 * @author Lalita Maraj
 *
 */
public class SimmulationTab extends Tab {
    private  GameData gameData;
    public SimmulationTab (GameData gameData) {
        super(gameData);
        this.gameData = gameData;
    
        
    }

    @Override
    public void loadJSON (Parser p) {
        

    }
    
    @Override
    public JPanel getTab(){
        JPanel panel = new JPanel();
        JButton simmulate = new JButton("Simmulate!");
        simmulate.addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent me) { 
               gameData.createSimmulationFile();
            } 
         
          }); 
        panel.add(simmulate);
        return panel;
    }

}
