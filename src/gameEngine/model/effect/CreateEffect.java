package gameEngine.model.effect;

import jgame.JGObject;

public class CreateEffect {
    public void blood(double x,double y){
        JGObject blood= new JGObject(null, true, 0, 0, 0, "Blood");
        new OneTimeEffect(x,y,blood,"BloodEnd");
    }
    
    public void Dollar(double x, double y){
        JGObject dollar= new JGObject(null, true, 0, 0, 0, "Dollar");
        new FadeEffect(x,y,dollar);
        new FloatEffect(dollar);
    }
    
    public void Words(double x, double y, String words){
        new WordEffect(y, y, words, 60);
    }
    
    public void Explosion(double x, double y){
        JGObject explosion= new JGObject(null, true, 0, 0, 0, "Explode");
        new OneTimeEffect(x,y,explosion,"ExplodeEnd");
    }
    
}
