package gameAuthoring.view;

import gameEngine.controller.Controller;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;


public class MainPanel {
    private JFrame frame;

    public MainPanel () {
        frame = new JFrame();
        ImagePanel mainPanel = new ImagePanel("tower_main2.png");
        mainPanel.setLayout(new MigLayout("wrap 1"));
        ImageButton authoringMode = new ImageButton("create_game.png");
        authoringMode.setPreferredSize(new Dimension(300, 100));
        authoringMode.addMouseListener(setAuthoringListener());
        ImageButton gameMode = new ImageButton("play_game.png");
        gameMode.setPreferredSize(new Dimension(300, 100));
        gameMode.addMouseListener(setEngineListener());
        mainPanel.add(authoringMode, "gap 300 50 300 0");
        mainPanel.add(gameMode, "gap 300 50 30 0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(950, 750));
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
    }

    private void show () {
        frame.setVisible(true);
    }

    private void close () {
        frame.dispose();
    }

    /**
     * @author Lalita Maraj
     * @return mouse listener to open the game
     */
    private MouseAdapter setEngineListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                Controller controller = new Controller();
                controller.promptForFile();
                close();
            }
        };
        return listener;
    }

    private MouseAdapter setAuthoringListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                GameAuthoringGUI gui = new GameAuthoringGUI();
            }
        };
        return listener;
    }

    public static void main (String[] args) {
        MainPanel panel = new MainPanel();
        panel.show();
    }
}
