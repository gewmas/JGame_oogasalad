package gameEngine.model.magic;

public class EHealMagic extends Magic {

    public EHealMagic (int expire,
                       IMagicable target,
                       IMagicable sender,
                       String name,
                       int collisionid,
                       String gfxname,
                       int magicId) {
        super(expire, target, sender, name, collisionid, gfxname, magicId);
        // TODO Auto-generated constructor stub
    }

    @Override
    boolean removeCondition () {
        // TODO Auto-generated method stub
        return false;
    }

}
