package gameEngine.model.magic;

public class ESpeedUpMagic extends Magic{
    public static String NAME="haste";
    private double mySpeedUp;
    public ESpeedUpMagic (int expire,
                          IMagicable target,
                          double speedUp,
                          int ID,
                          int CID) {
        super(expire, target, null, NAME, CID, NAME, ID);
        mySpeedUp=speedUp;
        magicOn ();
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }
    
    public void magicOffAction(){
        ((IEMagicable)myTarget).changeSpeed(-mySpeedUp);
    }
    
    public void magicOnAction(){ 
        ((IEMagicable)myTarget).changeSpeed(mySpeedUp);
    }




}
