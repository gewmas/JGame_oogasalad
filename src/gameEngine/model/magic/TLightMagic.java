package gameEngine.model.magic;
/**
 * 
 * @author wenxin
 *
 */
public class TLightMagic extends Magic {
    public static final String NAME="LightMagic";
    private static final double CHANGERANGE=-1;
    public TLightMagic (int expire,IMagicable target,int ID, int CID) {
        super(expire,target,null,NAME,CID,NAME,ID);
        magicOn();
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }
    
    public void magicOnAction () {
       myChangeRecord=((ITMagicable) myTarget).changePercentRange(CHANGERANGE);
    }

    public void magicOffAction () {
        ((ITMagicable) myTarget).changeRange(-myChangeRecord);
    }

}
