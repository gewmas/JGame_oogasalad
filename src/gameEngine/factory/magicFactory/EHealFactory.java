package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.EHealMagic;
import gameEngine.model.magic.IMagicable;
/**
 * 
 * @author wenxin
 *
 */
public class EHealFactory implements IMagicFactory{
    private int myExpire=60;
    private double myHeal=0.5;
    
    @Override
    public void createMagicInstance (IMagicable target, IMagicable sender) {
        new EHealMagic(myExpire,target,myHeal,GameEngineConstant.HEALMAGIC_ID,GameEngineConstant.NORMALMAGIC_CID);  
    }

}
