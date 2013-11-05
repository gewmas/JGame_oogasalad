package gameEngine.model;

import jgame.JGObject;

public class Enemy extends JGObject {

    public Enemy (String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
    }
    
    @Override
    public void move() {
        x -= 0.1;
        y += 0.1;
    }

    @Override
    public void hit(JGObject obj) {
//        System.out.println("Enemy Hit");

    }

}
