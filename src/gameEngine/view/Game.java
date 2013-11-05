package gameEngine.view;

import jgame.JGColor;
import jgame.platform.JGEngine;

public class Game extends JGEngine  {
    

   
    public Game () {
        initEngineComponent(600,600);
       
    }

    @Override
    public void initCanvas () {
        setCanvasSettings(30, 30, 10,
                          10, null, JGColor.black, null);
    }

    @Override
    public void initGame () {
        setFrameRate(30, 2);
    

        
    }

    @Override
    public void doFrame () {

    }

    @Override
    public void paintFrame () {
        super.paintFrame();

      

    }

  
}

