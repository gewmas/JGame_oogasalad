package gameEngine.model.effect;

public class FadeEffect extends Effect {
    private boolean isDown=false; 
    public FadeEffect (double x, double y, String gfxname, String audio) {
        super(x, y, gfxname,audio);
        alpha=0.1f;
    }

    public void move () {

        if (!isDown) {
            alpha+=0.05;
            if (alpha >= 1) {
                alpha = 1;
                isDown = true;
            }
        }
        else {
            alpha-=0.05;
            if(alpha<=0)
                remove();

        }
            
        

    }
}
