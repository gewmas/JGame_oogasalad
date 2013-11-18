package gameEngine.factory.buffFactory;

import java.util.HashMap;
import gameEngine.model.buff.IBuffable;

/*
 * wenxin shi
 */
public abstract class BuffFactory {
    
    private static BuffFactory myBuffFactory;
    private HashMap<Integer,BuffFactory> myFactoryMap=new HashMap<Integer,BuffFactory>();

    public void createBuffs(IBuffable target, int buffIds){
        createBuffs(target,buffIds,0);
    }
    
    public void createBuffs (IBuffable target, int buffIds, int currentBuffIds) {
        createBuffs(target,buffIds,currentBuffIds,0);
    }

    public void createBuffs(IBuffable target,int buffIds,int currentBuffIds, int immuneIds){
        int addNewBuffIds=(~currentBuffIds)&buffIds;
        int addNewBuffIdsAfterImmune=addNewBuffIds&(~immuneIds);
        
        for(int i=0;addNewBuffIdsAfterImmune<1;i++){
            if(((~1)&addNewBuffIdsAfterImmune)==1){
                myBuffFactory=myFactoryMap.get(Math.pow(2,i));
                myBuffFactory.createBuffInstance(target);
            }         
        }
        
        int newBuffIds=(currentBuffIds|buffIds)&(~immuneIds);
        target.changeCurrentBuffs(newBuffIds);
    }
    
    public abstract void createBuffInstance(IBuffable target);
}
