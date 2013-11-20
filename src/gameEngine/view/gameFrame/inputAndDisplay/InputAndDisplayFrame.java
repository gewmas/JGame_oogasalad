package gameEngine.view.gameFrame.inputAndDisplay;

import gameEngine.view.gameFrame.store.InfoDisplayPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


/**
 * @author lalitamaraj
 *         A frame that allows a user to
 */
public class InputAndDisplayFrame extends JFrame {

    private static final String DISPLAY_PANEL_POSITION = "north";
    private static final String INPUT_PANEL_POSITION = "span 2";
    private static final String LAYOUT_WRAP_SETTINGS = "wrap 3";

    public InputAndDisplayFrame (String displayName, InputSender inputSender) {
        super();

        MigLayout layout = new MigLayout(LAYOUT_WRAP_SETTINGS);
        JPanel main = new JPanel(layout);
        main.add(new InputPanel(inputSender), INPUT_PANEL_POSITION);
        main.add(new InfoDisplayPanel(displayName), DISPLAY_PANEL_POSITION);

        add(main);
    }

    public void showFrame () {
        pack();
        setVisible(true);
    }

}
