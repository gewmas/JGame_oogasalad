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
        this.addMouseListener(createImageLabelListener(this));
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
        if (myImageSource != null) {
            this.setLabelIcon(myImageSource);
        }
    }

    public void reset () {
        myImage = null;
        myImageSource = null;
        myID = null;
        this.setIcon(null);
    }

    public MouseAdapter createImageLabelListener (final ImageLabel label) {
        MouseAdapter listener = new MouseAdapter() {
            boolean selected = false;

            @Override
            public void mouseClicked (MouseEvent e) {
                selected = !selected;
                if (GameAuthoringGUI.myImageLabel == null && selected) {
                    GameAuthoringGUI.myImageLabel = label;
                    GameAuthoringGUI.setCursor(label.getImageFile());
                }
                if (GameAuthoringGUI.myImageLabel != null && isMutable && selected) {
                    label.transferLabelInformation(GameAuthoringGUI.myImageLabel);
                    GameAuthoringGUI.setCursorNull();
                }
                if (!selected) {
                    GameAuthoringGUI.setCursorNull();
                    GameAuthoringGUI.myImageLabel = null;
                }
            }
        };
        return listener;
    }

}
