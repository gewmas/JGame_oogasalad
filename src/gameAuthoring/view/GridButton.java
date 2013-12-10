package gameAuthoring.view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;


/**
 * GridButton is a modified JButton that is used to create a Grid. Each GridButton stores its own
 * coordinates, whether it is a path start or end, and an image.
 * 
 * @author Rebecca Lai
 * 
 */
@SuppressWarnings("serial")
public class GridButton extends JButton {

    private Point2D myCoordinate;
    private boolean isPath;
    private Grid myGrid;
    private File myImgSource;
    private JPopupMenu myPopupMenu;
    private JMenuItem myStartOptionItem;
    private JMenuItem myEndOptionItem;

    /**
     * Creates new GridButton
     * 
     * @param x is x coordinate of GridButton
     * @param y is y coordinate of GridButton
     * @param grid is Grid to which button is added
     */
    public GridButton (int x, int y, Grid grid) {
        myCoordinate = new Point2D.Double(x, y);
        myGrid = grid;
        isPath = false;
        this.setBorder(StyleConstants.DEFAULT_PANEL_BORDER);
        addPathListener(this);
    }

    /**
     * Set image for the GridButton
     * 
     * @param imgSource is image of GridButton
     */
    public void setImageSource (File imgSource) {
        myImgSource = imgSource;
    }

    /**
     * Allows user to set path start/end by right clicking on GridButton
     * Allows user to make GridButton part of path by left clicking on it
     * 
     * @param gButton is GridButton that responds to mouse action
     */
    private void addPathListener (final GridButton gButton) {
        MouseAdapter listener = new MouseAdapter() {

            @Override
            public void mouseClicked (MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    isPath = !isPath;
                    if (isPath) {
                        myGrid.addPathCoordinate(myCoordinate);
                        setImage();
                        myGrid.addPathCoordinate(myCoordinate);
                    }
                    else {
                        myGrid.removePathCoordinate(myCoordinate);
                        gButton.setIcon(null);
                    }
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    myPopupMenu = new JPopupMenu();
                    myStartOptionItem =
                            new JMenuItem(
                                          StyleConstants.resourceBundle
                                                  .getString("GridButtonStartMessage"));
                    myStartOptionItem.addMouseListener(new MouseAdapter() {
                        public void mousePressed (MouseEvent me) {
                            myGrid.setPathStart(myCoordinate);
                        }
                    });
                    myEndOptionItem =
                            new JMenuItem(
                                          StyleConstants.resourceBundle
                                                  .getString("GridButtonEndMessage"));
                    myEndOptionItem.addMouseListener(new MouseAdapter() {
                        public void mousePressed (MouseEvent me) {
                            myGrid.setPathEnd(myCoordinate);
                        }
                    });
                    myPopupMenu.add(myStartOptionItem);
                    myPopupMenu.add(myEndOptionItem);
                    myStartOptionItem.addActionListener(new ActionListener() {
                        public void actionPerformed (ActionEvent e) {
                        }
                    });
                    gButton.addMouseListener(new MouseAdapter() {
                        public void mousePressed (MouseEvent me) {
                            if (me.isPopupTrigger()) {
                                myPopupMenu.show(me.getComponent(), me.getX(), me.getY());
                            }
                        }

                        public void mouseReleased (MouseEvent Me) {
                            if (Me.isPopupTrigger()) {
                                myPopupMenu.show(Me.getComponent(), Me.getX(), Me.getY());
                            }
                        }
                    });
                }
            }
        };
        gButton.addMouseListener(listener);
    }

    /**
     * Set image for GridButton if image has been defined
     */
    public void setImage () {
        try {
            if (myImgSource == null) {
                JOptionPane.showMessageDialog(null, StyleConstants.resourceBundle
                        .getString("GridButtonImageUndefined"));
            }
            else {
                Image path = ImageIO.read(myImgSource);
                Image resized = path.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resized);
                this.setIcon(icon);
            }
        }
        catch (IOException ex) {
            System.out.println(StyleConstants.resourceBundle.getString("GridButtonImageNotFound"));
        }
    }

    /**
     * Set GridButton's path status to false
     */
    public void setPathStatusFalse () {
        isPath = false;
    }

    /**
     * Set GridButton's path status to true
     */
    public void setPathStatusTrue () {
        isPath = true;
    }

    /**
     * @return path status of GridButton
     */
    public boolean isPath () {
        return isPath;
    }

    public Point2D getCoordinate () {
        return myCoordinate;
    }

}
