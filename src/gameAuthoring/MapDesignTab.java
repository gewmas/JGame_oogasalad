package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
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


public class MapDesignTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/img");
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
        JPanel mainPanel = new GradientPanel(new GridBagLayout());
        JPanel gridPanel = new JPanel(new BorderLayout());
        myGrid = new Grid(20, 20);
        JLabel title = new JLabel("Map Design");
        title.setFont(new Font("Calibri", Font.PLAIN, 30));
        title.setForeground(new Color(80, 80, 80));
        // JLabel label = new JLabel("Current path image");

        myCurrentPathImage = new JButton("Choose path image");
        myCurrentPathImage.setFont(Constants.mapFont);
        myCurrentPathImage.addMouseListener(createPathListener());

        JButton checkPath = new JButton("Create Map");
        checkPath.setFont(Constants.mapFont);
        checkPath.addMouseListener(createPathCheckListener());

        JButton setBackground = new JButton("Set background image");
        setBackground.setFont(Constants.mapFont);
        setBackground.addMouseListener(createGridBackgroundListener(myGrid));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(title);
        gridPanel.add(myGrid, BorderLayout.WEST);

        // Add grid panel
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(gridPanel, c);

        // Add path image button
        // c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(myCurrentPathImage, c);

        // Add background image button
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        mainPanel.add(setBackground, c);

        // Add check path button
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        mainPanel.add(checkPath, c);
        Border b = BorderFactory.createLoweredBevelBorder();
        gridPanel.setBorder(b);
        return mainPanel;
    }

    public void loadJSON (Parser p) {
        myBackgroundImage = p.getString("BGImage");
        JSONObject map = p.getJSONObject("map");
        myPathImage = (String) map.get("pathImage");
        myGrid.setImageSource(new File(System.getProperties().getProperty("user.dir") + "/" +
                                       myPathImage));
        JSONArray pathPoints = (JSONArray) map.get("Path");
        myGrid.reset();

        for (int i = 0; i < pathPoints.length(); i++) {
            JSONObject point = (JSONObject) pathPoints.get(i);

            int x = (int) point.get("x");
            int y = (int) point.get("y");
            myGrid.toggleGridButton(x, y);

            if (i == 0) myGrid.setPathStart(new Point2D.Double(x, y));
            if (i == pathPoints.length() - 1) myGrid.setPathEnd(new Point2D.Double(x, y));
        }
        File f = new File(System.getProperties().getProperty("user.dir") + "/" + myPathImage);
        try {
            myCurrentPathImage.setIcon(new ImageIcon(ImageIO.read(f)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        setData();
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
                        myPathImage =
                                imgSource.toString().replace(System.getProperties()
                                        .getProperty("user.dir") + "/", "");
                        path = ImageIO.read(imgSource);
                        // myCurrentPathImage.setIcon(new ImageIcon(path));
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
                setData();
            }
        };
        return listener;
    }

    private void setData () {
        if (myGrid.isValidPathHelper()) {
            JOptionPane.showMessageDialog(null, "Valid path! Map Written");
            myGameData.setBackgroundImage(myBackgroundImage);
            myGameData.setMap(myPathImage, myGrid.getPathCoordinates());
        }
        else {
            JOptionPane.showMessageDialog(null,
                                          "Invalid path! Please fix path and try again");
        }
    }

    private MouseAdapter createGridBackgroundListener (final Grid grid) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    myBackgroundImage =
                            imgSource.toString().replace(System.getProperties()
                                    .getProperty("user.dir") + "/", "");
                    myGrid.setBackgroundImageSource(imgSource);
                }
            }
        };
        return listener;
    }

}
