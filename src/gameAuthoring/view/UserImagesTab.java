package gameAuthoring.view;

import gameAuthoring.JSONObjects.ResourceJSONObject;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class UserImagesTab extends Observable {

    private JPanel myMainPanel = new GradientPanel(new MigLayout("wrap 1"));
    private JPanel mySubPanel = new JPanel(new MigLayout("wrap 1"));
    private int myNumImages = 0;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/src/resources/img");

    public UserImagesTab () {
    }

    public JPanel getTab () {
        myMainPanel.setOpaque(false);
        mySubPanel.setPreferredSize(new Dimension(300, 500));
        JButton uploadImage = new JButton("Load image");
        uploadImage.setFont(StyleConstants.DEFAULT_BODY_FONT);
        uploadImage.addMouseListener(addFileUploadListener(this));
        JScrollPane scrollPane = new JScrollPane(mySubPanel);
        myMainPanel.add(scrollPane);
        myMainPanel.add(uploadImage, "align center, gap 10 10 30 10");
        return myMainPanel;
    }

    public MouseAdapter addFileUploadListener (final UserImagesTab userImagesTab) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    Image image;
                    try {
                        image = ImageIO.read(imgSource);
                        myNumImages++;
                        String id = "" + myNumImages;
                        ImageLabel imageLabel = new ImageLabel("" + myNumImages);
                        ResourceJSONObject imageResource =
                                new ResourceJSONObject(id, imgSource.getName());
                        setChanged();
                        notifyObservers(imageResource);
                        clearChanged();
                        imageLabel.setLabelIcon(imgSource);
                        GameAuthoringGUI.myImageLabel = imageLabel;
                        mySubPanel.add(imageLabel);
                        mySubPanel.revalidate();
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return listener;
    }
}
