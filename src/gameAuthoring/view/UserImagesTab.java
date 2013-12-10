package gameAuthoring.view;

import gameAuthoring.JSONObjects.ResourceJSONObject;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai & Susan Zhang
 *         UserImagesTab stores images uploaded by the user. It allows the user to click and drag
 *         images in order to set images for various game fields.
 */
public class UserImagesTab extends Observable {

    private JPanel myContentPanel;
    private int myNumImages = 0;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/src/resources/img");
    private static final Dimension CONTENT_PANEL_DIMENSION = new Dimension(300, 500);
    private static final String CONTENT_PANEL_WRAP_MODE = "wrap 4";
    private static final String UPLOAD_IMAGE_BUTTON_FORMATTING = "align center, gap 10 10 30 10";
    private static final String MAIN_PANEL_WRAP_MODE = "wrap 1";
    private List<ImageLabel> myImageLabels;

    /**
     * Creates new UserImagesTab
     */
    public UserImagesTab () {
        myImageLabels = new ArrayList<ImageLabel>();
    }

    /**
     * Get main panel
     * 
     * @return main content panel of UserImagesTab
     */
    public JPanel getTab () {
        JPanel mainPanel = new GradientPanel(new MigLayout(MAIN_PANEL_WRAP_MODE));
        myContentPanel = new JPanel(new MigLayout(CONTENT_PANEL_WRAP_MODE));
        mainPanel.setOpaque(false);       
        JButton uploadImageButton =
                new JButton(StyleConstants.resourceBundle.getString("UserImagesTitle"));
        uploadImageButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        uploadImageButton.addMouseListener(addFileUploadListener(this));
        JScrollPane scrollPane = new JScrollPane(myContentPanel);
        scrollPane.setPreferredSize(CONTENT_PANEL_DIMENSION);
        mainPanel.add(scrollPane);
        mainPanel.add(uploadImageButton, UPLOAD_IMAGE_BUTTON_FORMATTING);
        return mainPanel;
    }

    /**
     * Select and upload image file to user image library
     * 
     * @param userImagesTab is UserImagesTab that needs to be updated with uploaded image
     * @return MouseAdapter that allows images to be uploaded to the tab
     */
    private MouseAdapter addFileUploadListener (final UserImagesTab userImagesTab) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    String id = "" + myNumImages;
                    addImageLabel(imgSource, id);
                }
            }
        };
        return listener;
    }
    
    public void addImageLabel(File imgSource, String id){      
        myNumImages++;
        ImageLabel imageLabel = new ImageLabel(id);
        ResourceJSONObject imageResource =
                new ResourceJSONObject(id, imgSource.getName());
        setChanged();
        notifyObservers(imageResource);
        clearChanged();
        imageLabel.setLabelIcon(imgSource);
        myImageLabels.add(imageLabel);
        GameAuthoringGUI.myImageLabel = imageLabel;
        myContentPanel.add(imageLabel);
        myContentPanel.revalidate();
    }
}
