package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.EHasteMagic;
import gameEngine.model.magic.IMagicable;
/**
 * 
 * @author wenxin
 *
 */
public class EHasteFactory implements IMagicFactory {
    private double speedUp=1;
    private int expire=120;
    @Override
    public void createMagicInstance (IMagicable target, IMagicable sender) {
        new EHasteMagic(expire,target,speedUp,GameEngineConstant.HASTEMAGIC_ID,GameEngineConstant.NORMALMAGIC_CID);
        
    }
    

}
