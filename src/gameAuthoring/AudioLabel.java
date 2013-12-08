package gameAuthoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class AudioLabel extends JLabel {

    private File myAudioSource;
    private Image myAudioImage;
    private File myAudioImageSource;
    private String myID;

    public AudioLabel () {
        initialize();
    }

    public AudioLabel (String id) {
        myID = id;
        initialize();
        try {
            myAudioImageSource = new File(getClass().getResource("sound.png").toURI());
        }
        catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        Image image;
        try {
            image = ImageIO.read(myAudioImageSource);
            myAudioImage = image;
            this.setIcon(new ImageIcon(image));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize () {
        this.setPreferredSize(new Dimension(50, 50));
        this.addMouseListener(createAudioListener(this));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        this.setBorder(border);
        this.addMouseListener(addCursorChangeListener(this));
    }

    public void setAudioFile (File audio) {
        myAudioSource = audio;
    }

    public File getAudioFile () {
        return myAudioSource;
    }

    public String getID () {
        return myID;
    }

    public Image getAudioImage () {
        return myAudioImage;
    }

    public File getAudioImageSource () {
        return myAudioImageSource;
    }

    public void transferLabelInformation (AudioLabel other) {
        if (other != null) {
            myID = other.getID();
            myAudioSource = other.getAudioFile();
            myAudioImage = other.getAudioImage();
            this.setIcon(new ImageIcon(myAudioImage));
        }
    }

    public MouseAdapter createAudioListener (final AudioLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                label.transferLabelInformation(GameAuthoringGUI.myAudioLabel);
            }
        };
        return listener;
    }

    public MouseAdapter addCursorChangeListener (final AudioLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            private boolean audioSelected = false;

            @Override
            public void mouseClicked (MouseEvent e) {
                audioSelected = !audioSelected;
                GameAuthoringGUI.myAudioLabel = label;
                if (audioSelected) {
                    GameAuthoringGUI.setCursor(label.getAudioImageSource());
                }
                else {
                    GameAuthoringGUI.setCursorNull();
                }
            }
        };
        return listener;
    }
}
