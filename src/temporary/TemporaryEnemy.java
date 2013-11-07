package temporary;

import java.util.HashMap;
import jgame.JGObject;
import temporary.buff.Buff;

public class TemporaryEnemy extends JGObject {
    private HashMap<String,Buff> myBuffMap;
    public TemporaryEnemy (String name,
                           boolean unique_id,
                           double x,
                           double y,
                           int collisionid,
                           String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        this.xspeed=10;
        myBuffMap=new HashMap<String,Buff>();
        
    }
    @Override
    public void move(){
        super.move();
    }
    
    public void addBuff(Buff buff){
        Buff temp=myBuffMap.get(buff.getName());
        if(temp!=null){
            myBuffMap.remove(buff.getName());
            temp.remove();
        }
        myBuffMap.put(buff.getName(), buff);         
    }
    
    public void removeBuff(Buff buff){
        Buff temp=myBuffMap.get(buff.getName());
        if(temp!=null){
            myBuffMap.remove(buff.getName());
            temp.remove();
        }
    }
    
    public void changeSpeed(int speed){
        xspeed+=speed;
    }
   

}
