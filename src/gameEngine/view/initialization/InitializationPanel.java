package gameEngine.view.initialization;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import gameEngine.view.ViewConstants;
import gameEngine.view.View;


/**
 * @author Lalita Maraj
 *         Panel that contains the options to load a file
 *         to start a new game
 */

@SuppressWarnings("serial")
public class InitializationPanel extends JPanel {

    private static final String SELECTOR_BUTTOM_ALIGNMENT = "align right";
    private static final String CANCEL_BUTTOM_ALIGNMENT = "align left";
    private static final String MESSAGE_SPAN = "span 2";
    private static final String WRAP_CONSTNAT = "wrap 2";

    public InitializationPanel (JFrame initializationFrame, View view) {
        super();
        this.setLayout(new MigLayout(WRAP_CONSTNAT));
        JLabel message = new JLabel(ViewConstants.resourceBundle.getString("Welcome"));
        add(message, MESSAGE_SPAN);

        JButton selectorButton = new FileSelectorButton(view);
        JButton cancelButton = new CancelButton(initializationFrame);
        add(cancelButton, CANCEL_BUTTOM_ALIGNMENT);
        add(selectorButton, SELECTOR_BUTTOM_ALIGNMENT);
    }

}
