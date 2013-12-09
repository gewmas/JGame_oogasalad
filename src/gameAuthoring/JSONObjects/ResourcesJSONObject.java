package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.List;


/**
 * Class that stores mappings of resource names to url for images, audio, and animation
 * 
 * 
 */
public class ResourcesJSONObject extends JSONObject {
    private JSONArray myImages;
    private JSONArray myAudio;
    private JSONArray myAnimations;

    public ResourcesJSONObject () {
        myAnimations = new JSONArray();
        myAudio = new JSONArray();
        myImages = new JSONArray();
        this.put("image", myImages);
        this.put("audio", myAudio);
        this.put("animation", myAnimations);
        
    }

    public void addImage (String id, String url) {
        myImages.put(createBasicResource(id, url));
    }
    
    public void addImage(ResourceJSONObject image){
         myImages.put(image);
    }

    public void addAudio (String id, String url) {
        myAudio.put(createBasicResource(id, url));
    }

    public void addAudio(ResourceJSONObject audio){
        myAudio.put(audio);
   }
    
    private JSONObject createBasicResource (String id, String url) {
        JSONObject image = new JSONObject();
        image.put("id", id);
        image.put("url", url);
        return image;
    }
    
    public void addAnimation(AnimationJSONObject animation){
        myAnimations.put(animation);
    }

    public void addAnimation (String id, List<String> imagePaths) {
        JSONObject animation = new JSONObject();
        animation.put("id", id);

        JSONArray sheet = new JSONArray();

        for (String s : imagePaths) {
            JSONObject x = new JSONObject();
            x.put("id", s);
            sheet.put(x);
        }
        animation.put("sheet", sheet);
        myAnimations.put(animation);
    }
}
