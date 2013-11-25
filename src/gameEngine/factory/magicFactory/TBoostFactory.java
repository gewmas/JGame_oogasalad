package gameEngine.factory.magicFactory;

import gameEngine.Constant.Constant;
import gameEngine.model.magic.IMagicable;
import gameEngine.model.magic.TBoostMagic;

public class TBoostFactory implements IMagicFactory{
    public static final int ID=Constant.BOOSTMAGIC_ID;
    public static final int CID=Constant.NORMALMAGIC_CID;
    private double factor;
    
    public TBoostFactory(){
        this(0.5);
    }
    
    public TBoostFactory(double factor){
        this.factor = factor;
    }
    
    @Override
    public void createMagicInstance (IMagicable target,IMagicable sender) {
        new TBoostMagic(target,sender,factor,ID,CID);
    }
}
