package gameEngine.cheats;

import gameEngine.model.Model;
import gameEngine.model.enemy.Enemy;

public class CheatParser {
    
    private Model model;
    
    public CheatParser(Model model) {
        this.model = model;
    }
    
    public Cheat parse(String cheat) {
        String[] cheatArgs = cheat.split(" ");
        String code = cheatArgs[0];
        
        if(code.equals("add_gold") || code.equals(customGoldCheat())) {
            return new AddGoldCheat(cheat, model);
        } else if(code.equals("add_lives")  || code.equals(customLivesCheat())) {
            return new AddLivesCheat(cheat, model);
        } else if(code.equals("clear_all")) {
            return new ClearAllCheat(cheat, model);
        } else if(code.equals("win_game")) {
            return new WinGameCheat(cheat, model);
        } else if (code.equals("lose_game")) {
            return new LoseGameCheat(cheat, model);
        }
        return null;
    }
    
    public String customGoldCheat() {
        String custom_name = model.getGameInfo().getMyGoldName().toLowerCase();
        return "add_"+custom_name;
    }
    
    public String customLivesCheat() {
        String custom_name = model.getGameInfo().getMyLivesName().toLowerCase();
        return "add_"+custom_name;
    }

}
