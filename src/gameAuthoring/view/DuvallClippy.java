package gameAuthoring.view;

import java.awt.Dimension;
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


public class DuvallClippy extends JFrame implements Observer {

    private Map<String, String> myDisplayTips = new HashMap<String, String>();
    private String myDisplayString = "test";
    private JTextArea myDisplayBox;

    public DuvallClippy () {
        JPanel mainPanel = new ImagePanel("rainbow_texture.png");
        this.setPreferredSize(new Dimension(300, 300));
        JLabel duvallClippy = new JLabel();
        Image duvallImage;
        try {
            duvallImage = ImageIO.read(this.getClass().getResource("duvall_clippy.png"));
            duvallClippy.setIcon(new ImageIcon(duvallImage));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        mainPanel.add(duvallClippy);
        myDisplayBox = new JTextArea(myDisplayString);
        myDisplayBox.setPreferredSize(new Dimension(100, 100));
        JScrollPane scrollPane = new JScrollPane(myDisplayBox);
        mainPanel.add(scrollPane);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
        this.setResizable(false);
        fillDisplayTipsMap();
    }

    @Override
    public void update (Observable arg0, Object arg1) {
        String displayKey = (String) arg1;
        myDisplayString = myDisplayTips.get(displayKey);
        myDisplayBox.setText(myDisplayString);
    }

    public void fillDisplayTipsMap () {
        myDisplayTips.put("Basic Info", StyleConstants.resourceBundle.getString("BasicInfoHelp"));
        myDisplayTips.put("Map Design", "Blah1");
        myDisplayTips.put("Tower Design", "Blah2");
        myDisplayTips.put("Enemy Design", "Blah3");
        myDisplayTips.put("Wave Design", "Blah4");
        myDisplayTips.put("Temp Barrier Design", "Blah5");
        myDisplayTips.put("Skills Design", "Blah6");
    }

    public static void main (String[] args) {
        new DuvallClippy();
    }

}
