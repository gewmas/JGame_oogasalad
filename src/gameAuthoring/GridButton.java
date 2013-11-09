package gameAuthoring;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;


public class GridButton extends JButton {

    private int myX;
    private int myY;
    private JButton myButton;
    private boolean isPath;
    private Grid myGrid;
    private File myImgSource;
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir"));

    public GridButton (int x, int y, Grid grid) {
        myX = x;
        myY = y;
        myGrid = grid;
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(50, 50));
        addPathListener(this);
    }

    public void setImageSource (File imgSource) {
        myImgSource = imgSource;
    }

    private void addPathListener (final GridButton gButton) {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                isPath = true;
                myGrid.addCoordinate(new Point2D.Double(myX, myY));
                try {
                    Image path = ImageIO.read(myImgSource);
                    gButton.setIcon(new ImageIcon(path));
                }
                catch (IOException ex) {
                    System.out.println("Image not found");
                }
            }
        };
        gButton.addMouseListener(listener);
    }

    public boolean isPath () {
        return isPath;
    }

}
