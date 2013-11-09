package gameAuthoring;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Grid implements Observer {

    JFrame myFrame = new JFrame();
    JButton[][] myGrid;
    boolean[][] myPath;
    Collection<Point2D> myPathCoordinates = new ArrayList<Point2D>();

    public Grid (int width, int height) {
        myFrame.setLayout(new GridLayout(width, height));
        myGrid = new JButton[width][height];
        myPath = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                GridButton gButton = new GridButton(x, y);
                gButton.addObserver(this);
                JButton button = gButton.create();
                myGrid[x][y] = button;
                myPath[x][y] = false;
                myFrame.add(myGrid[x][y]);
            }
        }
        // JButton done = new JButton("Done");
        // done.addMouseListener(addPathDoneListener());
        // myFrame.add(done);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);
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

    @Override
    public void update (Observable arg0, Object arg1) {
        Point2D pathCoordinate = (Point2D.Double) arg1;
        // System.out.println(pathCoordinate.getX() + "," + pathCoordinate.getY());
        myPath[(int) pathCoordinate.getX()][(int) pathCoordinate.getY()] = true;
        isValidPath(0, 0, 2, 2);
    }

    public boolean isValidPath (int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= myGrid.length || startY < 0 || startY >= myGrid[0].length) { return false; }
        if (!myPath[startX][startY]) { return false; }
        if (startX == endX && startY == endY) {
            System.out.println("path complete");
            for (Point2D point : myPathCoordinates) {
                System.out.println(point.toString());
            }
            return true;
        }
        Point2D point = new Point2D.Double(startX, startY);
        if (!myPathCoordinates.contains(point)) {
            myPathCoordinates.add(point);
        }
        return (isValidPath(startX + 1, startY, endX, endY) || isValidPath(startX, startY + 1,
                                                                           endX, endY));
    }

    public static void main (String[] args) {
        Grid g = new Grid(4, 4);
    }

}
