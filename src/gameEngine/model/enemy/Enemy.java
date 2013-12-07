package gameEngine.model.enemy;
import gameEngine.constant.GameEngineConstant;
import gameEngine.factory.magicFactory.MagicsFactory;
import gameEngine.model.Model;
import gameEngine.model.bullet.Bullet;
import gameEngine.model.effect.CreateEffect;
import gameEngine.model.magic.IEMagicable;
import gameEngine.model.skill.Skill;
import gameEngine.model.skill.SkillFactory;
import gameEngine.model.temporaryBarrier.TemporaryBarrier;
import gameEngine.model.tile.Tile;
import gameEngine.model.tower.Tower;
import java.util.LinkedList;
import jgame.JGObject;
import jgame.JGRectangle;


/**
 * 
 * @author Fabio, Yuhua, wenxin
 * 
 */
public class Enemy extends JGObject implements IEMagicable {

    String id;
    String image;
    Skill mySkill=null;
    Model model;
    double gold;
    double life;
    double speed;
    double xMovement;
    double yMovement;
    int pathIndex;
    double pathStep;
    LinkedList<Tile> path;
    
    double pathX;
    double pathY;

    // wenxin add this attribution for magic.
    int currentMagics = 0;

    final double originalLife;
    final double orignalSpeed;

    public Enemy (
                  double gold,
                  double life,
                  double speed,
                  String id,
                  boolean unique_id,
                  int collisionid,
                  String image,
                  Model model) {
        super(id, unique_id, model.getPathList().get(0).getX(), model.getPathList().get(0).getY(),
              collisionid, image);

        this.model = model;
        this.id = id;
        this.image = image;
        this.setGraphic(image);
        this.xMovement = 1;
        this.yMovement = 0;

        this.gold = gold;
        this.life = life;
        this.originalLife = life;
        this.speed = speed;
        this.orignalSpeed = speed;
        this.path = model.getPathList();
        this.pathIndex = 0;

        this.x = path.get(0).getX();
        this.y = path.get(0).getY();
        
        pathX=x;
        pathY=y;


        this.currentMagics = 0;
        // Yuhua change it
        // this.x = x;
        // this.y = y;

        calculatePathStep();
        calculateNewDirection();
    }

    @Override
    public void move () {
        //update skills
        if (mySkill != null)
            mySkill.update((int) this.getCenterX(), (int) this.getCenterY());
        // Should walk along the Path
        if (reachedPoint()) {
            // System.out.println("Reached point!");
            calculateNewDirection();
        }
        pathX += xMovement * speed;
        pathY += yMovement * speed;
        JGRectangle box=this.getImageBBox();

        this.x=pathX-(box.width-GameEngineConstant.PIXELSPERTILE)/2;
        this.y=pathY-(box.height-GameEngineConstant.PIXELSPERTILE);
    }

    public boolean reachedPoint () {
        if (yMovement == 0) {
            double x1 = path.get(pathIndex - 1).getX();
            if (Math.abs(x1 - pathX) > pathStep) { return true; }
        }
        else {
            double y1 = path.get(pathIndex - 1).getY();
            if (Math.abs(y1 - pathY) > pathStep) { return true; }
        }

        return false;
    }
    
    public void lifeLessThanZero() {
        if(life <= 0) {
            model.getGameInfo().addGold((int)gold);
            CreateEffect effect=new CreateEffect();
            effect.blood(this.getCenterX(), this.getCenterY());
            effect.Dollar(this.getCenterX(), this.getCenterY());
            
            remove();
        }
    }

    @Override
    public void hit (JGObject obj) {
        // hit the target enemy, destroy that enemy
        // System.out.println("Bullet Hit");

        if (obj.colid == GameEngineConstant.BULLET_CID) {
            if(obj instanceof TemporaryBarrier) {
                /**
                 * @author Harris
                 * For killing enemies with temporary barriers
                 */
                life--;
                lifeLessThanZero();
   
            }
            else {
                Bullet bullet = (Bullet) obj;
                if (this == bullet.getTargetEnemy()) {
                    /**
                     * @author Yuhua
                     *         bullet can only hurt target enemy
                     *         no obj.remove(), let bullet kill itself
                     */

                    life -= ((Bullet) obj).getDamage();
                    lifeLessThanZero();
                    /**
                     * @author wenxin
                     *         below command deal with creation of magics;
                     */
                    MagicsFactory.getInstance().createMagics(this, null, bullet.getCurrentMagic(),
                                                             currentMagics);
                }
            }
        }
    }

    public void reachedGoal () {
        model.getGameInfo().loseLife();
        remove();
    }

    public void calculateNewDirection () {

        pathIndex = pathIndex + 1;
        if (pathIndex == path.size()) {
            reachedGoal();
            return;
        }
        double x1 = path.get(pathIndex).getX();
        double y1 = path.get(pathIndex).getY();
        if (Math.abs(pathX - x1) < Math.abs(pathY - y1)) {
            if ((pathY - y1) > 0) {
                this.yMovement = -1;
            }
            else {
                this.yMovement = 1;
            }
            this.xMovement = 0;

        }
        else {
            if ((pathX - x1) > 0) {
                this.xMovement = -1;
            }
            else {
                this.xMovement = 1;
            }
            this.yMovement = 0;
        }
    }

    public void calculatePathStep () {
        double x1 = path.get(pathIndex).getX();
        double y1 = path.get(pathIndex).getY();

        double x2 = path.get((pathIndex + 1)).getX();
        double y2 = path.get((pathIndex + 1)).getY();

        if (x1 == x2) {
            // Y direction distance
            pathStep = Math.abs(y1 - y2);
        }
        else {
            // x direction distance
            pathStep = Math.abs(x1 - x2);
        }
    }

    public double getLife () {
        return life;
    }

    /**
     * @author Yuhua
     *         For comparator to compare shortest/furthest enemy
     */
    public double getDistanceFromTower (Tower tower) {
        return Math.sqrt(Math.pow(pathX - tower.getX(), 2) + Math.pow(pathY - tower.getY(), 2));
    }

    /**
     * @author wenxin
     *         For the IMagicable interface implement
     */
    public double getCenterX(){
        
        return x+this.getImageBBox().width/2;
    }
    public double getCenterY(){
        return y+this.getImageBBox().height/2;
    }
    
    @Override
    public double getX () {
        return pathX;
    }

    @Override
    public double getY () {
        return pathY;
    }

    @Override
    public int getCurrentMagics () {
        return currentMagics;
    }

    @Override
    public void setCurrentMagic (int magic) {
        currentMagics = magic;
    }

    @Override
    public double changeLife (double offset) {
        life+=offset;
        return offset;
    }
    
    @Override
    public double changePercentLife (double percent) {
        double change=life*percent;
        life+=change;
        return change;  
    }

    @Override
    public double changePercentSpeed (double percent) {
        double change=speed*percent;
        speed+=change;
        return change;    
    }
    

    @Override
    public double changeSpeed(double offset){
       speed+=offset;
       return offset;
    }



    public void setLife(int value) {
        life = value;
        lifeLessThanZero();
    }


    public void setSkill(String skill){
        SkillFactory sf= new SkillFactory(this.eng);
        mySkill=sf.create(skill);
    }


}
