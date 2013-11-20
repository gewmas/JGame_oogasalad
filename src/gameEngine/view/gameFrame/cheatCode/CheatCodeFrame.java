package gameEngine.view.gameFrame.cheatCode;

import gameEngine.view.gameFrame.store.InfoPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


public class CheatCodeFrame extends JFrame {

    CheatCodeFrame () {
        super();

        MigLayout layout = new MigLayout("wrap 3");

        JPanel main = new JPanel(layout);

        main.add(new InputPanel(), "span 2");
        main.add(new InfoPanel("Cheat sheet"), "north");

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
