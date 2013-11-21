package gameEngine.model.magic;


public class TBoostMagic extends TMagic{
    public static final String NAME="BoostMagic";
    private double myFactor;
    public TBoostMagic (ITMagicable target, IMagicable sender,double factor) {
        super(-1, target,sender ,NAME, NAME);
        myFactor=factor;
        magicOn();
    }

    @Override
    void magicOnAction (ITMagicable target) {
        target.upgrade(myFactor);
    }

    @Override
    void magicOffAction (ITMagicable target) {
       target.downgrade(myFactor);        
    }
    
}
