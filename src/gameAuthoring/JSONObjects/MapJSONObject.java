package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import gameEngine.parser.JSONLibrary.JSONObject;

public class MapJSONObject extends JSONObject{

    public MapJSONObject (String backgroundImage, String pathImage, Point2D start, Point2D end) {
       this.put("BGImage", backgroundImage);
       this.put("pathImage", pathImage);
       this.put("startPath", new PointJSONObject(start));
       this.put("endPath", new PointJSONObject(end));
    }

}
