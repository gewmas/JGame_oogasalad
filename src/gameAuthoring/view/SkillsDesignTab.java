package gameAuthoring.view;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class SkillsDesignTab extends Tab {

    private List<AudioLabel> myAudioLabels = new ArrayList<AudioLabel>();

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

        AudioLabel hasteAudio = new AudioLabel("Haste", false);
        hasteAudio.setMutableStatusTrue();
        AudioLabel armorAudio = new AudioLabel("Armour", false);
        armorAudio.setMutableStatusTrue();
        AudioLabel healAudio = new AudioLabel("Heal", false);
        healAudio.setMutableStatusTrue();
        AudioLabel lightAudio = new AudioLabel("Light", false);
        lightAudio.setMutableStatusTrue();
        AudioLabel poisonAudio = new AudioLabel("Poison", false);
        poisonAudio.setMutableStatusTrue();

        myAudioLabels.add(hasteAudio);
        myAudioLabels.add(armorAudio);
        myAudioLabels.add(healAudio);
        myAudioLabels.add(lightAudio);
        myAudioLabels.add(poisonAudio);

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

        JButton submitButton = new JButton("Set Skills Audio");
        submitButton.setFont(Constants.DEFAULT_BODY_FONT);
        submitButton.addMouseListener(createSubmitListener());

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
        subPanel.add(submitButton, "align center");

        subPanel.setOpaque(false);
        mainPanel.setOpaque(false);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        subPanel.setBorder(b);
        mainPanel.add(subPanel);
        return mainPanel;
    }

    public MouseAdapter createSubmitListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                for (AudioLabel label : myAudioLabels) {
                    myGameData.addAudio(label.getID(), label.getAudioFile().getName());
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
