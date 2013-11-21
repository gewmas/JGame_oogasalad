package gameEngine.model.magic;

public abstract class TMagic extends Magic{
    private IMagicable mySender;
    public TMagic (int expire,
                   ITMagicable target,
                   IMagicable sender,
                   String name,
                   String gfxname) {
        super(expire, target, name, 0, gfxname,0);
        mySender=sender;
    }

    @Override
    public void magicOn () {
        ITMagicable target= (ITMagicable) myTarget;
        magicOnAction(target);
    }
    
    public void moveNextStep(){
        if(!mySender.isAlive())
            remove();
        else
            super.moveNextStep();
    }

    @Override
    public void magicOff () {
        ITMagicable target= (ITMagicable) myTarget;
        magicOffAction(target);
    }

    
    abstract void magicOnAction(ITMagicable target);
    abstract void magicOffAction(ITMagicable target);

}
