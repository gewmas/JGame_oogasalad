package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import java.util.Collection;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;


public class MapJSONObject extends JSONObject {
    JSONArray pathList = new JSONArray();

    public MapJSONObject (String pathImage, Collection<Point2D> pathPoints) {

        for (Point2D p : pathPoints) {
            pathList.put(new PointJSONObject(p));
        }

        this.put("pathImage", pathImage);

        this.put("Path", pathList);

    }

}
