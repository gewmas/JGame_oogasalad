package gameEngine.view.initialization;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import gameEngine.view.View;
import gameEngine.view.ViewConstants;


/**
 * Button that allows initiates
 * File selection to begin a new game
 * 
 * @author Lalita Maraj
 * 
 */
@SuppressWarnings("serial")
public class FileSelectorButton extends JButton {
    private View view;

    /**
     * @param controller facilitates communication between view and model
     */
    public FileSelectorButton (View view) {
        super(ViewConstants.resourceBundle.getString("SelectFile"));
        this.view = view;
        addMouseAdapter();

    }

    /**
     * Displays File choser and uses selected file to start a new game
     */
    private void selectFile () {
        JFileChooser chooser =
                new JFileChooser(System.getProperties().getProperty("user.dir"));

        int selected = chooser.showOpenDialog(null);
        if (selected == JFileChooser.APPROVE_OPTION) {
            File file = new File(chooser.getSelectedFile().toString());
            view.newGame(file);
        }

    }

    /**
     * Creates and assigns a mouse adapter
     */
    private void addMouseAdapter () {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent me) {
                selectFile();
            }

        });

    }

}
