package gameAuthoring;

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
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/img");

    public JPanel getTab () {
        myMainPanel.setOpaque(false);
        mySubPanel.setPreferredSize(new Dimension(300, 500));
        JButton uploadImage = new JButton("Load image");
        uploadImage.setFont(Constants.defaultBodyFont);
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
                        ImageLabel imageLabel = new ImageLabel(image);
                        imageLabel.addMouseListener(addIconListener(userImagesTab, imageLabel));
                        mySubPanel.add(imageLabel);
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return listener;
    }

    public MouseAdapter addIconListener (final UserImagesTab userImagesTab, final ImageLabel image) {
        MouseAdapter listener = new MouseAdapter() {

            private boolean imageSelected = false;

            @Override
            public void mouseClicked (MouseEvent e) {
                imageSelected = !imageSelected;
                if (imageSelected) {
                    userImagesTab.setChanged();
                    userImagesTab.notifyObservers(image.getImagePath());
                    userImagesTab.clearChanged();
                }
                else {
                    System.out.println("Set back to default cursor");
                    userImagesTab.setChanged();
                    userImagesTab.notifyObservers(0);
                    userImagesTab.clearChanged();
                }
            }
        };
        return listener;
    }
}
