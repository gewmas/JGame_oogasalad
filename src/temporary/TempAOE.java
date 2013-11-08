package temporary;

import gameEngine.model.Detector;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import temporary.buff.Buff;
import temporary.buff.SlowBuff;
import jgame.JGObject;

public class TempAOE extends JGObject {

    public TempAOE (String name,
                    boolean unique_id,
                    double x,
                    double y,
                    int collisionid,
                    String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        
    }
    
    private Set<TemporaryEnemy> mySet=new HashSet<TemporaryEnemy>();
    public void move(){
//        Detector d=new Detector(this.eng);
//        List<TemporaryEnemy> l= d.getEnemiesInRange((int)x, (int)y, 100);
//        for(int i=0;i<l.size();i++){
//            if(mySet.contains(l.get(i)))
//                    ;
//            else{
//                Buff buff=new SlowBuff("a",true,0,0,0,"heal",50,l.get(i));
//                mySet.add(l.get(i));
//           }
//        }
    }
}
