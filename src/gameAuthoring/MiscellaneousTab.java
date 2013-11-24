package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        JLabel title = new JLabel("Miscellaneous");
        title.setFont(Constants.defaultTitleFont);
        mainPanel.add(title, "span 2");
        mainPanel.setPreferredSize(new Dimension(500, 500));

        JButton bulletImageButton = new JButton("Select image for bullet");
        bulletImageButton.setFont(Constants.defaultBodyFont);
        JButton speedImageButton = new JButton("Select image for slow down buff");
        speedImageButton.setFont(Constants.defaultBodyFont);
        JButton poisonImageButton = new JButton("Select image for poison buff");
        poisonImageButton.setFont(Constants.defaultBodyFont);
        JButton flameImageButton = new JButton("Select image for flame buff");
        flameImageButton.setFont(Constants.defaultBodyFont);

        JLabel bulletImage = new JLabel();
        JLabel speedImage = new JLabel();
        JLabel poisonImage = new JLabel();
        JLabel flameImage = new JLabel();

        bulletImageButton.addMouseListener(createImageSelectionListener(bulletImage));
        speedImageButton.addMouseListener(createImageSelectionListener(speedImage));
        poisonImageButton.addMouseListener(createImageSelectionListener(poisonImage));
        flameImageButton.addMouseListener(createImageSelectionListener(flameImage));

        mainPanel.add(bulletImageButton);
        mainPanel.add(bulletImage);
        mainPanel.add(speedImageButton);
        mainPanel.add(speedImage);
        mainPanel.add(poisonImageButton);
        mainPanel.add(poisonImage);
        mainPanel.add(flameImageButton);
        mainPanel.add(flameImage);

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
