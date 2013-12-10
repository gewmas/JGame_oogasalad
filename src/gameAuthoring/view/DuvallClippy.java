package gameAuthoring.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;


/**
 * @author Rebecca Lai
 *         DuvallClippy is the "Office Assistant" for the GameAuthoringGUI. As users navigate
 *         between design tabs, DuvallClippy provides an overview of each design tab.
 */
public class DuvallClippy extends JFrame implements Observer {

    private Map<String, String> myDisplayTips = new HashMap<String, String>();
    private String myDisplayString = StyleConstants.resourceBundle.getString("BasicInfoHelp");
    private JTextArea myDisplayBox;
    private static final String DEFAULT_BACKGROUND_IMAGE = "glitter.jpg";
    private static final String DEFAULT_WRAP_MODE = "wrap 2";
    private static final Dimension FRAME_DIMENSION = new Dimension(400, 300);
    private static final Dimension CLIPPY_PANEL_DIMENSION = new Dimension(200, 300);
    private static final String DUVALL_IMAGE = "duvall_clippy.png";
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 16);

    /**
     * Creates new DuvallClippy
     * Sets up frame with all components necessary for DuvallClippy (image and textbox)
     */
    public DuvallClippy () {
        JPanel mainPanel = new ImagePanel(DEFAULT_BACKGROUND_IMAGE);
        mainPanel.setLayout(new MigLayout(DEFAULT_WRAP_MODE));
        this.setPreferredSize(FRAME_DIMENSION);
        JPanel clippyPanel = new JPanel();
        clippyPanel.setPreferredSize(CLIPPY_PANEL_DIMENSION);
        clippyPanel.setOpaque(false);
        JLabel duvallClippy = new JLabel();
        Image duvallImage;
        try {
            duvallImage = ImageIO.read(this.getClass().getResource(DUVALL_IMAGE));
            duvallClippy.setIcon(new ImageIcon(duvallImage));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        clippyPanel.add(duvallClippy);
        mainPanel.add(clippyPanel);
        myDisplayBox = new JTextArea(myDisplayString);
        myDisplayBox.setFont(DEFAULT_FONT);
        myDisplayBox.setOpaque(false);
        myDisplayBox.setLineWrap(true);
        myDisplayBox.setWrapStyleWord(true);
        JScrollPane scrollPane =
                new JScrollPane(myDisplayBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setPreferredSize(CLIPPY_PANEL_DIMENSION);
        mainPanel.add(scrollPane);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.setResizable(false);
        fillDisplayTipsMap();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update (Observable arg0, Object arg1) {
        String displayKey = (String) arg1;
        myDisplayString = myDisplayTips.get(displayKey);
        myDisplayBox.setText(myDisplayString);
    }

    /**
     * Create map that allows for DuvallClippy's help tips to be displayed without "if" statements
     */
    public void fillDisplayTipsMap () {
        myDisplayTips.put("Basic Info", StyleConstants.resourceBundle.getString("BasicInfoHelp"));
        myDisplayTips.put("Map Design", StyleConstants.resourceBundle.getString("MapHelp"));
        myDisplayTips.put("Tower Design", StyleConstants.resourceBundle.getString("TowerHelp"));
        myDisplayTips.put("Enemy Design", StyleConstants.resourceBundle.getString("EnemyHelp"));
        myDisplayTips.put("Wave Design", StyleConstants.resourceBundle.getString("WaveHelp"));
        myDisplayTips.put("Temp Barrier Design",
                          StyleConstants.resourceBundle.getString("TempBarrierHelp"));
        myDisplayTips.put("Skills Design", StyleConstants.resourceBundle.getString("SkillsHelp"));
    }

    public static void main (String[] args) {
        new DuvallClippy();
    }

}
