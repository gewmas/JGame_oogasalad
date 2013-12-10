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
                if (cheatArgs[i].matches("[0-9]+")) {
                    this.params.add(Integer.parseInt(cheatArgs[i]));   
                } else {
                    this.params.add(0);
                }
            }
        }
        this.model = model;
    }
    
    // Execute the Cheat
    public boolean execute() {
        return true;
    }

}
