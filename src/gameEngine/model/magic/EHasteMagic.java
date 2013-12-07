package gameEngine.model.magic;
/**
 * 
 * @author wenxin
 *
 */
public class EHasteMagic extends Magic{
    public static String NAME="HasteMagic";
    private double mySpeedUp;  
   private double alphIncrement;
    public EHasteMagic (int expire,
                          IMagicable target,
                          double speedUp,
                          int ID,
                          int CID) {
        super(expire, target, null, NAME, CID, NAME, ID);
        mySpeedUp=speedUp;
        alphIncrement=-alpha*0.8/expire;
        magicOn ();
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }
    
    public void magicOnAction(){ 
        myChangeRecord=((IEMagicable)myTarget).changePercentSpeed(mySpeedUp);
    }
    
    public void magicOffAction(){
        myChangeRecord=((IEMagicable)myTarget).changePercentSpeed(-myChangeRecord);
    }
    
    public void move(){
        super.move();
        this.alpha+=alphIncrement;      
    }
}
