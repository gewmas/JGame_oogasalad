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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class GridButton extends JButton {

    private Point2D myCoordinate;
    private JButton myButton;
    private boolean isPath;
    private Grid myGrid;
    private File myImgSource;

    public GridButton (int x, int y, Grid grid) {
        myCoordinate = new Point2D.Double(x, y);
        myGrid = grid;
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
                isPath = !isPath;
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (isPath) {
                        myGrid.addCoordinate(myCoordinate);
                        try {
                            if (myImgSource == null) {
                                JOptionPane.showMessageDialog(null, "No image defined");
                            }
                            else {
                                Image path = ImageIO.read(myImgSource);
                                gButton.setIcon(new ImageIcon(path));
                                gButton.setPreferredSize(new Dimension(50, 50));
                            }
                        }
                        catch (IOException ex) {
                            System.out.println("Image not found");
                        }
                    }
                    else {
                        myGrid.removeCoordinate(myCoordinate);
                        gButton.setIcon(null);
                    }
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    String[] options = { "Set as start", "Set as end" };
                    String choice =
                            (String) JOptionPane.showInputDialog(
                                                                 null,
                                                                 "Set path start and end:",
                                                                 "Set path start and end",
                                                                 JOptionPane.PLAIN_MESSAGE,
                                                                 null,
                                                                 options,
                                                                 "");
                    if (choice.equals("Set as start")) {
                        myGrid.setPathStart(myCoordinate);
                    }
                    if (choice.equals("Set as end")) {
                        myGrid.setPathEnd(myCoordinate);
                    }
                }
            }
        };
        gButton.addMouseListener(listener);
    }

    public boolean isPath () {
        return isPath;
    }

    public Point2D getCoordinate () {
        return myCoordinate;
    }

}
