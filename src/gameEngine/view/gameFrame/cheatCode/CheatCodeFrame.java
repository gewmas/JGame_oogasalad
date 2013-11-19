package gameEngine.view.gameFrame.cheatCode;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;


public class CheatCodeFrame extends JFrame {

    CheatCodeFrame () {
        super();

        MigLayout layout = new MigLayout("wrap 4");

        JPanel main = new JPanel(layout);

        JLabel available = new JLabel("cheat codes yo");
        JTextArea input = new JTextArea("dfd");
        input.setPreferredSize(new Dimension(100, 100));
        DefaultTableModel model =
                new DefaultTableModel(null,
                                      new Object[] { "Cheat code", "Success" });
        JTable history = new JTable(model);

        main.add(history, "span 4");
        main.add(new JScrollPane(input), "span 3");
        main.add(available, "span 1");

        add(main);
    }

    public void showFrame () {
        pack();
        setVisible(true);
    }

    public static void main (String[] args) {
        CheatCodeFrame frame = new CheatCodeFrame();
        frame.showFrame();
    }
}
