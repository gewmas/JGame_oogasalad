package gameEngine.cheats;

import java.util.ArrayList;
import gameEngine.model.Model;
import gameEngine.model.enemy.Enemy;

public class ClearAllCheat extends Cheat {

    public ClearAllCheat (String command, Model model) {
        super(command, model);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean execute() {
        ArrayList<Enemy> spawnedEnemies = model.getSpawnedEnemies();
        for (int j = 0; j < spawnedEnemies.size(); j++) {
            Enemy enemy = spawnedEnemies.get(j);
            enemy.setLife(0);
            spawnedEnemies.remove(j);
        }
        return true;
    }
    

    
}
