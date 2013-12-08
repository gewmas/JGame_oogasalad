package gameEngine.model;

import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import jgame.impl.JGEngineInterface;


/**
 * @author Jiaran
 *         This class register all the resources specified in JSON to our engine.
 *         All the resources is represented by a string name following the convention
 *         of JGame.
 */
public class Resources {

    JGEngineInterface myEngine;

    // private Map<String, String> myImages = new HashMap<String, String>();

    public Resources (JGEngineInterface eng) {
        myEngine = eng;
    }

    public void register (Parser parser) {
        register(parser, new ImageRegister());
        register(parser, new AudioRegister());
        register(parser, new AnimationRegister());

    }

    /**
     * The purpose of this method is to register the resource based on their category.
     * Because different category has different fields. All the loops are supposed to
     * be the same so by doing this way we avoid duplicated code. To make this fit in
     * the open-closed principle, we use strategy pattern. Because every time we want
     * to register new category or want to change the way how we register it, we
     * can make new classes. This Register class remains closed. Actually, all the
     * strategy class should be put into different folders. I didn't do that because
     * Strategy Pattern for this is already
     * an overkill for this because or the register strategy class is so simple. But
     * this can be viewed as left for further extensibility. For example, one may
     * come up with new data format to register pictures.
     * 
     * @param parser: the parser passed from model.
     * @param category: which part to initialize
     * 
     */
    private void register (Parser parser, RegisterStrategy rs) {
        JSONArray items = parser.getJSONObject("resources").getJSONArray(rs.category);
        for (int i = 0; i < items.length(); i++) {
            JSONObject oneItem = items.getJSONObject(i);
            rs.register(oneItem);

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

    private abstract class RegisterStrategy {
        protected String category = "";

        abstract public void register (JSONObject oneItem);
    }

    private class ImageRegister extends RegisterStrategy {

        public ImageRegister () {
            category = "image";
        }

        @Override
        public void register (JSONObject oneItem) {
            String id = oneItem.getString("id");
            String url = oneItem.getString("url");
            myEngine.defineImage(id, "-", 256, url, "-");

        }

    }

    private class AudioRegister extends RegisterStrategy {

        public AudioRegister () {
            category = "audio";
        }

        @Override
        public void register (JSONObject oneItem) {
            String id = oneItem.getString("id");
            String url = oneItem.getString("url");
            myEngine.defineAudioClip(id, url);

        }

    }

    private class AnimationRegister extends RegisterStrategy {

        public AnimationRegister () {
            category = "animation";
        }

        @Override
        public void register (JSONObject oneItem) {
            String id = oneItem.getString("id");
            String[] frames = makeFrames(oneItem.getJSONArray("sheet"));
            myEngine.defineAnimation(id, frames, 0.2);

        }

    }

}
