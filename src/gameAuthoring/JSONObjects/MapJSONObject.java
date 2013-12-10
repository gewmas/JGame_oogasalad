package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import java.util.Collection;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;


/**
 * JSONObject that stores game map data. Consists of path image and in order list of points
 * of path
 * 
 */
public class MapJSONObject extends JSONObject {
    JSONArray myPathList = new JSONArray();
    JSONArray myBarrierList = new JSONArray();

    /**
     * Constructor of MapJSONObject. 
     * 
     * @param pathImage Path image name
     * @param pathPoints Collection of points in path in order of enemy traversal
     */
    public MapJSONObject (String pathImage, Collection<Point2D> pathPoints) {

        this.put("pathImage", pathImage);
        
        for (Point2D p : pathPoints) {
            myPathList.put(new PointJSONObject(p));
        }

        this.put("Path", myPathList);
        this.put("Barriers", myBarrierList);

    }
    
    /**
     * Method to add (permanent barrier) to map
     * 
     * @param x x coordinate of barrier
     * @param y y coordinate of barrier
     * @param imageName ID of barrier image
     */
    public void addBarrier(int x, int y, String imageName){
        JSONObject newBarrier = new JSONObject();
        newBarrier.put("x", x);
        newBarrier.put("y", y);
        newBarrier.put("image", imageName);
        myBarrierList.put(newBarrier);
    }

}
