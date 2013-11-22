package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import java.util.Collection;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;


/**
 * Class that represents JSONObject for game map. Consists of path image and in order list of points
 * of path
 * 
 */
public class MapJSONObject extends JSONObject {
    JSONArray pathList = new JSONArray();

    /**
     * Constructor of MapJSONObject. 
     * 
     * @param pathImage Path image name
     * @param pathPoints Collection of points in path in orfer of path traversal
     */
    public MapJSONObject (String pathImage, Collection<Point2D> pathPoints) {

        for (Point2D p : pathPoints) {
            pathList.put(new PointJSONObject(p));
        }

        this.put("pathImage", pathImage);

        this.put("Path", pathList);

    }

}
