package gameEngine.factory.magicFactory;

import gameEngine.model.magic.IMagicable;
import gameEngine.model.magic.PoisonMagic;

public class PoisonFactory implements IMagicFactory {
    public static final int ID = 2;
    public static final int CID = 0;
    private double myPosionStrength=0.5;
    private int myExpire=5;

    @Override
    public void createMagicInstance (IMagicable target) {
        new PoisonMagic(myExpire,target,myPosionStrength,ID,CID);
    }

}
