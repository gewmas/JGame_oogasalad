package gameEngine.factory.magicFactory;

import gameEngine.model.magic.IMagicable;
/**
 * 
 * @author wenxin
 *
 */
public interface IMagicFactory {
    public void createMagicInstance(IMagicable target,IMagicable sender);
}
