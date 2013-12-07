package gameEngine.model.effect;

import jgame.JGObject;

public class OneTimeEffect extends Effect {
    String myEnd= null;
    public OneTimeEffect (double x, double y, JGObject o, String end) {
        super(x, y, o);
        myEnd=end;
    }

    public void move () {

        String str = myObject.getImageName();
        System.out.println(str);
        if (str.equals(myEnd)) {
            myObject.remove();
            remove();
        }

    }
}
