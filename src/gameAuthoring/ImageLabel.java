package gameAuthoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class ImageLabel extends JLabel {

    private Image myImage;
    private File myImageSource;
    private File myAudioSource;
    private String myID;
    private boolean isMutable = false;

    public ImageLabel () {
        initialize();
    }

    public ImageLabel (String id) {
        myID = id;
        initialize();
    }

    public void setMutableStatusTrue () {
        isMutable = true;
    }

    public ImageLabel (int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        initialize();
    }

    public void initialize () {
        this.addMouseListener(createImageListener(this));
        this.addMouseListener(addCursorListener(this));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        this.setBorder(border);
    }

    public void setLabelIcon (File imageSource) {
        myImageSource = imageSource;
        try {
            myImage = ImageIO.read(imageSource);
            myImage = myImage.getScaledInstance(50, 50, Image.SCALE_FAST);
            this.setIcon(new ImageIcon(myImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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

    public File getImageFile () {
        return myImageSource;
    }

    public Image getImage () {
        return myImage;
    }

    public void transferLabelInformation (ImageLabel other) {
        myImage = other.getImage();
        myImageSource = other.getImageFile();
        myID = other.getID();
        myAudioSource = other.getAudioFile();
        if (myImageSource != null) {
            this.setLabelIcon(myImageSource);
        }
    }

    public MouseAdapter createImageListener (final ImageLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                if (GameAuthoringGUI.myImageLabel == null) {
                    GameAuthoringGUI.myImageLabel = label;
                    GameAuthoringGUI.myImageLabel.setLabelIcon(myImageSource);
                }
                else if (isMutable) {
                    transferLabelInformation(GameAuthoringGUI.myImageLabel);
                }
            }
        };
        return listener;
    }

    public MouseAdapter addCursorListener (final ImageLabel image) {
        MouseAdapter listener = new MouseAdapter() {
            private boolean imageSelected = false;

            @Override
            public void mouseClicked (MouseEvent e) {
                imageSelected = !imageSelected;
                if (imageSelected) {
                    GameAuthoringGUI.setCursor(image.getImageFile());
                    GameAuthoringGUI.myImageLabel = image;
                }
                else {
                    GameAuthoringGUI.setCursorNull();
                    GameAuthoringGUI.myImageLabel = null;
                }
            }
        };
        return listener;
    }

}
