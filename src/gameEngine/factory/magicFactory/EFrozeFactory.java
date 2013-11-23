package gameEngine.factory.magicFactory;

import gameEngine.Constant.Constant;
import gameEngine.model.magic.EFrozeMagic;
import gameEngine.model.magic.IEMagicable;
import gameEngine.model.magic.IMagicable;

public class EFrozeFactory implements IMagicFactory {
    public static final int ID=Constant.FROZEMAGIC_ID;
    public static final int CID=Constant.NORMALMAGIC_CID;
    private int myExpire=60;
    
    @Override
    public void createMagicInstance (IMagicable target, IMagicable sender) {
        new EFrozeMagic(myExpire,(IEMagicable)target,ID,CID);  
    }

}
