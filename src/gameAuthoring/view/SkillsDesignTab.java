package gameAuthoring.view;

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
    private JPanel mySubPanel;
    private static final String WRAP_MODE = "wrap 1";

    public SkillsDesignTab () {
    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new GradientPanel();
        mainPanel.setLayout(new MigLayout(WRAP_MODE));
        JLabel title = new JLabel("Miscellaneous");
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        mainPanel.add(title, "span 2");
        mySubPanel = new JPanel(new MigLayout("wrap 2"));
        mySubPanel.setPreferredSize(new Dimension(300, 300));
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
        hasteLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        JLabel armorLabel = new JLabel("Armor Audio:");
        armorLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        JLabel healLabel = new JLabel("Heal Audio:");
        healLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        JLabel lightLabel = new JLabel("Light Audio:");
        lightLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        JLabel poisonLabel = new JLabel("Poison Audio:");
        poisonLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);

        JButton submitButton = new JButton("Set Skills Audio");
        submitButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        submitButton.addMouseListener(createSubmitListener());

        mySubPanel.add(hasteLabel);
        mySubPanel.add(hasteAudio);
        mySubPanel.add(armorLabel);
        mySubPanel.add(armorAudio);
        mySubPanel.add(healLabel);
        mySubPanel.add(healAudio);
        mySubPanel.add(lightLabel);
        mySubPanel.add(lightAudio);
        mySubPanel.add(poisonLabel);
        mySubPanel.add(poisonAudio);
        mySubPanel.add(submitButton, "align center");

        mySubPanel.setOpaque(false);
        mainPanel.setOpaque(false);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        mySubPanel.setBorder(b);
        mainPanel.add(mySubPanel);
        return mainPanel;
    }

    public void addHaste () {
        JLabel hasteLabel = new JLabel("Haste Audio:");
        hasteLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        AudioLabel hasteAudio = new AudioLabel("Haste", false);
        hasteAudio.setMutableStatusTrue();
        mySubPanel.add(hasteLabel);
        mySubPanel.add(hasteAudio);
    }

    public void addArmour () {
        JLabel armorLabel = new JLabel("Armor Audio:");
        armorLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        AudioLabel armorAudio = new AudioLabel("Armour", false);
        armorAudio.setMutableStatusTrue();
        mySubPanel.add(armorLabel);
        mySubPanel.add(armorAudio);
    }

    public void addHeal () {
        JLabel healLabel = new JLabel("Heal Audio:");
        healLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        AudioLabel healAudio = new AudioLabel("Heal", false);
        healAudio.setMutableStatusTrue();
        mySubPanel.add(healLabel);
        mySubPanel.add(healAudio);
    }

    public void addLight () {
        JLabel lightLabel = new JLabel("Light Audio:");
        lightLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);

    }

    public MouseAdapter createSubmitListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                setChanged();
                notifyObservers(myAudioLabels);
                clearChanged();
            }
        };
        return listener;
    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }

}
