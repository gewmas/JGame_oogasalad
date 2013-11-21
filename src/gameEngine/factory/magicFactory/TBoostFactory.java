package gameEngine.factory.magicFactory;

import gameEngine.model.magic.IMagicable;
import gameEngine.model.magic.ITMagicable;
import gameEngine.model.magic.TBoostMagic;

public class TBoostFactory implements IMagicFactory{
    public static final int ID=2;
    private double factor=0.5;
    @Override
    public void createMagicInstance (IMagicable target,IMagicable sender) {
        new TBoostMagic((ITMagicable)target,sender,factor);
    }
}
