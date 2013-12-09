package gameEngine.model.skill;

import jgame.impl.JGEngineInterface;


/**
 * Skill class allows Enemy or Tower to have different behavior. This class
 * only handle the cast mechanism. It's extension can decide when is appropriate
 * to cast the skill. How to cast skill is decided by overriding the abstract
 * methoud: cast.
 * 
 * @author Jiaran
 * 
 */
abstract public class Skill {

    protected long myCoolDown = 0;
    protected long myCurrentCount = 0;
    protected int myX;
    protected int myY;
    protected JGEngineInterface myEng;
    protected String myName;

    public Skill (long cd, JGEngineInterface eng, String name) {
        myCoolDown = cd;
        myEng = eng;
        myName=name;
    }

    /*
     * because we can alter game speed, so everything should related
     * frame. That's why I don't use a built-in Timer. The ememy who carries
     * the skills should call update each time.
     */
    public void update (int x, int y) {
        myX = x;
        myY = y;
        myCurrentCount++;
    }

    public void cast () {
        if (isCastable()) {
            myCurrentCount = 0;
            cast(myX, myY);

        }
        else {
            return;
        }
    }

    abstract protected void cast (int x, int y);

    protected boolean isCastable () {
        return myCurrentCount >= myCoolDown;
    }
}
