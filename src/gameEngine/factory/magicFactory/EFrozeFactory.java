package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.EFrozeMagic;
import gameEngine.model.magic.IMagicable;
/**
 * 
 * @author wenxin
 *
 */
public class EFrozeFactory implements IMagicFactory {

    private int myExpire=60;
    
    @Override
    public void createMagicInstance (IMagicable target, IMagicable sender) {
        new EFrozeMagic(myExpire,target,GameEngineConstant.FROZEMAGIC_ID,GameEngineConstant.NORMALMAGIC_CID);  
    }

}
