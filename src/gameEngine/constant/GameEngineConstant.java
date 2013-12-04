package gameEngine.constant;



/**
 * @author Jiaran, Yuhua
 *         Stores all the constant value of game engine.
 *         These constants should include all the CIDs of JGObject.
 */
public class GameEngineConstant {
    public static final int PIXELSPERTILE=20;
    public static final int ENEMY_CID = 1;
    public static final int TOWER_CID = 2;
    public static final int BULLET_CID = 4;
    public static final int NORMALMAGIC_CID=0;
    
    public static final int FROZEMAGIC_ID=1;
    public static final int BOOSTMAGIC_ID=2;
    public static final int HASTEMAGIC_ID=4;
    public static final int ARMOURMAGIC_ID=8;
    public static final int HEALMAGIC_ID=16;
    public static final int LIGHTMAGIC_ID=32;
    public static final int OVERLAPMAGIC_ID=-1;
    
    //JSON TYPE
    public static final String TOWER_TYPE = "Tower";
    
    //Share Constant between Tower and Barrier
    public static final String PURCHASE_INFO_TYPE = "Type";
    public static final String PURCHASE_INFO_NAME = "Name";
    public static final String PURCHASE_INFO_DESCRIPTION = "Description";
    public static final String PURCHASE_INFO_COST = "Cost";
    public static final String PURCHASE_INFO_IMAGE = "Image";
    //Tower Constant
    public static final String TOWER_DAMAGE = "Damage";
    public static final String TOWER_ATTACK_SPEED = "Attack Speed";
    public static final String TOWER_RANGE = "Range";
    public static final String TOWER_SELL_PRICE = "Sell Price";
    public static final String TOWER_ATTACK_AMOUNT = "Attack Amount";
    public static final String TOWER_BOOST_FACTOR = "Boost Factor";
    public static final String TOWER_MAGIC_FACTOR = "Magic Factor";
    public static final String TOWER_MAGIC = "Magic";    
    public static final String TOWER_ATTACK_MODE = "Attack Mode";
    //Tower Upgrade Constant
    public static final String TOWER_UPGRADE_PRICE = "Upgrade Price";
    public static final String TOWER_UPGRADE_DAMAGE = "Upgrade Damage";
    public static final String TOWER_UPGRADE_ATTACK_SPEED = "Upgrade Attack Speed";
    public static final String TOWER_UPGRADE_BOOST_FACTOR = "Upgrade Boost Factor";
    public static final String TOWER_UPGRADE_MAGIC_FACTOR = "Upgrade Magic Factor";
    public static final String TOWER_UPGRADE_ATTACK_AMOUNT = "Upgrade Attack Amount";

    public static int query (Class T) {
        // System.out.println(T.getName());
        if (T.getName().equals("gameEngine.model.enemy.Enemy")) {
            return ENEMY_CID;
        }
        else if (T.getName().equals("gameEngine.model.tower.Tower")) {
            return TOWER_CID;
        }
        else return 0;
    }
}
