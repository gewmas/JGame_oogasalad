package gameAuthoring;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Grid extends JPanel {

    private GridButton[][] myGrid;
    private Point2D myStart;
    private Point2D myEnd;
    private Collection<Point2D> myPathCoordinates = new ArrayList<Point2D>();
    private File myBackgroundImage;
    private int myWidth;
    private int myHeight;

    public Grid (int width, int height) {
        this.setLayout(new GridLayout(width, height));
        this.setBackground(Color.blue);
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

    public void reset () {
        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                myGrid[i][j].setPathStatusFalse();
                myGrid[i][j].setIcon(null);
            }
        }
    }

    private MouseAdapter addPathDoneListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                if (isValidPath(0, 0, 2, 2)) {
                    for (Point2D point : myPathCoordinates) {
                        System.out.println(point.toString());
                    }
                }
            }
        };
        return listener;
    }

    public void setImageSource (File imgSource) {
        for (int x = 0; x < myGrid.length; x++) {
            for (int y = 0; y < myGrid[0].length; y++) {
                myGrid[x][y].setImageSource(imgSource);
            }
        }
    }

    public void setBackgroundImageSource (File bgSource) {
        myBackgroundImage = bgSource;
    }

    public void addCoordinate (Point2D coordinate) {
        myPathCoordinates.add(coordinate);
    }

    public void removeCoordinate (Point2D coordinate) {
        myPathCoordinates.remove(coordinate);
    }

    public void setPathStart (Point2D start) {
        myStart = start;
        System.out.println(myStart.toString());
    }

    public Point2D getPathStart () {
        return myStart;
    }

    public void setPathEnd (Point2D end) {
        myEnd = end;
        System.out.println(myEnd.toString());
    }

    public Point2D getPathEnd () {
        return myEnd;
    }

    public Collection<Point2D> getPathCoordinates () {
        return myPathCoordinates;
    }

    public void toggleGridButton (int x, int y) {
        myGrid[x][y].toggle();
    }

    public boolean isValidPathHelper () {
        if (myStart == null || myEnd == null) {
            JOptionPane.showMessageDialog(null, "Start or Endpoint not defined!");
            return false;
        }

        myPathCoordinates.clear();
        /*
         * for (int i = 0; i < myPath[0].length; i++) {
         * for (int j = 0; j < myPath.length; j++) {
         * System.out.print(myGrid[i][j].isPath() ? '.' : 'x');
         * }
         * System.out.println();
         * }
         */
        return isValidPath((int) myStart.getX(), (int) myStart.getY(), (int) myEnd.getX(),
                           (int) myEnd.getY());
    }

    public boolean isValidPath (int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= myGrid.length || startY < 0 || startY >= myGrid[0].length) { return false; }
        if (!myGrid[startX][startY].isPath()) { return false; }
        if (!myPathCoordinates.contains(myGrid[startX][startY].getCoordinate())) {
            myPathCoordinates.add(myGrid[startX][startY].getCoordinate());
        }
        else {
            return false;
        }

        if (startX == endX && startY == endY) {
            for (Point2D point : myPathCoordinates) {
                System.out.println(point.toString());
            }
            return true;
        }

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
                // TODO Auto-generated catch block
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

    public static void main (String[] args) {
        Grid g = new Grid(4, 4);
    }

}
