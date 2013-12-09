package gameAuthoring.menuBar;

import java.io.File;
import javax.swing.JOptionPane;
import gameAuthoring.JSONObjects.GameData;
import gameEngine.controller.Controller;

public class Simulator {

    public void simulate(GameData gameData){
        File file = gameData.createSimmulationFile();
        file = new File("../oogasalad_FooBar/simmulation.json");
        if (file!= null){
            Controller controller = new Controller();
            try {
                controller.newGame(file);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Ooops our bad. We messed up and can't simmulate your file!");
            }
        }
        
    }
}
