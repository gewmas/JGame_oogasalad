package gameEngine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import jgame.JGObject;
import jgame.JGRectangle;
import jgame.impl.JGEngineInterface;
import jgame.platform.JGEngine;
import temporary.TemporaryEnemy;

/**
 * @author Jiaran
 * In this game, we have lots of detection to decide
 * if an enemy or a tower is in range. This class handles
 * different kind of detection and serve as a sift( for eg,
 * if some enemies is invisible, you cannot attack them).
 * This class also hide the bad design of JGEngine from other
 * part of code.
 *  One can create subclass of detectors to fit in different
 *  situation. This is a strategy pattern. 
 *  NOTE: to make this class function nicely. Recommend we 
 *  have a good convention in naming the objects. eg. All
 *  enemies begin with the name of "enemyXX".
 *
 */
public class Detector {
    private JGEngineInterface myEng;
    public Detector(JGEngineInterface eng){
        myEng=eng;
       
    }
    //cid and prefix should get from a Constant class.
    public List<TemporaryEnemy> getEnemiesInRange(int centerx, int centery, int range){
        Vector<JGObject> v= getObjects(centerx,centery,range,1,null);
        List<TemporaryEnemy> result=new ArrayList<TemporaryEnemy>();
        for ( int i=0;i<v.size();i++){
            result.add((TemporaryEnemy)v.get(i));
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    private Vector<JGObject> getObjects(int centerx,int centery,int range,int cid,String prefix){
        JGRectangle rect=new JGRectangle(centerx-range,centery-range,2*range,2*range);
        return myEng.getObjects(prefix, cid, false, rect);
    }
    

}
