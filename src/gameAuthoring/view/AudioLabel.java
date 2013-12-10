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
import javax.swing.border.Border;


/**
 * @author Rebecca Lai & Susan Zhang
 *         AudioLabel is a label that holds information for a specific audio file. AudioLabel
 *         inherits from ImageLabel so that it can be clicked and dragged from the User Library.
 */
@SuppressWarnings("serial")
public class AudioLabel extends ImageLabel {

    private File myAudioSource;

    /**
     * Creates a new AudioLabel
     */
    public AudioLabel () {
        initialize();
    }

    /**
     * @param id is name of new AudioLabel
     * @param displayImage determines whether the AudioLabel should be displayed with a default
     *        image
     */
    public AudioLabel (String id, boolean displayImage) {
        myID = id;
        initialize();
        if (displayImage) {
            try {
                myImageSource = new File(getClass().getResource("sound.png").toURI());
            }
            catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
            Image image;
            try {
                image = ImageIO.read(myImageSource);
                myImage = image;
                this.setIcon(new ImageIcon(image));
               
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize () {
        this.setPreferredSize(new Dimension(50, 50));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        this.setBorder(border);
        this.addMouseListener(changeCursorListener(this));
    }

    /**
     * Set the audio file for the AudioLabel
     * 
     * @param audio is audio file to be stored in AudioLabel
     */
    public void setAudioFile (File audio) {
        myAudioSource = audio;
    }

    /**
     * @return File for audio stored in AudioLabel
     */
    public File getAudioFile () {
        return myAudioSource;
    }

    /**
     * Pass information from one AudioLabel to another
     * 
     * @param other is AudioLabel that is passing information to this AudioLabel
     */
    public void transferLabelInformation (AudioLabel other) {
        if (other != null) {
            if (myID == null) {
                myID = other.getID();
            }
            myAudioSource = other.getAudioFile();
            myImageSource = other.getImageFile();
            myImage = other.getImage();
            this.setIcon(new ImageIcon(myImage));
        }
    }

    /**
     * Allows for click-and-drag between AudioLabels
     * 
     * @param label is the AudioLabel that receives information when clicked
     * @return MouseAdapter that transfers audio information between AudioLabels
     */
    public MouseAdapter changeCursorListener (final AudioLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            boolean selected = false;

            @Override
            public void mouseClicked (MouseEvent e) {
                selected = !selected;
                if (GameAuthoringGUI.myAudioLabel == null && selected) {
                    GameAuthoringGUI.myAudioLabel = label;
                    GameAuthoringGUI.setCursor(label.getImageFile());
                }
                if (GameAuthoringGUI.myAudioLabel != null && isMutable && selected) {
                    label.transferLabelInformation(GameAuthoringGUI.myAudioLabel);
                    GameAuthoringGUI.setCursorDefault();
                }
                if (!selected) {
                    GameAuthoringGUI.setCursorDefault();
                    GameAuthoringGUI.myAudioLabel = null;
                }
            }
        };
        return listener;
    }
}
