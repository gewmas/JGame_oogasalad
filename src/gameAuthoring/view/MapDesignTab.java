package gameAuthoring.view;

import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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


/**
 * @author Rebecca Lai & Susan Zhang
 *         MapDesignTab allows for users to design a map with a path for a Tower Defense game. Users
 *         can select images to use for the path tiles and map background, define start/end points
 *         for the path, and check path integrity. MapDesignTab inherits from Tab and is thus an
 *         Observable. When all fields in MapDesignTab are set, its observers are notified and a
 *         MapDesignInformation object is passed to them, allowing for map information to be
 *         extracted into GameData.
 */
public class MapDesignTab extends Tab {

    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/src/resources/img");
    private Grid myGrid;
    private ImageLabel myCurrentPathImage;
    private String myBackgroundImage;
    private String myPathImage;
    private JPanel myGridPanel;
    private JPanel myButtonPanel;
    private static final String PATH_LABEL_FORMATTING = "gap 0 0 0 30";

    /**
     * Creates new MapDesignTab
     */
    public MapDesignTab () {
    }

    @Override
    public JPanel getTab () {
        myMainPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myGridPanel = new JPanel(new BorderLayout());
        myButtonPanel = new JPanel(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myButtonPanel.setOpaque(false);
        addTitle();
        addGrid();
        addPathImage();
        addBackgroundButton();
        addSubmitMapButton();
        myMainPanel.add(myButtonPanel);
        Border b = BorderFactory.createLoweredBevelBorder();
        myGridPanel.setBorder(b);
        return myMainPanel;
    }

    /**
     * Adds button for selecting map background image
     */
    private void addBackgroundButton () {
        JButton setBackground =
                new JButton(StyleConstants.resourceBundle.getString("MapBackground"));
        setBackground.setFont(StyleConstants.DEFAULT_BODY_FONT);
        setBackground.addMouseListener(createGridBackgroundListener(myGrid));
        setBackground
                .setToolTipText(StyleConstants.resourceBundle.getString("MapBackgroundTip"));
        myButtonPanel.add(setBackground, StyleConstants.DEFAULT_SPAN_MODE);
    }

    /**
     * Adds button for checking validity of path and setting it
     */
    private void addSubmitMapButton () {
        JButton checkPath = new JButton(StyleConstants.resourceBundle.getString("CreateMap"));
        checkPath.setFont(StyleConstants.DEFAULT_BODY_FONT);
        checkPath.addMouseListener(createPathCheckListener());
        checkPath
                .setToolTipText(StyleConstants.resourceBundle.getString("MapPathCheckTip"));
        myButtonPanel.add(checkPath);
    }

    /**
     * Adds title to tab
     */
    private void addTitle () {
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("MapDesignTitle"));
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, StyleConstants.DEFAULT_SPAN_MODE);
    }

    /**
     * Creates and adds new Grid to tab
     */
    private void addGrid () {
        myGrid = new Grid(20, 20);
        myGridPanel.add(myGrid, BorderLayout.WEST);
        myMainPanel.add(myGridPanel);
    }

    /**
     * Adds label and ImageLabel to allow specification of path tile image
     */
    private void addPathImage () {
        myCurrentPathImage = new ImageLabel(50, 50);
        myCurrentPathImage.setMutableStatusTrue();
        myCurrentPathImage.addMouseListener(createPathListener());
        JLabel pathLabel = new JLabel(StyleConstants.resourceBundle.getString("MapPath"));
        pathLabel.setFont(StyleConstants.DEFAULT_BODY_FONT);
        pathLabel.setToolTipText(StyleConstants.resourceBundle.getString("MapPathTip"));
        myButtonPanel.add(pathLabel, PATH_LABEL_FORMATTING);
        myButtonPanel.add(myCurrentPathImage, PATH_LABEL_FORMATTING);
    }

    public void loadJSON (Parser p) {
        JSONObject resources = p.getJSONObject("resources");
        JSONArray images = (JSONArray) resources.get("image");

        Map<String, String> imageMap = new HashMap<String, String>();
        for (int i = 0; i < images.length(); i++) {
            JSONObject image = images.getJSONObject(i);
            String id = image.getString("id");
            String url = image.getString("url");
            imageMap.put(id, url);
        }
        
        myBackgroundImage = p.getString("BGImage") + ".png";
        JSONObject map = p.getJSONObject("map");
        myPathImage = (String) map.get("pathImage");
        myGrid.setImageSource(new File(GameAuthoringGUI.FILE_PREFIX + myPathImage));
        myGrid.setBackgroundImageSource(new File(GameAuthoringGUI.FILE_PREFIX + myBackgroundImage));
        
        JSONArray pathPoints = (JSONArray) map.get("Path");
        myGrid.reset();
        for (int i = 0; i < pathPoints.length(); i++) {
            JSONObject point = (JSONObject) pathPoints.get(i);
            int x = (Integer) point.get("y");
            int y = (Integer) point.get("x");
            myGrid.toggleGridButton(x, y);
            if (i == 0) myGrid.setPathStart(new Point2D.Double(x, y));
            if (i == pathPoints.length() - 1) myGrid.setPathEnd(new Point2D.Double(x, y));
        }
        File f = new File(GameAuthoringGUI.FILE_PREFIX +  myPathImage);
        try {
            myCurrentPathImage.setIcon(new ImageIcon(ImageIO.read(f)));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        setPathData();
    }

    /**
     * Sets path tiles to specified image
     * 
     * @return MouseAdapter that allows for tiles of Grid to be set to path images
     */
    private MouseAdapter createPathListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                File imgSource = GameAuthoringGUI.myImageLabel.getImageFile();
                myGrid.setImageSource(imgSource);
                Image path;
                try {
                    myPathImage =
                            GameAuthoringGUI.myImageLabel.getImageFile().getName();
                    path = ImageIO.read(imgSource).getScaledInstance(50, 50, Image.SCALE_FAST);
                    myCurrentPathImage.setIcon(new ImageIcon(path));
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
        return listener;
    }

    /**
     * Set user-defined map/path data
     * 
     * @return MouseAdapter that allows for map/path data to be set
     */
    private MouseAdapter createPathCheckListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                setPathData();
            }
        };
        return listener;
    }

    /**
     * If path defined by user is valid, send map/path information to observers
     */
    private void setPathData () {
        if (myGrid.isValidPathHelper()) {
            JOptionPane.showMessageDialog(null,
                                          StyleConstants.resourceBundle.getString("MapValidPath"));
            String key = myBackgroundImage.substring(0, myBackgroundImage.length() - 4);
            MapDesignInformation mapDesignInfo =
                    new MapDesignInformation(myBackgroundImage, myPathImage, key,
                                             myGrid.getPathCoordinates());
            setChanged();
            notifyObservers(mapDesignInfo);
            clearChanged();
        }
        else {
            JOptionPane
                    .showMessageDialog(null,
                                       StyleConstants.resourceBundle.getString("MapInvalidPath"));
        }
    }

    /**
     * Allows users to define background image for map
     * 
     * @param grid is grid for which background is set
     * @return MouseAdapter allowing for map background image to be set upon click
     */
    private MouseAdapter createGridBackgroundListener (final Grid grid) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                int loadObject = INPUT_CHOOSER.showOpenDialog(null);
                if (loadObject == JFileChooser.APPROVE_OPTION) {
                    File imgSource = INPUT_CHOOSER.getSelectedFile();
                    myBackgroundImage = imgSource.getName();
                    myGrid.setBackgroundImageSource(imgSource);
                }
            }
        };
        return listener;
    }

}
