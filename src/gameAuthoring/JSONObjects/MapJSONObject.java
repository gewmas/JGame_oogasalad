package gameAuthoring.JSONObjects;

import java.awt.geom.Point2D;
import java.util.Collection;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;


public class MapJSONObject extends JSONObject {
    JSONArray pathList = new JSONArray();

    public MapJSONObject (String backgroundImage,
                          String pathImage,
                          Point2D start,
                          Point2D end,
                          Collection<Point2D> pathPoints) {
        
        for (Point2D p : pathPoints) {
            pathList.put(new PointJSONObject(p));
        }

        this.put("BGImage", backgroundImage);
        this.put("pathImage", pathImage);
        this.put("startPath", new PointJSONObject(start));
        this.put("endPath", new PointJSONObject(end));

        this.put("Path", pathList);

    }

}
