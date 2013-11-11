package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import gameEngine.parser.JSONLibrary.JSONObject;

public class PointJSONObject extends JSONObject{

    public PointJSONObject (Point2D point) {
        this.put("x", point.getX());
        this.put("y", point.getY());
    }

}
