package gameEngine.view;

import javax.swing.JFrame;

public class View extends Frame{
    Panel canvasPanel;
    Panel statsPanel;
    Panel storePanel;
    
    public View(){
        super();
        canvasPanel = new CanvasPanel();
        this.add(canvasPanel);
        pack();
        setVisible(true);
    }
    
}
