package gameEngine.view.gameFrame.gameObjects;

import jgame.JGColor;
import jgame.JGObject;


/**
 * 
 * @author alex
 *         Draws a circle around a Tower to indicate its range
 */
public class RangeDisplay extends JGObject {

    double range;

    public RangeDisplay (String name,
                         boolean unique_id,
                         double x,
                         double y,
                         int collisionid) {
        super(name, unique_id, x, y, collisionid, null);
        this.resume_in_view = false;
        this.suspend();
    }

    @Override
    public void move () {

    }

    @Override
    public void paint () {
        eng.drawOval(x, y, range, range, false, true, 4, JGColor.red);
    }
<<<<<<< HEAD

    public void setTower (double range, double towerX, double towerY) {
        this.range = range;
        JGPoint tileCoords = eng.getTileCoord(eng.getTileIndex(towerX, towerY));
        this.setPos(tileCoords.x + tilewidth / 2, tileCoords.y + tileheight / 2);
=======
//wenxin shi, change towerX, towerY as center of tower
//So when need to draw, use towerCenterX,towerCenterY
    public void setTower (double range,double towerCenterX, double towerCenterY) {
        this.range=range;
        this.setPos(towerCenterX, towerCenterY);
//        JGPoint tileCoords=eng.getTileCoord(eng.getTileIndex(towerX, towerY));
//        this.setPos(tileCoords.x+tilewidth/2, tileCoords.y+tileheight/2);
>>>>>>> engine
    }

}
