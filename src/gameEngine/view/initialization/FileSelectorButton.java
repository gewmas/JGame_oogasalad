package gameEngine.view.initialization;

import java.io.File;
import javax.swing.JFileChooser;
import gameEngine.view.Button;
import gameEngine.controller.*;


public class FileSelectorButton extends Button {
    Controller controller;

    public FileSelectorButton (String label, Controller controller) {
        super(label);
        this.controller = controller;
  

    }
    @Override 
    protected void mouseClickAction() {
        JFileChooser chooser =
                new JFileChooser(System.getProperties().getProperty("user.dir"));

        int selected = chooser.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File file = new File(chooser.getSelectedFile().toString());
            // to do: check that it is a Json file
            controller.newGame(file);
        }

    }
   
}
