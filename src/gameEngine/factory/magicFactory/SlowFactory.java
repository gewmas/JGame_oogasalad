package gameEngine.factory.magicFactory;

import gameEngine.model.magic.IMagicable;
import gameEngine.model.magic.SlowMagic;

public class SlowFactory implements IMagicFactory {
    public static final int ID = 4;
    public static final int CID=0;
    private int myExpire=5;
    private double mySlowStrength=0.5;
    
    @Override
    public void createMagicInstance (IMagicable target) {
        new SlowMagic(myExpire,target,mySlowStrength,ID,CID);
    }

}
