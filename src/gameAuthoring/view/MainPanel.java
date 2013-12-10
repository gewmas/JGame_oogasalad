package gameAuthoring.view;

import java.awt.Dimension;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai & Susan Zhang
 *         MainPanel combines the GameAuthoringGUI with the game engine. It allows the user to pick
 *         between the two options.
 * 
 */
public class MainPanel {
    private JFrame myFrame;

    /**
     * Creates new MainPanel
     */
    public MainPanel () {
        myFrame = new JFrame();
        ImagePanel mainPanel = new ImagePanel("tower_main2.png");
        mainPanel.setLayout(new MigLayout("wrap 1"));
        ImageButton authoringMode = new ImageButton("create_game.png");
        authoringMode.setPreferredSize(new Dimension(300, 100));
        ImageButton gameMode = new ImageButton("play_game.png");
        gameMode.setPreferredSize(new Dimension(300, 100));
        mainPanel.add(authoringMode, "gap 300 50 300 0");
        mainPanel.add(gameMode, "gap 300 50 30 0");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setPreferredSize(new Dimension(950, 750));
        myFrame.setContentPane(mainPanel);
        myFrame.pack();
        myFrame.setLocationByPlatform(true);
        myFrame.setResizable(false);
    }

    /**
     * Allows MainPanel to be displayed
     */
    public void show () {
        myFrame.setVisible(true);
    }

    /**
     * Disposes of MainPanel
     */
    private void close () {
        myFrame.dispose();
    }

    public static void main (String[] args) {
        MainPanel panel = new MainPanel();
        panel.show();
    }
}
