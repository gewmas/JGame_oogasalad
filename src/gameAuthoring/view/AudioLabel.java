package gameAuthoring.view;

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
    private boolean isMutable = false;

    public AudioLabel () {
        initialize();
    }

    public AudioLabel (String id, boolean displayImage) {
        myID = id;
        initialize();
        if (displayImage) {
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
    }

    public void initialize () {
        this.setPreferredSize(new Dimension(50, 50));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        this.setBorder(border);
        this.addMouseListener(changeCursorListener(this));
    }

    public void setMutableStatusTrue () {
        isMutable = true;
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
            if (myID == null) {
                myID = other.getID();
            }
            myAudioSource = other.getAudioFile();
            myAudioImage = other.getAudioImage();
            this.setIcon(new ImageIcon(myAudioImage));
        }
    }

    public void setLabelIcon (File imageSource) {
        myAudioImageSource = imageSource;
        try {
            myAudioImage = ImageIO.read(imageSource);
            myAudioImage = myAudioImage.getScaledInstance(50, 50, Image.SCALE_FAST);
            this.setIcon(new ImageIcon(myAudioImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MouseAdapter changeCursorListener (final AudioLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            boolean selected = false;

            @Override
            public void mouseClicked (MouseEvent e) {
                selected = !selected;
                if (GameAuthoringGUI.myAudioLabel == null && selected) {
                    GameAuthoringGUI.myAudioLabel = label;
                    GameAuthoringGUI.setCursor(label.getAudioImageSource());
                }
                if (GameAuthoringGUI.myAudioLabel != null && isMutable && selected) {
                    label.transferLabelInformation(GameAuthoringGUI.myAudioLabel);
                    GameAuthoringGUI.setCursorNull();
                }
                if (!selected) {
                    GameAuthoringGUI.setCursorNull();
                    GameAuthoringGUI.myAudioLabel = null;
                }
            }
        };
        return listener;
    }
}
