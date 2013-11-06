package temporary;

/**
 * @author Jiaran
 * For testing enemy generatation
 *
 */
public class TemporaryEnemyFactory {
    
    public TemporaryEnemy create(){
        return new TemporaryEnemy(null, true, 100, 400 , 1, "mana");
    }
}
