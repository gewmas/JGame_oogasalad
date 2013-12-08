package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class MiscellaneousTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    // bullet image
    // magic image for each
    // speed
    // flame
    // invisibility - enemy
    // poison -
    // damage
    public MiscellaneousTab (GameData gameData) {
        super(gameData);
    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new MigLayout("wrap 1"));
        JLabel title = new JLabel("Miscellaneous");
        title.setFont(Constants.DEFAULT_TITLE_FONT);
        mainPanel.add(title, "span 2");
        JPanel subPanel = new JPanel(new MigLayout("wrap 2"));
        subPanel.setPreferredSize(new Dimension(300, 300));
        mainPanel.setPreferredSize(new Dimension(500, 500));

        ImageLabel bulletImage = new ImageLabel(50, 50);
        ImageLabel speedImage = new ImageLabel(50, 50);
        ImageLabel poisonImage = new ImageLabel(50, 50);
        ImageLabel flameImage = new ImageLabel(50, 50);

        JLabel bulletLabel = new JLabel("Bullet image:");
        bulletLabel.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel speedLabel = new JLabel("Speed image:");
        speedLabel.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel poisonLabel = new JLabel("Poison image:");
        poisonLabel.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel flameLabel = new JLabel("Flame image:");
        flameLabel.setFont(Constants.DEFAULT_BODY_FONT);

        subPanel.add(bulletLabel);
        subPanel.add(bulletImage);
        subPanel.add(speedLabel);
        subPanel.add(speedImage);
        subPanel.add(poisonLabel);
        subPanel.add(poisonImage);
        subPanel.add(flameLabel);
        subPanel.add(flameImage);
        subPanel.setOpaque(false);
        mainPanel.setOpaque(false);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        subPanel.setBorder(b);
        mainPanel.add(subPanel);
        return mainPanel;
    }

    public MouseAdapter createImageSelectionListener (final JLabel imgLabel) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                System.out.println("clicked");
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    System.out.println(INPUT_CHOOSER.getSelectedFile());
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    Image tower;
                    try {
                        tower = ImageIO.read(imgSource);
                        imgLabel.setIcon(new ImageIcon(tower));
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return listener;
    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }

}
