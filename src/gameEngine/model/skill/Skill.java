package gameEngine.model.skill;

import jgame.impl.JGEngineInterface;

//Jiaran
abstract public class Skill {

    protected long myCoolDown = 0;
    protected long myCurrentCount=0;
    protected int myX;
    protected int myY;
    protected JGEngineInterface myEng;
    protected String myName;
    // because we can alter game speed, so everything should related
    // frame. That's why I don't use a built-in Timer.
    public Skill(long cd, JGEngineInterface eng){
        myCoolDown= cd;
        myEng=eng;
    }
    public void update (int x, int y){
        myX=x;
        myY=y;
        myCurrentCount++;
    }

    public void cast () {
        if(isCastable()){
            myCurrentCount=0;
            cast(myX,myY);
            
        }
        else {
            return;
        }
    }
    
    abstract protected void cast(int x , int y);
    
    
    
    
    protected boolean isCastable(){
        return myCurrentCount>=myCoolDown; 
    }
}
