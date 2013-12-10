package gameEngine.factory.magicFactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.EPoisonMagic;
import gameEngine.model.magic.IMagicable;
/**
 * 
 * @author wenxin
 *
 */
public class EPoisonFactory implements IMagicFactory{
    private double myHeal=0.005;
    private double myHealLevel=50;
    
    @Override
    public void createMagicInstance (IMagicable target, IMagicable sender) {
        new EPoisonMagic(-1,target,myHeal,myHealLevel,GameEngineConstant.POISONMAGIC_ID,GameEngineConstant.NORMALMAGIC_CID);  
    }

}