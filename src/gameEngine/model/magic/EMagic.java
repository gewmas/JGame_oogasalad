package gameEngine.model.magic;

public abstract class EMagic extends Magic {

    public EMagic (int expire,
                   IEMagicable target,
                   String name,
                   int collisionid,
                   String gfxname,
                   int magicId) {
        super(expire, target, name, collisionid, gfxname,magicId);
        
    }

    @Override
    public void magicOn () {
        IEMagicable target= (IEMagicable) myTarget;
        target.changeCurrentMagics(target.getCurrentMagics()|myCurrMagicIds);
        magicOnAction(target);
        
    }

    @Override
    public void magicOff () {
        IEMagicable target= (IEMagicable) myTarget;
        target.changeCurrentMagics(target.getCurrentMagics()&(~myCurrMagicIds));
        magicOffAction(target);
    }

    
    abstract void magicOnAction(IEMagicable target);
    abstract void magicOffAction(IEMagicable target);
    
}
