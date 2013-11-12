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
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/src/gameAuthoring");
    private Grid myGrid;
    private JButton myCurrentPathImage;
    private String myBackgroundImage;
    private String myPathImage;
    

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
        myCurrentPathImage = new JButton();
        myCurrentPathImage.setPreferredSize(new Dimension(50, 50));
        myCurrentPathImage.addMouseListener(createPathListener());
        JButton checkPath = new JButton("Create Map");
        checkPath.addMouseListener(createPathCheckListener());
        JButton setBackground = new JButton("Set background image");
        setBackground.addMouseListener(createGridBackgroundListener(myGrid));
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(title, "span 2");
        gridPanel.add(label, c);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridPanel.add(myCurrentPathImage, c);
        gridPanel.add(myGrid, c);
        gridPanel.add(setBackground, c);
        gridPanel.add(checkPath, c);
        mainPanel.add(gridPanel, "span 2, align center");
        Border b = BorderFactory.createLoweredBevelBorder();
        gridPanel.setBorder(b);
        return mainPanel;
    }

    private MouseAdapter createPathListener () {
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
                        myPathImage = imgSource.toString().replace(System.getProperties().getProperty("user.dir"), "");
                        path = ImageIO.read(imgSource);
                        myCurrentPathImage.setIcon(new ImageIcon(path));
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        return listener;
    }

    private MouseAdapter createPathCheckListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                if (myGrid.isValidPathHelper()) {
                    JOptionPane.showMessageDialog(null, "Valid path! Map Written");
                    myGameData.setMap(myBackgroundImage, myPathImage, myGrid.getPathStart(), myGrid.getPathEnd(), myGrid.getPathCoordinates()); 
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid path! Please fix path and try again");
                }
            }
        };
        return listener;
    }

    private MouseAdapter createGridBackgroundListener (final Grid grid) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    myBackgroundImage = imgSource.toString().replace(System.getProperties().getProperty("user.dir"), "");                
                    myGrid.setBackgroundImageSource(imgSource);
                }
            }
        };
        return listener;
    }
    


}
