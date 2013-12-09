package gameAuthoring.modifiedSwingComponents;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class ImageButton extends JButton {

    private String myImageName;

    public ImageButton (String imageName) {
        myImageName = imageName;
    }

    @Override
    protected void paintComponent (Graphics grphcs) {
        Image img = null;
        try {
            img = ImageIO.read(this.getClass().getResource(myImageName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        grphcs.drawImage(img, 0, 0, this);
        grphcs.finalize();
    }
}
