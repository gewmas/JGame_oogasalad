package temporary;

import java.util.HashMap;
import jgame.JGObject;
import temporary.buff.Buff;
import temporary.buff.ISlowBuffable;

public class TemporaryEnemy extends JGObject  implements ISlowBuffable {
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
    
    public void changeSpeed(int speed){
        xspeed+=speed;
    }
    @Override
    public double getX () {
        return this.x;
    }
    @Override
    public double getY () {
        return this.y;
    }
    @Override
    public void slowBuffOn (Buff buff,int offSet) {
        Buff temp=myBuffMap.get(buff.getName());
        if(temp!=null){
            myBuffMap.remove(buff.getName());
            temp.remove();
        }
        myBuffMap.put(buff.getName(), buff);
        this.xspeed+=offSet;
        
    }
    @Override
    public void slowBuffOff (Buff buff,int offSet) {
        Buff temp=myBuffMap.get(buff.getName());
        if(temp!=null){
            myBuffMap.remove(buff.getName());
            temp.remove();
            this.xspeed+=offSet;
        }
        
    }
   
}
