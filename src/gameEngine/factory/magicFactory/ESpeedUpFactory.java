package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.ESpeedUpMagic;
import gameEngine.model.magic.IMagicable;

public class ESpeedUpFactory implements IMagicFactory {
    private double speedUp=1;
    private int expire=120;
    @Override
    public void createMagicInstance (IMagicable target, IMagicable sender) {
        new ESpeedUpMagic(expire,target,speedUp,GameEngineConstant.SPEEDUPMAGIC_ID,GameEngineConstant.NORMALMAGIC_CID);
        
    }
    

}
