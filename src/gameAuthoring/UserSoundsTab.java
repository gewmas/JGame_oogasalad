package gameAuthoring;

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


public class UserSoundsTab {

    private JPanel myMainPanel = new GradientPanel(new MigLayout("wrap 1"));
    private JPanel mySubPanel = new JPanel(new MigLayout("wrap 2"));
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/audio");
    private int myNumAudio;

    public JPanel getTab () {
        myMainPanel.setOpaque(false);
        mySubPanel.setPreferredSize(new Dimension(300, 500));
        JButton uploadAudio = new JButton("Load audio");
        uploadAudio.setFont(Constants.DEFAULT_BODY_FONT);
        uploadAudio.addMouseListener(addFileUploadListener());
        JScrollPane scrollPane = new JScrollPane(mySubPanel);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        myMainPanel.add(scrollPane);
        myMainPanel.add(uploadAudio, "align center, gap 10 10 30 10");
        return myMainPanel;
    }

    public MouseAdapter addFileUploadListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    myNumAudio++;
                    AudioLabel sound = new AudioLabel("" + myNumAudio, true);
                    JButton playSound = new JButton("Play sound");
                    playSound.addMouseListener(addSoundPreviewListener(sound));
                    playSound.setFont(Constants.DEFAULT_BODY_FONT);
                    File audioSource = INPUT_CHOOSER.getSelectedFile();
                    myNumAudio++;
                    sound.setAudioFile(audioSource);
                    mySubPanel.add(sound, "gap 40 0 0 10");
                    mySubPanel.add(playSound, "gap 20 0 0 10");
                    mySubPanel.revalidate();
                }
            }
        };
        return listener;
    }

    public MouseAdapter addSoundPreviewListener (final AudioLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            private boolean audioSelected = false;

            @Override
            public void mouseClicked (MouseEvent e) {
                playSound(label);
            }
        };
        return listener;
    }

    public void playSound (AudioLabel sound) {
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
