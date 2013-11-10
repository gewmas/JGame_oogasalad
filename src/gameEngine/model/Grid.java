package gameEngine.model;

import jgame.JGObject;


/**
 * 
 * @author Harris
 * 
 *         Each grid square will be a Grid object
 * 
 */
public class Grid extends JGObject {

    private boolean onPath;

    public Grid (String name,
                 boolean unique_id,
                 double x,
                 double y,
                 int collisionid,
                 String gfxname,
                 int tilebbox_x,
                 int tilebbox_y,
                 int tilebbox_width,
                 int tilebbox_height) {
        super(name, unique_id, x, y, collisionid, gfxname, tilebbox_x, tilebbox_y, tilebbox_width,
              tilebbox_height);
        onPath = false;

    }

    public void setOnPath () {
        onPath = true;
    }

    public boolean isOnPath () {
        return onPath;
    }
    // know the x,y position, and getters for that

}
