package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * JSONObject that represents an image or audio resource
 *
 */
public class ResourceJSONObject extends JSONObject {

    public ResourceJSONObject (String id, String url) {
       this.put("id", id);
       this.put("url", url);
    }

}
