package gameAuthoring;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class UserSoundsTab implements Observer {

    private JPanel myMainPanel = new JPanel(new MigLayout("wrap 1"));

    public JScrollPane getTab () {
        JScrollPane scrollPane = new JScrollPane(myMainPanel);
        addSound("test1");
        addSound("test2");
        return scrollPane;
    }

    public void addSound (String name) {
        Icon icon = new ImageIcon(getClass().getResource("sound.png"));
        JButton sound = new JButton(name);
        sound.setPreferredSize(new Dimension(60, 60));
        sound.setIcon(icon);
        sound.addMouseListener(addSoundPreview());
        myMainPanel.add(sound);
    }

    public MouseAdapter addSoundPreview () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                playSound();
            }
        };
        return listener;
    }

    public void playSound () {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem
                            .getAudioInputStream(new File(
                                                          "C:/Users/BecLai/workspace/oogasalad_FooBar/src/gameAuthoring/laser.wav")
                                    .getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    @Override
    public void update (Observable o, Object arg) {
        // TODO Auto-generated method stub

    }
}
