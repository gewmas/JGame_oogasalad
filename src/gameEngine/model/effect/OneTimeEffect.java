package gameEngine.model.effect;

public class OneTimeEffect extends Effect {
    
    public OneTimeEffect (double x, double y, String gfxname,String audio) {
        super(x, y, gfxname,audio);
    }

    public void move () {

        String str = this.getImageName();
        if (str.equals(myGraphics + "END")) {
            remove();
        }

    }
}
