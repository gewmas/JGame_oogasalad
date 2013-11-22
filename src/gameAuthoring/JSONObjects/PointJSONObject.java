package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * Class that represents a point JSONObject
 *
 */
public class PointJSONObject extends JSONObject {

    /**
     * Constructor for PointJSONObject class
     * 
     * @param point Point2D to be put into object
     */
    public PointJSONObject (Point2D point) {
        this.put("x", point.getX());
        this.put("y", point.getY());
    }

}
