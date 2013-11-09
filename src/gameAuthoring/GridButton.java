package gameAuthoring;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Observable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class GridButton extends Observable {

    private int myX;
    private int myY;
    private JButton myButton;
    private boolean isPath;

    public GridButton (int x, int y) {
        myX = x;
        myY = y;
    }

    public JButton create () {
        myButton = new JButton();
        myButton.setPreferredSize(new Dimension(50, 50));
        try {
            Image grass = ImageIO.read(getClass().getResource("grass_tile.jpg"));
            myButton.setIcon(new ImageIcon(grass));
        }
        catch (IOException ex) {
            System.out.println("Image not found");
        }
        addPathListener();
        return myButton;
    }

    private void addPathListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                Image pressed;
                isPath = true;
                try {
                    pressed = ImageIO.read(getClass().getResource("dirt_tile.jpg"));
                    myButton.setIcon(new ImageIcon(pressed));
                    setChanged();
                    notifyObservers(new Point2D.Double(myX, myY));
                    clearChanged();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };
        myButton.addMouseListener(listener);
    }

    public boolean isPath () {
        return isPath;
    }

}
