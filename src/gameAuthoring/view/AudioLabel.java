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


public class AudioLabel extends ImageLabel {

    private File myAudioSource;

    public AudioLabel () {
        initialize();
    }

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

    public void setAudioFile (File audio) {
        myAudioSource = audio;
    }

    public File getAudioFile () {
        return myAudioSource;
    }

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

    public MouseAdapter changeCursorListener (final AudioLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            boolean selected = false;

            @Override
            public void mouseClicked (MouseEvent e) {
                selected = !selected;
                System.out.println("Selected? " + selected);
                if (GameAuthoringGUI.myAudioLabel == null) {
                    System.out.println("GameAuthoringGUI label null");
                }
                if (GameAuthoringGUI.myAudioLabel != null) {
                    System.out.println("GameAuthoringGUI label ID " +
                                       GameAuthoringGUI.myAudioLabel.getID());
                }
                System.out.println("Mutable? " + isMutable);

                if (GameAuthoringGUI.myAudioLabel == null && selected) {
                    System.out.println("IF 1");
                    GameAuthoringGUI.myAudioLabel = label;
                    GameAuthoringGUI.setCursor(label.getImageFile());
                }
                if (GameAuthoringGUI.myAudioLabel != null && isMutable && selected) {
                    System.out.println("IF 2");
                    label.transferLabelInformation(GameAuthoringGUI.myAudioLabel);
                    GameAuthoringGUI.setCursorNull();
                }
                if (!selected) {
                    System.out.println("IF 3");
                    GameAuthoringGUI.setCursorNull();
                    GameAuthoringGUI.myAudioLabel = null;
                }
            }
        };
        return listener;
    }
}
