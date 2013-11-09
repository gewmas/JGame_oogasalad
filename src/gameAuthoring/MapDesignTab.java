package gameAuthoring;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MapDesignTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    private Grid myGrid;
    private JButton myCurrentImage;

    public MapDesignTab () {

    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel(new GridBagLayout());
        myGrid = new Grid(5, 5);
        GridBagConstraints c = new GridBagConstraints();
        JLabel label = new JLabel("Current path image");
        myCurrentImage = new JButton();
        myCurrentImage.setPreferredSize(new Dimension(50, 50));
        myCurrentImage.addMouseListener(createPathListener());
        JButton checkPath = new JButton("Check if path is valid");
        checkPath.addMouseListener(createPathCheckListener());
        c.gridwidth = 3;
        c.gridx = 2;
        c.insets = new Insets(10, 10, 10, 10);
        panel.add(label, c);
        panel.add(myCurrentImage, c);
        panel.add(myGrid, c);
        panel.add(checkPath, c);
        return panel;
    }

    public MouseAdapter createPathListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    System.out.println(INPUT_CHOOSER.getSelectedFile());
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    myGrid.setImageSource(imgSource);
                    Image path;
                    try {
                        path = ImageIO.read(imgSource);
                        myCurrentImage.setIcon(new ImageIcon(path));
                    }
                    catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        };
        return listener;
    }

    public MouseAdapter createPathCheckListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                if (myGrid.isValidPathHelper()) {
                    JOptionPane.showMessageDialog(null, "Valid path!");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid path!");
                }
            }
        };
        return listener;
    }
}
