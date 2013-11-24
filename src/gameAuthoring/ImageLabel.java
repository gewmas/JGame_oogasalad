package gameAuthoring;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class ImageLabel extends JLabel {

    private Image myImage;
    private File myImageSource;

    public ImageLabel (File imageSource) {
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

    public File getImageFile () {
        return myImageSource;
    }

    public Image getImage () {
        return myImage;
    }
}
