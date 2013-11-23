package gameEngine.factory.magicFactory;

import gameEngine.model.magic.IMagicable;
import java.util.HashMap;


/*
 * wenxin shi
 */
public class MagicsFactory {

    private static MagicsFactory myMagicFactory;
    private HashMap<Integer, IMagicFactory> myFactoryMap = new HashMap<Integer, IMagicFactory>();
    
    
    private MagicsFactory () {
        myFactoryMap.put(EFrozeFactory.ID, new EFrozeFactory());
        myFactoryMap.put(TBoostFactory.ID, new TBoostFactory());
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
     * @param newMagicIds
     * @param currentMagicIds
     */
    public void createMagics (IMagicable target, IMagicable sender,int newMagicIds, int currentMagicIds) {
        newMagicIds = (~currentMagicIds) & newMagicIds;
        int temp=newMagicIds;
        int mask=1;
        while(newMagicIds > 0) {
            IMagicFactory factory = myFactoryMap.get(temp&mask);
            if (factory != null) {
                factory.createMagicInstance(target,sender);
            }
            newMagicIds = newMagicIds >> 1;
            mask=mask<<1;
        }
    }
}
