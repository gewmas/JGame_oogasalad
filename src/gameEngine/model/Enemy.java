package gameEngine.model;

import java.io.File;
import jgame.JGObject;


public class Enemy extends JGObject implements Readable, Writeable {

    public Enemy (String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        // XXX Auto-generated constructor stub
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
