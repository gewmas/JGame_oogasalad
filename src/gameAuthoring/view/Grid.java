package gameAuthoring.view;

import gameAuthoring.modifiedSwingComponents.GridButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @author Rebecca Lai & Susan Zhang
 *         Grid is a special JPanel that holds a collection of GridButtons that represents a map. A
 *         path can be defined on the map by clicking individual GridButtons and defining path start
 *         and end points. Grid also contains methods that allow for path validation.
 */
@SuppressWarnings("serial")
public class Grid extends JPanel {

    private GridButton[][] myGrid;
    private Point2D myStart;
    private Point2D myEnd;
    private Collection<Point2D> myPathCoordinates = new ArrayList<Point2D>();
    private File myBackgroundImage;
    private int myWidth;
    private int myHeight;

    /**
     * Creates a new Grid
     * 
     * @param width is width of Grid (number of GridButtons in x direction)
     * @param height is height of Grid (number of GridButtons in y direction)
     */
    public Grid (int width, int height) {
        this.setLayout(new GridLayout(width, height));
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(500, 500));
        myWidth = width;
        myHeight = height;
        myGrid = new GridButton[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GridButton gButton = new GridButton(x, y, this);
                myGrid[x][y] = gButton;
                gButton.setOpaque(false);
                gButton.setContentAreaFilled(false);
                gButton.setBorderPainted(true);
                this.add(myGrid[x][y]);
            }
        }
    }

    /**
     * Clears the grid by removing path images from GridButtons and setting their path status to
     * false
     */
    public void reset () {
        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                myGrid[i][j].setPathStatusFalse();
                myGrid[i][j].setIcon(null);
            }
        }
    }

    /**
     * Set an image for each tile in the path
     * 
     * @param imgSource is image of each path tile
     */
    public void setImageSource (File imgSource) {
        for (int x = 0; x < myGrid.length; x++) {
            for (int y = 0; y < myGrid[0].length; y++) {
                myGrid[x][y].setImageSource(imgSource);
            }
        }
    }

    /**
     * Set a background image for the grid/map
     * 
     * @param bgSource is background image of map
     */
    public void setBackgroundImageSource (File bgSource) {
        myBackgroundImage = bgSource;
        this.revalidate();
    }

    /**
     * Add coordinate of new path tile
     * 
     * @param coordinate is coordinate (Point2D) of tile in path
     */
    public void addPathCoordinate (Point2D coordinate) {
        myPathCoordinates.add(coordinate);
    }

    /**
     * Remove tile from defined path
     * 
     * @param coordinate is coordianate (Point2D) of tile to be removed from path
     */
    public void removePathCoordinate (Point2D coordinate) {
        myPathCoordinates.remove(coordinate);
    }

    /**
     * Set path start
     * 
     * @param start is coordinate (Point2D) of path's beginning
     */
    public void setPathStart (Point2D start) {
        myStart = start;
    }

    /**
     * @return starting coordinate (Point2D) of path
     */
    public Point2D getPathStart () {
        return myStart;
    }

    /**
     * Set end of path
     * 
     * @param end is coordinate (Point2D) of path end
     */
    public void setPathEnd (Point2D end) {
        myEnd = end;
    }

    /**
     * @return ending coordinate (Point2D) of path
     */
    public Point2D getPathEnd () {
        return myEnd;
    }

    /**
     * @return collection of coordinates representing map path
     */
    public Collection<Point2D> getPathCoordinates () {
        return myPathCoordinates;
    }

    /**
     * Toggle image of a specific GridButton on and off
     * 
     * @param x is x coordinate of GridButton to be toggled
     * @param y is y coordinate of GridButton to be toggled
     */
    public void toggleGridButton (int x, int y) {
        myGrid[x][y].setImage();
    }

    /**
     * Helper method to check validity of user defined path
     * 
     * @return boolean representing whether defined path is valid
     */
    public boolean isValidPathHelper () {
        if (myStart == null || myEnd == null) {
            JOptionPane.showMessageDialog(null, StyleConstants.resourceBundle
                    .getString("GridStartEndMessage"));
            return false;
        }
        myPathCoordinates.clear();
        return isValidPath((int) myStart.getX(), (int) myStart.getY(), (int) myEnd.getX(),
                           (int) myEnd.getY());
    }

    /**
     * Checks validity of user defined path
     * 
     * @param startX is starting x coordinate of path
     * @param startY is starting y coordinate of path
     * @param endX is ending x coordinate of path
     * @param endY is ending y coordinate of path
     * @return
     */
    public boolean isValidPath (int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= myGrid.length || startY < 0 || startY >= myGrid[0].length) { return false; }
        if (!myGrid[startX][startY].isPath()) { return false; }
        if (!myPathCoordinates.contains(myGrid[startX][startY].getCoordinate())) {
            myPathCoordinates.add(myGrid[startX][startY].getCoordinate());
        }
        else {
            return false;
        }
        if (startX == endX && startY == endY) { return true; }
        return (isValidPath(startX + 1, startY, endX, endY) || isValidPath(startX, startY + 1,
                                                                           endX, endY) ||
                isValidPath(startX - 1, startY, endX, endY) || isValidPath(startX, startY - 1,
                                                                           endX, endY));
    }

    @Override
    public void paintComponent (Graphics page) {
        if (myBackgroundImage == null) {
            super.paintComponent(page);
        }
        else {
            super.paintComponent(page);
            Image img = null;
            try {
                img = ImageIO.read(myBackgroundImage);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            int h = img.getHeight(null);
            int w = img.getWidth(null);
            if (w > this.getWidth() || w < this.getWidth()) {
                img = img.getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT);
                h = img.getHeight(null);
            }
            if (h > this.getHeight() || h < this.getHeight()) {
                img = img.getScaledInstance(-1, getHeight(), Image.SCALE_DEFAULT);
            }
            int x = (getWidth() - img.getWidth(null)) / 2;
            int y = (getHeight() - img.getHeight(null)) / 2;
            page.drawImage(img, x, y, null);
        }
    }

}
