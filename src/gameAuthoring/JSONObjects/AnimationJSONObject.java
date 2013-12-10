package gameAuthoring.JSONObjects;

import java.util.List;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * JSON Object that represents data stored in an animation
 *
 */
public class AnimationJSONObject extends JSONObject {

    /**
     * Constructor for Animation JSONObject
     * @param id ID of animation
     * @param imagePaths IDs of images used in animation
     */
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
