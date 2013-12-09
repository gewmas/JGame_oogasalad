package gameEngine.view.gameFrame;

import java.util.ArrayList;
import java.util.Collection;

public class CompositeGameUpdatable implements GameUpdatable
{
    private Collection<GameUpdatable> gameUpdatables;
    public CompositeGameUpdatable(){
        gameUpdatables = new ArrayList();
    }
    
    public void add(GameUpdatable gameUpdatable){
        gameUpdatables.add(gameUpdatable);
    }
    
    public void remove(GameUpdatable gameUpdatable){
        gameUpdatables.remove(gameUpdatable);
    }
    @Override
    public void update () {
        for (GameUpdatable updatable : gameUpdatables) {
            updatable.update();
        }
        
    }

    @Override
    public void endGame () {
        for (GameUpdatable updatable : gameUpdatables) {
            updatable.endGame();
        }
        
    }

}
