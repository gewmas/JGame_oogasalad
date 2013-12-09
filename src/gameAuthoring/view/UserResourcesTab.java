package gameAuthoring.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


public class UserResourcesTab {

    protected JPanel myMainPanel = new GradientPanel(new MigLayout("wrap 1"));
    protected JPanel mySubPanel = new JPanel(new MigLayout("wrap 1"));
    protected int myNumItems = 0;
    protected static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/img");

    public JPanel getTab () {
        myMainPanel.setOpaque(false);
        mySubPanel.setPreferredSize(new Dimension(300, 500));
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
                        myNumItems++;
                        ImageLabel imageLabel = new ImageLabel(" " + myNumItems);
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
