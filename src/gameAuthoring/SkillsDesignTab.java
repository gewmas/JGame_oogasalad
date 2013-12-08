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


public class SkillsDesignTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    public SkillsDesignTab (GameData gameData) {
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

        AudioLabel hasteAudio = new AudioLabel("Haste");
        hasteAudio.setMutableStatusTrue();
        AudioLabel armorAudio = new AudioLabel("Armour");
        armorAudio.setMutableStatusTrue();
        AudioLabel healAudio = new AudioLabel("Heal");
        healAudio.setMutableStatusTrue();
        AudioLabel lightAudio = new AudioLabel("Light");
        lightAudio.setMutableStatusTrue();
        AudioLabel poisonAudio = new AudioLabel("Poison");
        poisonAudio.setMutableStatusTrue();

        JLabel hasteLabel = new JLabel("Haste Audio:");
        hasteLabel.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel armorLabel = new JLabel("Armor Audio:");
        armorLabel.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel healLabel = new JLabel("Heal Audio:");
        healLabel.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel lightLabel = new JLabel("Light Audio:");
        lightLabel.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel poisonLabel = new JLabel("Poison Audio:");
        poisonLabel.setFont(Constants.DEFAULT_BODY_FONT);

        subPanel.add(hasteLabel);
        subPanel.add(hasteAudio);
        subPanel.add(armorLabel);
        subPanel.add(armorAudio);
        subPanel.add(healLabel);
        subPanel.add(healAudio);
        subPanel.add(lightLabel);
        subPanel.add(lightAudio);
        subPanel.add(poisonLabel);
        subPanel.add(poisonAudio);

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
