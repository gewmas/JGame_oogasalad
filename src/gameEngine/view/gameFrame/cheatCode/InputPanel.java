package gameEngine.view.gameFrame.cheatCode;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;
import gameEngine.view.Button;
import gameEngine.view.Panel;


public class InputPanel extends Panel {

    public InputPanel () {
        super();
        MigLayout layout = new MigLayout("wrap 4");
        this.setLayout(layout);
        Border valuePanelBorder =
                BorderFactory.createTitledBorder("");
        setBorder(valuePanelBorder);
        setBorder(valuePanelBorder);
        Button submit = new Button("Submit");
        JTextArea input = new JTextArea("WIN DA GAME BRO");
        this.add(new JScrollPane(input), "span 3");
        this.add(submit, "span 1");

    }
}
