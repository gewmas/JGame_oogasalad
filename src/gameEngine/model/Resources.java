package gameEngine.model;

import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;
import java.util.Map;
import jgame.impl.JGEngineInterface;


/**
 * @author Jiaran
 *         This class register all the resources specified in JSON to our engine.
 *         All the resources is represented by a string name following the convention
 *         of JGame.
 */
public class Resources {

    JGEngineInterface myEngine;
    private Map<String, String> myImages = new HashMap<String, String>();

    public Resources (JGEngineInterface eng) {
        myEngine = eng;
    }

    public void register (Parser parser) {
        register(parser, "image");
        register(parser, "audio");
        register(parser, "animation");

    }

    /**
     * The purpose of this method is to register the resource based on their category.
     * Because different category has different fields. All the loops are supposed to
     * be the same so by doing this way we avoid duplicated code. To make this fit in
     * the open-closed principle, we can use strategy pattern. But it would be an over-kill
     * in this case.
     * 
     * @param parser: the parser passed from model.
     * @param category: which part to initialize
     * 
     */
    private void register (Parser parser, String category) {
        JSONArray items = parser.getJSONObject("resources").getJSONArray(category);
        for (int i = 0; i < items.length(); i++) {
            JSONObject oneItem = items.getJSONObject(i);
            String id = oneItem.getString("id");

            if (category.equals("image")) {
                String url = oneItem.getString("url");
                System.out.println(url);
                myEngine.defineImage(id, "-", 256, url, "-");
                myImages.put(id, url);
            }
            else if (category.equals("audio")) {
                String url = oneItem.getString("url");
                myEngine.defineAudioClip(id, url);
            }
            else {
                String[] frames = makeFrames(oneItem.getJSONArray("sheet"));
                myEngine.defineAnimation(id, frames, 0.2);

            }

        }
    }

    /**
     * @param jsonArray
     * @return a String array represents the animation.
     *         eg. "animation1", "animation2"....
     */
    private String[] makeFrames (JSONArray jsonArray) {
        int size = jsonArray.length();
        String[] result = new String[size];
        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = jsonArray.getJSONObject(i).getString("id");
        }
        return result;

    }

}
