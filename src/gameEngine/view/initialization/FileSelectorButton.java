package gameEngine.view.initialization;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JFileChooser;
import gameEngine.view.Button;
import gameEngine.view.View;
import gameEngine.view.StyleConstants;


/**
 * Button that allows initiates
 * File selection to begin a new game
 * 
 * @author Lalita Maraj
 * 
 */
public class FileSelectorButton extends Button {
    private View view;

    /**
     * @param controller facilitates communication between view and model
     */
    public FileSelectorButton (View view) {
        super(StyleConstants.resourceBundle.getString("SelectFile"));
        this.view = view;
        addMouseAdapter();

    }

    protected void selectFile () {
        JFileChooser chooser =
                new JFileChooser(System.getProperties().getProperty("user.dir"));

        int selected = chooser.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File file = new File(chooser.getSelectedFile().toString());
            view.newGame(file);
        }

    }

    private void addMouseAdapter(){
        addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent me) { 
                selectFile();
            } 
         
          }); 

    }

}
