package gameAuthoring.menuBar;

import java.io.File;
import gameAuthoring.JSONObjects.GameData;
import gameEngine.controller.Controller;

public class Simulator {

    public void simulate(GameData gameData){
        File file = gameData.createSimmulationFile();
        file = new File("../oogasalad_FooBar/officialJSON.json");
        if (file!= null){
            Controller controller = new Controller();
            try {
                controller.newGame(file);
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
}
