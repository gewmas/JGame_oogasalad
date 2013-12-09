package gameEngine.model.magic;

/**
 * 
 * @author wenxin
 *
 */
public class EFrozeMagic extends Magic {
    public static final String NAME="FrozeMagic";
    private static final double FROZESPEEDCHANGE=-0.5;
    private double changeAlphaPerFrame;
    
    public EFrozeMagic ( int expire,IMagicable target,int ID, int CID) {
        super(expire,target,null,NAME,CID,NAME,ID);
        this.alpha=1f;
        changeAlphaPerFrame=0.95*alpha/expire;
        magicOn();
    }

    public void magicOnAction () {
        myChangeRecord=myTarget.changePercentSpeed(FROZESPEEDCHANGE);
    }

    public void magicOffAction () {
        myTarget.changeSpeed(-myChangeRecord);
    }
    
    public void move(){
        super.move();
        alpha-=changeAlphaPerFrame;
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }





}
