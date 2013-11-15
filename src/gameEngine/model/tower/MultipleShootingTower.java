package gameEngine.model.tower;

public class MultipleShootingTower extends DefaultTower {

    public MultipleShootingTower (double damage,
                                  double attackSpeed,
                                  int attackMode,
                                  double range,
                                  double cost,
                                  double recyclePrice,
                                  String id,
                                  boolean unique_id,
                                  double x,
                                  double y,
                                  int collisionid,
                                  String image) {
        super(damage, attackSpeed, attackMode, range, cost, recyclePrice, id, unique_id, x, y, collisionid,
              image);
    }

}
