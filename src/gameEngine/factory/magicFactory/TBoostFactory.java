package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.IMagicable;
import gameEngine.model.magic.TBoostMagic;
/**
 * 
 * @author wenxin
 *
 */
public class TBoostFactory implements IMagicFactory{
    public static final int ID=GameEngineConstant.BOOSTMAGIC_ID;
    public static final int CID=GameEngineConstant.NORMALMAGIC_CID;
    private double factor;
    
    public TBoostFactory(){
        this(0.5);
    }
    
    public TBoostFactory(double factor){
        this.factor = factor;
    }
    
    @Override
    public void createMagicInstance (IMagicable target,IMagicable sender) {
        new TBoostMagic(target,sender,factor,GameEngineConstant.BOOSTMAGIC_ID,GameEngineConstant.NORMALMAGIC_CID);
    }
}
