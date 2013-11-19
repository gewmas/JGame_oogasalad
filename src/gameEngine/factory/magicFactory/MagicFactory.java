package gameEngine.factory.magicFactory;

import java.util.HashMap;
import gameEngine.model.magic.IMagicable;

/*
 * wenxin shi
 */
public abstract class MagicFactory {
    
    private static MagicFactory myMagicFactory;
    private HashMap<Integer,MagicFactory> myFactoryMap=new HashMap<Integer,MagicFactory>();

    public void createMagics(IMagicable target, int magicIds){
        createMagics(target,magicIds,0);
    }
    
    public void createMagics (IMagicable target, int magicIds, int currentMagicIds) {
        createMagics(target,magicIds,currentMagicIds,0);
    }

    public void createMagics(IMagicable target,int magicIds,int currentMagicIds, int immuneIds){
        int addNewMagicIds=(~currentMagicIds)&magicIds;
        int addNewMagicIdsAfterImmune=addNewMagicIds&(~immuneIds);
        
        for(int i=0;addNewMagicIdsAfterImmune<1;i++){
            if(((~1)&addNewMagicIdsAfterImmune)==1){
                myMagicFactory=myFactoryMap.get(Math.pow(2,i));
                myMagicFactory.createMagicInstance(target);
            }         
        }
        
        int newMagicIds=(currentMagicIds|magicIds)&(~immuneIds);
        target.changeCurrentMagics(newMagicIds);
    }
    
    public abstract void createMagicInstance(IMagicable target);
}
