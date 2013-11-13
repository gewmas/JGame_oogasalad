package gameEngine.model.tower;

/**
 * @author Jiaran and Yuhua all tower and tower factory
 * implements TowerInfo so that front end people can 
 * get tower info properly without exposing tower and 
 * towerfactory.
 *
 */
public interface TowerInfo {

    public String getTowerName ();

    public double getX ();

    public double getY ();

    public double getDamage ();

    public double getAttackSpeed ();

    public double getRange ();

    public int getCost ();

    public double getRecyclePrice ();
    public String getDescription();
    public String getImage ();
}
