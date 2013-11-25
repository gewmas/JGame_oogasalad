package gameEngine.model.magic;



public class TBoostMagic extends Magic{
    public static final String NAME="BoostMagic";
    private double myFactor;
    public TBoostMagic (IMagicable target, IMagicable sender,double factor,int ID, int CID) {
        super(-1, target,sender ,NAME,CID,NAME,ID);
        myFactor=factor;
        magicOn();
    }
    
   public void magicOnAction () {
        ((ITMagicable)myTarget).downgrade(myFactor);
    }
    public void magicOffAction () {
        ((ITMagicable)myTarget).upgrade(myFactor);
    }
    @Override
    boolean removeCondition () {
        return !myTarget.isAlive()||!mySender.isAlive();
    }
    
    
    protected void moveNextStep (){                
        this.x=myTarget.getX();
        this.y=myTarget.getY();
    }

    
}
