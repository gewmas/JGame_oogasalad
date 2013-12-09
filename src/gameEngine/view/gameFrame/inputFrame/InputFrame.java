package gameEngine.view.gameFrame.inputFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


/**
 * @author Lalita Maraj
 *         A frame that accepts user text input and can specify actions
 *         to be performed by text input when user clicks the
 *         submit button.
 *         The text input behavior is defined in the InputSender's submit
 *         method.
 */
@SuppressWarnings("serial")
public class InputFrame extends JFrame{

    private static final String INPUT_PANEL_POSITION = "span 2";
    private static final String LAYOUT_WRAP_SETTINGS = "wrap 3";

    public InputFrame (InputSender inputSender) {
        super();

        MigLayout layout = new MigLayout(LAYOUT_WRAP_SETTINGS);
        JPanel main = new JPanel(layout);
        main.add(new InputPanel(inputSender), INPUT_PANEL_POSITION);

        add(main);
    }

    public void display () {
        pack();
        setVisible(true);
    }

}
