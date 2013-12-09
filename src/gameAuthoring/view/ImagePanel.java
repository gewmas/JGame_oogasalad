package gameAuthoring.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * @author BecLai
 *         Modified JPanel that is painted with a specified image as background
 */
public class ImagePanel extends JPanel {

    private String myImageName;

    /**
     * @param imageName
     */
    public ImagePanel (String imageName) {
        myImageName = imageName;
    }

    public ImagePanel (LayoutManager layoutManager) {
        super(layoutManager);
        this.setOpaque(false);
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
        int h = img.getHeight(null);
        int w = img.getWidth(null);

        if (w > this.getWidth() || w < this.getWidth()) {
            img = img.getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT);
            h = img.getHeight(null);
        }
        if (h > this.getHeight() || h < this.getHeight()) {
            img = img.getScaledInstance(-1, getHeight(), Image.SCALE_DEFAULT);
        }
        int x = (getWidth() - img.getWidth(null)) / 2;
        int y = (getHeight() - img.getHeight(null)) / 2;
        grphcs.drawImage(img, x, y, null);
    }

}
