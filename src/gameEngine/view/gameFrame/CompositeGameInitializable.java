package gameEngine.view.gameFrame;

import java.util.ArrayList;
import java.util.Collection;

public class CompositeGameInitializable implements GameInitializable {
    private Collection<GameInitializable> gameInitializables;
    public CompositeGameInitializable(){
        gameInitializables = new ArrayList();
    }
    
    public void add(GameInitializable gameInitializable){
        gameInitializables.add(gameInitializable);
    }
    
    public void remove(GameInitializable gameInitializable){
        gameInitializables.remove(gameInitializable);
    }
    @Override
    public void initialize () {
       for (GameInitializable initializable: gameInitializables){
           initializable.initialize();
       }
        
    }

}
