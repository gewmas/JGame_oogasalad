package gameEngine.model.purchase;

/**
 * @author Jiaran and Yuhua all tower and tower factory
 * implements TowerInfo so that front end people can 
 * get tower info properly without exposing tower and 
 * towerfactory.
 *
 */
public interface PurchaseInfo {

    public String getItemName ();

    public double getX ();

    public double getY ();

    public double getDamage ();

    public double getAttackSpeed ();
    public int getAttackMode();
    public double getRange ();

    public int getCost ();

    public double getRecyclePrice ();
    public String getDescription();
    public String getImage ();
}
