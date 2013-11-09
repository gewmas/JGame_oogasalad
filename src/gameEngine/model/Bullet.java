package gameEngine.model;

import jgame.JGObject;


public class Bullet extends JGObject {

    Enemy targetEnemy;

    public Bullet (
                   Enemy targetEnemy,
                   String name,
                   boolean unique_id,
                   double x,
                   double y,
                   int collisionid,
                   String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);

        this.targetEnemy = targetEnemy;
    }

    @Override
    public void move () {

    }

    @Override
    public void hit (JGObject obj) {

    }

}
