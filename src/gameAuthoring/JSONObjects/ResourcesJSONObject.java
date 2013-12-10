package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.List;


/**
 * Class that stores mappings of resource names to url for images, audio, and animation
 */
public class ResourcesJSONObject extends JSONObject {
    private JSONArray myImages;
    private JSONArray myAudio;
    private JSONArray myAnimations;

    /**
     * Constructor for ResourcesJSONObject
     */
    public ResourcesJSONObject () {
        myAnimations = new JSONArray();
        myAudio = new JSONArray();
        myImages = new JSONArray();
        this.put("image", myImages);
        this.put("audio", myAudio);
        this.put("animation", myAnimations);
        
    }

    /**
     * Adds image to resources
     * 
     * @param id Image ID
     * @param url Image URL
     */
    public void addImage (String id, String url) {
        myImages.put(new ResourceJSONObject(id, url));
    }
    
    /**
     * Adds image ResourceJSONObject to resources
     * 
     * @param image ResourceJSONObject for image
     */
    public void addImage(ResourceJSONObject image){
         myImages.put(image);
    }

    /**
     * Adds audio to resources
     * 
     * @param id Audio ID
     * @param url Audio URL
     */
    public void addAudio (String id, String url) {
        myAudio.put(new ResourceJSONObject(id, url));
    }

    /**
     * Adds audio ResourceJSONObject to resources
     * 
     * @param audio ResourceJSONObject for audio
     */
    public void addAudio(ResourceJSONObject audio){
        myAudio.put(audio);
   }
    
 
    /**
     * Adds AnimationJSONOBject to resource
     * 
     * @param animation AnimationJSONObject
     */
    public void addAnimation(AnimationJSONObject animation){
        myAnimations.put(animation);
    }

}
