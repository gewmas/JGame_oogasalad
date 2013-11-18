package gameEngine.model.tower;

import java.util.Map;
import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;

/**
 * 
 * @author Yuhua
 *
 * Freeze Tower would slow down or other functionall enemies in range by slowFactor
 * The enemies get back normal speed when out of range
 * 
 */
public class MagicTower extends Tower {

	private Detector<Enemy> detector;
	private double slowFactor;
	
    public MagicTower (String name,
                        boolean unique_id,
                        double x,
                        double y,
                        int collisionid,
                        String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);

        this.detector = new Detector<Enemy>(this.eng, Enemy.class);
    }

    @Override
    public void sell () {
        // TODO Auto-generated method stub

    }

    @Override
    public void upgrade () {
    	
    }
    
    @Override
	public void downgrade(){
    	
    }

    @Override
   	public void upgrade(double factor) {
   		// TODO Auto-generated method stub
   		
   	}

   	@Override
   	public void downgrade(double factor) {
   		// TODO Auto-generated method stub
   		
   	}
    
    @Override
    public void setAttackMode (int attackMode) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<String, String> getInfo () {
        // TODO Auto-generated method stub
        return null;
    }

}
