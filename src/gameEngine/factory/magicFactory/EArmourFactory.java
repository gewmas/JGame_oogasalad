package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.EArmourMagic;
import gameEngine.model.magic.IMagicable;

public class EArmourFactory implements IMagicFactory {
    
    private int myExpire=60;
    
    @Override
    public void createMagicInstance (IMagicable target, IMagicable sender) {
        new EArmourMagic(myExpire,target,GameEngineConstant.ARMOURMAGIC_ID,GameEngineConstant.ENEMY_CID);  
    }
    

}
