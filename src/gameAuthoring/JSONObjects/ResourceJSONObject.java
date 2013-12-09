package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONObject;

public class ResourceJSONObject extends JSONObject {

    public ResourceJSONObject (String id, String url) {
       this.put("id", id);
       this.put("url", url);
    }

}
