package gameEngine.model.effect;

import jgame.JGObject;

/**
 * This class is created to facilitate other people to use built-in Effects.
 * Blood, explosion, dollar, words are almost in every Tower defense game.
 * We save time of users of our engine by giving them built-in effect.
 * It is similar that when design CG, basically every CAD has water and 
 * fire effect.   
 * It is a Singleton Pattern because all it has no dependency to
 * all the other classes. Just show effects on the screen.
 * @author Jiaran
 *
 */
public class CreateEffect {
    public void blood(double x,double y){
        JGObject blood= new JGObject(null, true, 0, 0, 0, "Blood");
        new OneTimeEffect("BloodEffect",x,y,blood,"BloodEnd");
    }
    
    public void Dollar(double x, double y){
        JGObject dollar= new JGObject(null, true, 0, 0, 0, "Dollar");
        new FadeEffect("DollarEffect",x,y,dollar);
        new FloatEffect(dollar);
    }
    
    public void Words(double x, double y, String words){
        new WordEffect(x, y, words, 60);
    }
    
    public void Explosion(double x, double y){
        JGObject explosion= new JGObject(null, true, 0, 0, 0, "Explode");
        new OneTimeEffect("ExplosionEffect",x,y,explosion,"ExplodeEnd");
    }
    
}
