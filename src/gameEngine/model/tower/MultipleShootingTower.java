package gameEngine.model.tower;

public class MultipleShootingTower extends DefaultTower {

    public MultipleShootingTower (double damage,
                                  double attackSpeed,
                                  int attackMode,
                                  int attackAmount,
                                  double range,
                                  double cost,
                                  double recyclePrice,
                                  String description,
                                  
                                  String id,
                                  boolean unique_id,
                                  double x,
                                  double y,
                                  int collisionid,
                                  String image) {
        super(damage, attackSpeed, attackMode, range, cost, recyclePrice, description, id, unique_id, x, y,
              collisionid, image);
        
        this.attackAmount = attackAmount;
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        
        info.put("Attack Amount", String.valueOf(attackAmount));
    }
    
    @Override
    public void upgrade () {
        super.upgrade();
        this.attackAmount++;
    }

    @Override
    public void downgrade(){
        super.downgrade();
        this.attackAmount--;
    }

}
