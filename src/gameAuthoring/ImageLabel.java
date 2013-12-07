package gameAuthoring;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ImageLabel extends JLabel {

    private Image myImage;
    private File myImageSource;
    private String myID;

    public ImageLabel () {

    }

    public ImageLabel (String id) {
        myID = id;
    }

    public ImageLabel (int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
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

    public void transferImageInformation (ImageLabel other) {
        myImage = other.getImage();
        myImageSource = other.getImageFile();
        myID = other.getID();
        this.setLabelIcon(myImageSource);
    }
}
