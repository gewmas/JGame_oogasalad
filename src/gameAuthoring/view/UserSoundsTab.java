package gameAuthoring.view;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai & Susan Zhang
 *         UserSoundsTab stores audio uploaded by the user. It allows the user to click and drag on
 *         audio icons in order to set fields in the authoring environment that require audio.
 */
public class UserSoundsTab {

    private JPanel myContentPanel;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/audio");
    private int myNumAudio;
    private static final String AUDIO_UPLOAD_BUTTON_FORMATTING = "align center, gap 10 10 30 10";
    private static final String NEW_SOUND_BUTTON_FORMATTING = "gap 40 0 0 10";
    private static final String PLAY_SOUND_BUTTON_FORMATTING = "gap 20 0 0 10";
    private static final String MAIN_PANEL_WRAP_MODE = "wrap 1";
    private static final Dimension CONTENT_PANEL_DIMENSION = new Dimension(300, 500);

    /**
     * @return main content panel of UserSoundsTab
     */
    public JPanel getTab () {
        JPanel mainPanel = new GradientPanel(new MigLayout(MAIN_PANEL_WRAP_MODE));
        myContentPanel = new JPanel(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        mainPanel.setOpaque(false);
        myContentPanel.setPreferredSize(CONTENT_PANEL_DIMENSION);
        JButton uploadAudio =
                new JButton(StyleConstants.resourceBundle.getString("UserSoundsTitle"));
        uploadAudio.setFont(StyleConstants.DEFAULT_BODY_FONT);
        uploadAudio.addMouseListener(addFileUploadListener());
        JScrollPane scrollPane = new JScrollPane(myContentPanel);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        mainPanel.add(scrollPane);
        mainPanel.add(uploadAudio, AUDIO_UPLOAD_BUTTON_FORMATTING);
        return mainPanel;
    }

    /**
     * Select and upload audio file to library
     * 
     * @return MouseAdapter that allows audio to be uploaded to library
     */
    private MouseAdapter addFileUploadListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    myNumAudio++;
                    AudioLabel sound = new AudioLabel("" + myNumAudio, true);
                    JButton playSound = new JButton("Play sound");
                    playSound.addMouseListener(addSoundPreviewListener(sound));
                    playSound.setFont(StyleConstants.DEFAULT_BODY_FONT);
                    File audioSource = INPUT_CHOOSER.getSelectedFile();
                    myNumAudio++;
                    sound.setAudioFile(audioSource);
                    myContentPanel.add(sound, NEW_SOUND_BUTTON_FORMATTING);
                    myContentPanel.add(playSound, PLAY_SOUND_BUTTON_FORMATTING);
                    myContentPanel.revalidate();
                }
            }
        };
        return listener;
    }

    /**
     * Allows audio to be played when component is clicked
     * 
     * @param label is AudioLabel that can be previewed by clicking on component
     * @return MouseAdapter that plays sound on click
     */
    private MouseAdapter addSoundPreviewListener (final AudioLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                playSound(label);
            }
        };
        return listener;
    }

    /**
     * Plays audio
     * 
     * @param sound is AudioLabel containing audio to be played
     */
    private void playSound (AudioLabel sound) {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem
                            .getAudioInputStream(sound.getAudioFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
