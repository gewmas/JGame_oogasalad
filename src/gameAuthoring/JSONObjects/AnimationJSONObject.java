package gameAuthoring.JSONObjects;

import java.util.List;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

public class AnimationJSONObject extends JSONObject {

    public AnimationJSONObject (String id, List<String> imagePaths) {
        this.put("id", id);

        JSONArray sheet = new JSONArray();

        for (String s : imagePaths) {
            JSONObject x = new JSONObject();
            x.put("id", s);
            sheet.put(x);
        }
        this.put("sheet", sheet);
    }

}
