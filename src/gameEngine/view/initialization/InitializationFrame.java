package gameEngine.view.initialization;

import javax.swing.JFrame;
import gameEngine.view.View;


/**
 * Frame that prompts user to select a file to load to
 * begin a game
 * 
 * @author Lalita Maraj
 * 
 */
public class InitializationFrame extends JFrame {

    /**
     * @param view interface between view components and controller
     */
    public InitializationFrame (View view) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().add(new InitializationPanel(this, view));

    }

    public void showFrame () {
        pack();
        setVisible(true);
    }

}
