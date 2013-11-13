package gameEngine.factory.towerfactory;

import gameEngine.model.tower.Tower;
import gameEngine.model.tower.*;

public abstract class TowerFactory implements TowerInfo{
    
    String id;
    String description;
    String image;

    double x;
    double y;

    double damage;
    double attackSpeed;
    
    int attackMode;
    
    double range;

    double cost;
    double recyclePrice;

    public abstract Tower create (int x, int y);

    public String getImage () {
        return image;
    }


    public int getCost () {
        return (int) cost;
    }

    public String getName () {
        return id;
    }

    public String getDescription () {
        return description;
    }
    
    public double getDamage (){
        return damage;
    }
    public double getAttackSpeed (){
        return attackSpeed;
    }
    public int getAttackMode() {
		return attackMode;
	}

	public double getRange (){
        return range;
    }
    
    public double getRecyclePrice (){
        return recyclePrice;
    }
    

}
