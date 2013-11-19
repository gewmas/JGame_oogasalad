package gameEngine.factory.magicFactory;

import gameEngine.model.magic.FrozeMagic;
import gameEngine.model.magic.IMagicable;

public class FrozeFactory implements IMagicFactory {
    public static final int ID=1;
    public static final int CID=0;
    private int myExpire=5;

    @Override
    public void createMagicInstance (IMagicable target) {
        new FrozeMagic(myExpire,target,ID,CID);
    }

}
