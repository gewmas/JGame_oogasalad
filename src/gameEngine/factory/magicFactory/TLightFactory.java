package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.IMagicable;
import gameEngine.model.magic.TLightMagic;
/**
 * 
 * @author wenxin
 *
 */
public class TLightFactory implements IMagicFactory {

    private int myExpire=60;
    
    @Override
    public void createMagicInstance (IMagicable target, IMagicable sender) {
        new TLightMagic(myExpire,target,GameEngineConstant.LIGHTMAGIC_ID,GameEngineConstant.NORMALMAGIC_CID);  
    }

}
