package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * Class that stores mappings of resource names to url for images, audio, and animation
 * 
 * 
 */
public class ResourcesJSONObject extends JSONObject{
    private JSONArray myImages;
    private JSONArray myAudio;
    private JSONArray myAnimations;
    
    public ResourcesJSONObject () {
        super();
        this.put("image", myImages);
        this.put("audio", myAudio);
        this.put("animation", myAnimations);
    }
    
    public void addImage(String id, String url){
       myImages.put(createBasicResource(id, url));
    }
    
    public void addAudio(String id, String url){
        myAudio.put(createBasicResource(id, url));
    }
    
    private JSONObject createBasicResource(String id, String url){
        JSONObject image = new JSONObject();
        image.put("id", id);
        image.put("url", url);
        return image;
    }
    
    //TODO: Finish this
    public void addAnimation(String id){
        JSONObject animation = new JSONObject();
        animation.put("id", id);
        
        JSONArray sheet = new JSONArray();
        
        
        animation.put("sheet", sheet);
        myAnimations.put(animation);
    }
}
