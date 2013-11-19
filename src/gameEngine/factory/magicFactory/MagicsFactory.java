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

        myFactoryMap.put(PoisonFactory.ID, new PoisonFactory());
        myFactoryMap.put(SlowFactory.ID, new SlowFactory());
        myFactoryMap.put(FrozeFactory.ID, new FrozeFactory());
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
    public void createMagics (IMagicable target, int newMagicIds, int currentMagicIds) {
        int addNewMagicIds = (~currentMagicIds) & newMagicIds;
        createMagics(target, addNewMagicIds);
    }

    /**
     * This method is for generating the same magic can overlap
     * 
     * @param target
     * @param newMagicIds
     */

    public void createMagics (IMagicable target, int newMagicIds) {
        for (int i = 0; newMagicIds > 0; i++) {
            IMagicFactory factory = myFactoryMap.get((int)Math.pow(2, i));
            if (factory != null) {
                factory.createMagicInstance(target);
            }
            newMagicIds = newMagicIds >> 1;
        }
    }
}
