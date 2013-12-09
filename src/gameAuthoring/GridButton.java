package gameAuthoring;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;


public class GridButton extends JButton {

    private Point2D myCoordinate;
    private JButton myButton;
    private boolean isPath;
    private String mySelectionMode;
    private Grid myGrid;
    private File myImgSource;
    private JPopupMenu myPopupMenu;
    private JMenuItem myStartOptionItem;
    private JMenuItem myEndOptionItem;

    public GridButton (int x, int y, Grid grid) {
        myCoordinate = new Point2D.Double(x, y);
        myGrid = grid;
        isPath = false;
        this.setBorder(BorderFactory.createLineBorder(Color.white, 1));
        addPathListener(this);
    }

    public void setImageSource (File imgSource) {
        myImgSource = imgSource;
    }

    public void setSelectionMode (String mode) {
        mySelectionMode = mode;
    }

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
                    myStartOptionItem = new JMenuItem("Set as path start");
                    myStartOptionItem.addMouseListener(new MouseAdapter() {
                        public void mousePressed (MouseEvent me) {
                            System.out.println("Set as start");
                            myGrid.setPathStart(myCoordinate);
                        }
                    });
                    myEndOptionItem = new JMenuItem("Set as path end");
                    myEndOptionItem.addMouseListener(new MouseAdapter() {
                        public void mousePressed (MouseEvent me) {
                            System.out.println("Set as end");
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

    public void setImage () {
        try {
            if (myImgSource == null) {
                JOptionPane.showMessageDialog(null, "No image defined");
            }
            else {
                Image path = ImageIO.read(myImgSource);
                Image resized = path.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resized);
                this.setIcon(icon);
            }
        }
        catch (IOException ex) {
            System.out.println("Image not found");
        }
    }

    public void setPathStatusFalse () {
        isPath = false;
    }

    public void setPathStatusTrue () {
        isPath = true;
    }

    public boolean isPath () {
        return isPath;
    }

    public Point2D getCoordinate () {
        return myCoordinate;
    }

}
