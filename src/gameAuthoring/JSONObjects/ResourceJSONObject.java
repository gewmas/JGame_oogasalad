package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * JSONObject that represents an image or audio resource
 *
 */
public class ResourceJSONObject extends JSONObject {

    /**
     * Constructor for ResourceJSONObject
     * 
     * @param id ID of image
     * @param url URL of image
     */
    public ResourceJSONObject (String id, String url) {
       this.put("id", id);
       this.put("url", url);
    }

}
