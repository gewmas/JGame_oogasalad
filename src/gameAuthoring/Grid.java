package gameAuthoring;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JPanel;


public class Grid extends JPanel {

    GridButton[][] myGrid;
    boolean[][] myPath;
    private Point2D myStart;
    private Point2D myEnd;
    Collection<Point2D> myPathCoordinates = new ArrayList<Point2D>();

    public Grid (int width, int height) {
        this.setLayout(new GridLayout(width, height));
        myGrid = new GridButton[width][height];
        myPath = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GridButton gButton = new GridButton(x, y, this);
                myGrid[x][y] = gButton;
                myPath[x][y] = false;
                this.add(myGrid[x][y]);
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

    public void setPathEnd (Point2D end) {
        myEnd = end;
        System.out.println(myEnd.toString());
    }

    public boolean isValidPathHelper () {
        myPathCoordinates.clear();
        return isValidPath((int) myStart.getX(), (int) myStart.getY(), (int) myEnd.getX(),
                           (int) myEnd.getY());
    }

    public boolean isValidPath (int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= myGrid.length || startY < 0 || startY >= myGrid[0].length) { return false; }
        if (!myGrid[startX][startY].isPath()) { return false; }
        if (startX == endX && startY == endY) {
            System.out.println("path complete");
            for (Point2D point : myPathCoordinates) {
                System.out.println(point.toString());
            }
            return true;
        }
        if (!myPathCoordinates.contains(myGrid[startX][startY].getCoordinate())) {
            myPathCoordinates.add(myGrid[startX][startY].getCoordinate());
        }
        
        else{
            return false;
        }
        
        return (isValidPath(startX + 1, startY, endX, endY) || isValidPath(startX, startY + 1,
                                                                           endX, endY) ||
                isValidPath(startX - 1, startY, endX, endY) || isValidPath(startX, startY - 1,
                                                                           endX, endY));
    }

    public static void main (String[] args) {
        Grid g = new Grid(4, 4);
    }

}
