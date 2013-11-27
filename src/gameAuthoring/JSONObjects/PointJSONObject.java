package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * JSONObject that represents the information contained in a point 
 *
 */
public class PointJSONObject extends JSONObject {

    /**
     * Constructor for PointJSONObject class
     * 
     * @param point Point2D to be put into object
     */
    public PointJSONObject (Point2D point) {
        this.put("x", point.getY());
        this.put("y", point.getX());
    }

}
