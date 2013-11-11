package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONObject;




public class EnemyJSONObject extends JSONObject {

    public EnemyJSONObject (String ID, int gold, String imagePath, int lives, int speed) {
        super();
        this.put("id", ID);
        this.put("gold", gold);
        this.put("image", imagePath);
        this.put("life", lives);
        this.put("speed", speed);
    }

}
