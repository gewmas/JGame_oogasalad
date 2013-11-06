package gameEngine.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    /**
     * @author Yuhua
     * towers and enemies should associate with each level
     * which should store in some class call Level or Rule
     * 
     * Instance variables here used for test purpose
     */
    private GameInfo gameInfo;
    private Level level;
    
    public Model(){
        gameInfo = new GameInfo(1000, 1000, 1000);
        level = new Level(gameInfo);
    }


}
