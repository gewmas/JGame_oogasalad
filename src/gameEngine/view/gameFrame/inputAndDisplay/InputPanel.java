package gameEngine.view.gameFrame.inputAndDisplay;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


/**
 * @author lalitamaraj
 *         Panel used to receive user input and execute
 *         user defined behavior on text input
 *         by calling the submit method on inputSender
 */
public class InputPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int TEXTBOX_HEIGHT = 50;
    private static final int TEXTBOX_WIDTH = 200;
    private static final String SUBMIT_BUTTON_POSITION = "span 1";
    private static final String TEXT_BOX_POSITION = "span 3";
    private static final String LAYOUT_WRAP_SETTING = "wrap 4";

    protected InputPanel (final InputSender inputSender) {
        super();
        MigLayout layout = new MigLayout(LAYOUT_WRAP_SETTING);
        this.setLayout(layout);
        Border valuePanelBorder =
                BorderFactory.createTitledBorder("");
        setBorder(valuePanelBorder);
        setBorder(valuePanelBorder);
        JButton submit = new JButton("Submit");
        final JTextArea input = new JTextArea("add_gold");
        input.setPreferredSize(new Dimension(TEXTBOX_WIDTH, TEXTBOX_HEIGHT));
        this.add(new JScrollPane(input), TEXT_BOX_POSITION);
        this.add(submit, SUBMIT_BUTTON_POSITION);

        submit.addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent me) {

                inputSender.submit(input.getText().trim());
                input.setText("");
            }

        });
    }
}
