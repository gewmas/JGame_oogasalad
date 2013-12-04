package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.IMagicable;
import java.util.HashMap;
import java.util.List;


/*
 * wenxin shi
 */
public class MagicsFactory {

    private static MagicsFactory myMagicFactory;
    private HashMap<Integer, IMagicFactory> myFactoryMap = new HashMap<Integer, IMagicFactory>();
    private HashMap<String, Integer> myTranlateMap=new HashMap<String,Integer>();
    
    private MagicsFactory () {
        myFactoryMap.put(GameEngineConstant.FROZEMAGIC_ID, new EFrozeFactory());
        myFactoryMap.put(GameEngineConstant.BOOSTMAGIC_ID, new TBoostFactory());
        myFactoryMap.put(GameEngineConstant.SPEEDUPMAGIC_ID, new ESpeedUpFactory());
        
        
        
        myTranlateMap.put("FrozeMagic", GameEngineConstant.FROZEMAGIC_ID);
        myTranlateMap.put("BoostMagic", GameEngineConstant.FROZEMAGIC_ID);
        myTranlateMap.put("SpeedMagic", GameEngineConstant.SPEEDUPMAGIC_ID);
    }

    public static MagicsFactory getInstance () {
        if (myMagicFactory == null)
            myMagicFactory = new MagicsFactory();
        return myMagicFactory;
    }

    /**
     * This method is for the same magic can't overlap
     * 
     * @param target
     * @param magicIdToCreate
     * @param currentMagicIds,if the magic can overlap then make the currentMagicIds be Constant.OVERLAPMAGIC_ID
     */
    public void createMagics (IMagicable target, IMagicable sender,int magicIdToCreate, int currMagicIds) {
        if(currMagicIds!=GameEngineConstant.OVERLAPMAGIC_ID){
            magicIdToCreate = (~currMagicIds) & magicIdToCreate;
        }
        
        int temp=magicIdToCreate;
        int mask=1;
        while(magicIdToCreate > 0) {
            IMagicFactory factory = myFactoryMap.get(temp&mask);
            if (factory != null) {
                factory.createMagicInstance(target,sender);
            }
            magicIdToCreate = magicIdToCreate >> 1;
            mask=mask<<1;
        }
    }
    
    public void creatMagics(IMagicable target, IMagicable sender,List<String> magicIdToCreate,List<String> currMagicIds){
        int newMagicIds=0;
        int currentMagicIds=0;
        
        for(String str:magicIdToCreate){
            newMagicIds+= myTranlateMap.get(str);
        }
        
        for(String str:currMagicIds){
            currentMagicIds+= myTranlateMap.get(str);
        }
        
        createMagics (target, sender,newMagicIds, currentMagicIds);
        
        
    }
}
