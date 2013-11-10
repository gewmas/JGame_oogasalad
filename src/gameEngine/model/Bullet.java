package gameEngine.model;

import gameEngine.model.enemy.Enemy;
import java.io.File;
import jgame.JGObject;


public class Bullet extends JGObject implements Readable, Writeable {

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

    @Override
    public File write () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void read () {
        // TODO Auto-generated method stub

    }
}
