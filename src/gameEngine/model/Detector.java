package gameEngine.model;

import gameEngine.Constant.Constant;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.tower.Tower;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import jgame.JGObject;
import jgame.JGRectangle;
import jgame.impl.JGEngineInterface;
import jgame.platform.JGEngine;


/**
 * @author Jiaran
 *         In this game, we have lots of detection to decide
 *         if an enemy or a tower is in range. This class handles
 *         different kind of detection and serve as a sift( for eg,
 *         if some enemies is invisible, you cannot attack them).
 *         This class also hide the bad design of JGEngine from other
 *         part of code.
 *         One can create subclass of detectors to fit in different
 *         situation. This is a strategy pattern.
 *         NOTE: to make this class function nicely. Recommend we
 *         have a good convention in naming the objects. eg. All
 *         enemies begin with the name of "enemyXX".
 * 
 */
public class Detector {
    private JGEngineInterface myEng;

    public Detector (JGEngineInterface eng) {
        myEng = eng;

    }

    // cid and prefix should get from a Constant class.
    public List<Enemy> getEnemiesInRange (int centerx, int centery, int range) {
        Vector<JGObject> v = getObjects(centerx, centery, range, Constant.ENEMY_CID, null);
        List<Enemy> result = new ArrayList<Enemy>();
        for (int i = 0; i < v.size(); i++) {
            result.add((Enemy) v.get(i));
        }
        return result;
    }
    
    /**
     * @author Yuhua
     * 
     * Jiaran, I add this to get current tower
     * Not test yet, have to do it in range and find the right one
     * 
     * @param centerx
     * @param centery
     * @param range
     * @return
     */
    public Tower getTowerInRange (int centerx, int centery, int range) {
        Vector<JGObject> v = getObjects(centerx, centery, range, Constant.TOWER_CID, null);
        
        for (int i = 0; i < v.size(); i++) {
            Tower tower = (Tower)v.get(i);
            if(tower.getX() == centerx && tower.getY() == centery){
                return tower;
            }
        }
        
        return null;
    }
    
   

    @SuppressWarnings("unchecked")
    private Vector<JGObject> getObjects (int centerx, int centery, int range, int cid, String prefix) {
        JGRectangle rect = new JGRectangle(centerx - range, centery - range, 2 * range, 2 * range);
        return myEng.getObjects(prefix, cid, false, rect);
    }

}
