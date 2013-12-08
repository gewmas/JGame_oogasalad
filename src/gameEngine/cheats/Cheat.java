package gameEngine.cheats;

import gameEngine.model.Model;
import java.util.ArrayList;


/**
 * @author Fabio Berger
 *
 */
public class Cheat {
    
    String code;
    int numParams;
    ArrayList<Integer> params = new ArrayList<Integer>();
    Model model;
    
    public Cheat(String command, Model model) {
        String[] cheatArgs = command.split(" ");
        this.numParams = cheatArgs.length-1;
        for (int i = 0; i < cheatArgs.length; i++) {
            if(i==0) {
                this.code = cheatArgs[i];
            } else {
                this.params.add(Integer.parseInt(cheatArgs[i]));
            }
        }
        this.model = model;
    }
    
    // Execute the Cheat
    public boolean execute() {
        return true;
    }

}
