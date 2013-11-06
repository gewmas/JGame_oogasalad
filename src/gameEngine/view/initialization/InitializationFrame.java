package gameEngine.view.initialization;

import java.util.ResourceBundle;
import gameEngine.view.Frame;
import gameEngine.view.View;
import gameEngine.controller.*;

public class InitializationFrame extends Frame {

    public InitializationFrame(Controller controller, ResourceBundle resource){
        super();
        getContentPane().add(new InitializationPanel(controller, resource));
        pack();
        setVisible(true);
    }
}
