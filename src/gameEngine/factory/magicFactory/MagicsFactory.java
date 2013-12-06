package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.IMagicable;
import java.util.Collection;
import java.util.HashMap;


/*
 * wenxin shi
 */
public class MagicsFactory {

    private static MagicsFactory myMagicFactory;
    private HashMap<Integer, IMagicFactory> myFactoryMap = new HashMap<Integer, IMagicFactory>();
    private HashMap<String, Integer> myTranslateMap=new HashMap<String,Integer>();
    
    private MagicsFactory () {
        myFactoryMap.put(GameEngineConstant.FROZEMAGIC_ID, new EFrozeFactory());
        myFactoryMap.put(GameEngineConstant.BOOSTMAGIC_ID, new TBoostFactory());
        myFactoryMap.put(GameEngineConstant.HASTEMAGIC_ID, new EHasteFactory());
        
        
        
        myTranslateMap.put("FrozeMagic", GameEngineConstant.FROZEMAGIC_ID);
        myTranslateMap.put("BoostMagic", GameEngineConstant.BOOSTMAGIC_ID);
        myTranslateMap.put("HasteMagic", GameEngineConstant.HASTEMAGIC_ID);
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
    
    public void createMagics(IMagicable target, IMagicable sender,Collection<String> newMagicNames,Collection<String> currMagicNames){
        int newMagicIds=parserMagicNamesToId(newMagicNames);
        int currentMagicIds=parserMagicNamesToId(currMagicNames);
        createMagics (target, sender,newMagicIds, currentMagicIds);     
    }
    
    public void createMagics(IMagicable target, IMagicable sender,String newMagicNames){
        int newMagicIds=myTranslateMap.get(newMagicNames);
        createMagics (target, sender,newMagicIds,0);     
    }
    
    public int parserMagicNamesToId(Collection<String> names){
        int newMagicIds=0;
        for(String str:names){
            newMagicIds+= myTranslateMap.get(str);
        }
        return newMagicIds;
    }
}
