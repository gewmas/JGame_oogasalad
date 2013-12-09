package gameAuthoring.view;

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


/**
 * @author Rebecca Lai & Susan Zhang
 *         ImageLabel is a label that holds information for a specific image file and allows for its
 *         display in the GUI. ImageLabel contains the image as an Image, as a File, and the image's
 *         ID. If the ImageLabel is mutable, it can "receive" information from another ImageLabel
 *         (which mimics click-and-drag from the User Library).
 * 
 */
@SuppressWarnings("serial")
public class ImageLabel extends JLabel {

    protected Image myImage;
    protected File myImageSource;
    protected String myID;
    protected boolean isMutable = false;

    /**
     * Creates new ImageLabel with basic initialization
     */
    public ImageLabel () {
        initialize();
    }

    /**
     * Creates new ImageLabel with an ID
     * 
     * @param id is string ID of an ImageLabel
     */
    public ImageLabel (String id) {
        myID = id;
        initialize();
    }

    /**
     * Creates new ImageLabel with specific width and height
     * 
     * @param width is specified width of ImageLabel
     * @param height is specified height of ImageLabel
     */
    public ImageLabel (int width, int height) {
        initialize();
        this.setPreferredSize(new Dimension(width, height));
    }

    /**
     * Initializes the ImageLabel to have a mouse listener and specific dimensions
     */
    protected void initialize () {
        this.addMouseListener(createImageLabelListener(this));
        Border border = BorderFactory.createLineBorder(new Color(100, 100, 100), 2);
        this.setBorder(border);
        this.setPreferredSize(new Dimension(50, 50));
    }

    /**
     * Makes ImageLabel mutable
     * If ImageLabel is mutable, it can receive information from another ImageLabel
     */
    public void setMutableStatusTrue () {
        isMutable = true;
        this.setToolTipText("Drag a pre-defined image from the Image Library onto this button to set it");
    }

    /**
     * Changes ImageLabel to display image
     * 
     * @param imageSource is image file that ImageLabel should store and display
     */
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

    /**
     * @return ID of image stored in ImageLabel
     */
    public String getID () {
        return myID;
    }

    /**
     * @return File representation of image stored in ImageLabel
     */
    public File getImageFile () {
        return myImageSource;
    }

    /**
     * @return Image representation of image stored in ImageLabel
     */
    public Image getImage () {
        return myImage;
    }

    /**
     * Allows for information transfer between two ImageLabels
     * 
     * @param other is ImageLabel that is passing information to this ImageLabel
     */
    protected void transferLabelInformation (ImageLabel other) {
        myImage = other.getImage();
        myImageSource = other.getImageFile();
        myID = other.getID();
        if (myImageSource != null) {
            this.setLabelIcon(myImageSource);
        }
    }

    /**
     * Resets parameters of ImageLabel so that label is resusable
     */
    public void reset () {
        myImage = null;
        myImageSource = null;
        myID = null;
        this.setIcon(null);
    }

    /**
     * Allows for click-and-drag between ImageLabels
     * 
     * @param label is the ImageLabel that receives information when clicked
     * @return MouseAdapater that transfers image information between two ImageLabels
     */
    private MouseAdapter createImageLabelListener (final ImageLabel label) {
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
                    GameAuthoringGUI.setCursorDefault();
                }
                if (!selected) {
                    GameAuthoringGUI.setCursorDefault();
                    GameAuthoringGUI.myImageLabel = null;
                }
            }
        };
        return listener;
    }

}
