package gameEngine.factory.magicFactory;

import gameEngine.model.magic.IMagicable;

public interface IMagicFactory {
    public void createMagicInstance(IMagicable target,IMagicable sender);
}
