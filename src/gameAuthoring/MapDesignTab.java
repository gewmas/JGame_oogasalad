package gameAuthoring;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


public class MapDesignTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));
    private Grid myGrid;
    private JButton myCurrentImage;

    public MapDesignTab (GameData gameData) {
        super(gameData);
    }

    // TODO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setPreferredSize(new Dimension(500, 500));
        myGrid = new Grid(5, 5);
        JLabel title = new JLabel("Map Design");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        JLabel label = new JLabel("Current path image");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        myCurrentImage = new JButton();
        myCurrentImage.setPreferredSize(new Dimension(50, 50));
        myCurrentImage.addMouseListener(createPathListener());
        JButton checkPath = new JButton("Check if path is valid");
        checkPath.addMouseListener(createPathCheckListener());
        JButton setBackground = new JButton("Set background image");
        setBackground.addMouseListener(createGridBackgroundListener(myGrid));
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(title, "span 2");
        gridPanel.add(label, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridPanel.add(myCurrentImage, c);
        gridPanel.add(myGrid, c);
        gridPanel.add(checkPath, c);
        gridPanel.add(setBackground, c);
        mainPanel.add(gridPanel, "span 2, align center");
        Border b = BorderFactory.createLoweredBevelBorder();
        gridPanel.setBorder(b);
        return mainPanel;
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

    public MouseAdapter createGridBackgroundListener (final Grid grid) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    System.out.println(INPUT_CHOOSER.getSelectedFile());
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    myGrid.setBackgroundImageSource(imgSource);
                }
            }
        };
        return listener;
    }
}
