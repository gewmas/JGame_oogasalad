package gameAuthoring;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;


public class MainPanel {

    public MainPanel () {
        JFrame frame = new JFrame();
        ImagePanel mainPanel = new ImagePanel("tower_main.jpg");
        mainPanel.setLayout(new MigLayout("wrap 2"));
        JLabel title = new JLabel();
        Image titleImage;
        try {
            titleImage = ImageIO.read(this.getClass().getResource("tower_defense_title.png"));
            title.setIcon(new ImageIcon(titleImage));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        ImageButton authoringMode = new ImageButton("create_game.png");
        authoringMode.setPreferredSize(new Dimension(300, 100));
        ImageButton gameMode = new ImageButton("play_game.png");
        gameMode.setPreferredSize(new Dimension(300, 100));
        mainPanel.add(title, "align center, span 2, gap 0 0 50 0");
        mainPanel.add(authoringMode, "gap 500 0 300 300");
        mainPanel.add(gameMode);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1300, 1000));
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public static void main (String[] args) {
        new MainPanel();
    }
}
