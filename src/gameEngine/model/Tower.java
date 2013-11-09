package gameEngine.model;

import java.io.File;
import jgame.JGObject;


public class Tower extends JGObject implements Readable, Writeable {

    double damage;
    double attackSpeed;
    double range;

    double cost;
    double recyclePrice;

    public Tower (
                  double damage,
                  double attackSpeed,
                  double range,
                  double cost,
                  double recyclePrice,

                  String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {

        super(name, unique_id, x, y, collisionid, gfxname);

        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.cost = cost;
        this.recyclePrice = recyclePrice;

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
