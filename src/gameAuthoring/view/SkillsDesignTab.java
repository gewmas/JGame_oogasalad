package gameAuthoring.view;

import gameEngine.parser.Parser;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai & Susan Zhang
 *         SkillsDesignTab allows for users to upload audio corresponding to the special skills
 *         allowed for enemies. When all fields in SkillsDesignTab are completed, SkillsDesignTab
 *         notifies its observers by passing its stored audio as ResourceJSONObjects. These JSON
 *         objects can then be stored in GameData.
 */
public class SkillsDesignTab extends Tab {

    private List<AudioLabel> myAudioLabels = new ArrayList<AudioLabel>();
    private static final String HASTE = "haste";
    private static final String ARMOUR = "armour";
    private static final String HEAL = "heal";
    private static final String LIGHT = "light";
    private static final String POISON = "poison";
    private static final String BUTTON_ALIGNMENT = "align center";
    private static final Dimension SUBPANEL_DIMENSION = new Dimension(300, 400);
    private static final String WRAP_MODE = "wrap 1";

    /**
     * Creates new SkillsDesignTab
     */
    public SkillsDesignTab () {
    }

    @Override
    public JPanel getTab () {
        myMainPanel.setLayout(new MigLayout(WRAP_MODE));
        myContentPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myContentPanel.setPreferredSize(SUBPANEL_DIMENSION);
        myContentPanel.setOpaque(false);
        myContentPanel.setBorder(StyleConstants.DEFAULT_PANEL_BORDER);
        myMainPanel.setOpaque(false);
        myMainPanel.setPreferredSize(StyleConstants.DEFAULT_PANEL_SIZE);
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("SkillsTitle"));
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, StyleConstants.DEFAULT_SPAN_MODE);
        addHaste();
        addArmour();
        addHeal();
        addLight();
        addPoison();
        addSubmitButton();
        myMainPanel.add(myContentPanel);
        return myMainPanel;
    }

    /**
     * Adds label and AudioLabel that allows for haste skill audio to be set
     */
    private void addHaste () {
        JLabel hasteLabel = new JLabel(StyleConstants.resourceBundle.getString("SkillsHaste"));
        hasteLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        hasteLabel.setToolTipText(StyleConstants.resourceBundle.getString("SkillsHasteTip"));
        AudioLabel hasteAudio = new AudioLabel(HASTE, false);
        hasteAudio.setMutableStatusTrue();
        myAudioLabels.add(hasteAudio);
        myContentPanel.add(hasteLabel);
        myContentPanel.add(hasteAudio);
    }

    /**
     * Adds label and AudioLabel that allows for armour skill audio to be set
     */
    private void addArmour () {
        JLabel armourLabel = new JLabel(StyleConstants.resourceBundle.getString("SkillsArmour"));
        armourLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        armourLabel.setToolTipText(StyleConstants.resourceBundle.getString("SkillsArmourTip"));
        AudioLabel armorAudio = new AudioLabel(ARMOUR, false);
        armorAudio.setMutableStatusTrue();
        myAudioLabels.add(armorAudio);
        myContentPanel.add(armourLabel);
        myContentPanel.add(armorAudio);
    }

    /**
     * Adds label and AudioLabel that allows for heal skill audio to be set
     */
    private void addHeal () {
        JLabel healLabel = new JLabel(StyleConstants.resourceBundle.getString("SkillsHeal"));
        healLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        healLabel.setToolTipText(StyleConstants.resourceBundle.getString("SkillsHealTip"));
        AudioLabel healAudio = new AudioLabel(HEAL, false);
        healAudio.setMutableStatusTrue();
        myAudioLabels.add(healAudio);
        myContentPanel.add(healLabel);
        myContentPanel.add(healAudio);
    }

    /**
     * Adds label and AudioLabel that allows for light skill audio to be set
     */
    private void addLight () {
        JLabel lightLabel = new JLabel(StyleConstants.resourceBundle.getString("SkillsLight"));
        lightLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        lightLabel.setToolTipText(StyleConstants.resourceBundle.getString("SkillsLightTip"));
        AudioLabel lightAudio = new AudioLabel(LIGHT, false);
        lightAudio.setMutableStatusTrue();
        myAudioLabels.add(lightAudio);
        myContentPanel.add(lightLabel);
        myContentPanel.add(lightAudio);
    }

    /**
     * Adds label and AudioLabel that allows for poison skill audio to be set
     */
    private void addPoison () {
        JLabel poisonLabel = new JLabel(StyleConstants.resourceBundle.getString("SkillsPoison"));
        poisonLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        poisonLabel.setToolTipText(StyleConstants.resourceBundle.getString("SkillsPoisonTip"));
        AudioLabel poisonAudio = new AudioLabel(POISON, false);
        poisonAudio.setMutableStatusTrue();
        myAudioLabels.add(poisonAudio);
        myContentPanel.add(poisonLabel);
        myContentPanel.add(poisonAudio);
    }

    /**
     * Adds button that, when clicked, allows for skills audio to be set
     */
    private void addSubmitButton () {
        JButton submitButton = new JButton(StyleConstants.resourceBundle.getString("SkillsSubmit"));
        submitButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        submitButton.setToolTipText(StyleConstants.resourceBundle.getString("SkillsSubmitTip"));
        submitButton.addMouseListener(createSubmitListener());
        myContentPanel.add(submitButton, BUTTON_ALIGNMENT);
    }

    /**
     * Set audio for special enemy skills
     * 
     * @return MouseAdapter that allows for skills audio to be set
     */
    private MouseAdapter createSubmitListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                setChanged();
                notifyObservers(myAudioLabels);
                clearChanged();
                JOptionPane.showMessageDialog(null, StyleConstants.resourceBundle
                        .getString("SkillsValidSubmission"));
            }
        };
        return listener;
    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }

}
