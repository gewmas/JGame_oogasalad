package gameAuthoring;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ImageLabel extends JLabel {

    private Image myImage;

    public ImageLabel (Image image) {
        myImage = image;
        this.setIcon(new ImageIcon(image));
    }

    public Image getImagePath () {
        return myImage;
    }
}
