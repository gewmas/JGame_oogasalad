package gameEngine.model;

import gameEngine.Constant.Constant;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import jgame.JGObject;
import jgame.JGRectangle;
import jgame.impl.JGEngineInterface;



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
public class Detector<T extends JGObject> {
    private JGEngineInterface myEng;
    //Because of Erasure, we need to use reflection to help complete the function
    private Class<T> myType;
    public Detector (JGEngineInterface eng, Class<T> type) {
        myEng = eng;
        myType=type;

    }

    // cid and prefix should get from a Constant class.
    @SuppressWarnings("unchecked")
    public List<T> getTargetsInRange (int centerx, int centery, int range) {
        Vector<JGObject> v = getObjects(centerx, centery, range, Constant.query(myType), null);
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < v.size(); i++) {
            result.add((T) v.get(i));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public T getOneTargetInRange (int centerx, int centery, int range) {
        Vector<JGObject> v = getObjects(centerx, centery, range, Constant.query(myType), null);

        T result = (T) v.get(0);
        return result;
    }

  

    @SuppressWarnings("unchecked")
    private Vector<JGObject> getObjects (int centerx, int centery, int range, int cid, String prefix) {
        JGRectangle rect = new JGRectangle(centerx - range, centery - range, 2 * range, 2 * range);
        return myEng.getObjects(prefix, cid, false, rect);
    }

}
