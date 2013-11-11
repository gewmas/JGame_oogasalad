package gameAuthoring;

import gameEngine.parser.JSONLibrary.JSONObject;




public class EnemyJSON extends JSONObject {

    public EnemyJSON (String ID, int gold, String imagePath, int lives, int speed) {
        super();
        this.put("id", ID);
        this.put("gold", gold);
        this.put("image", imagePath);
        this.put("life", lives);
        this.put("speed", speed);
    }

}
