package gameEngine.view.gameFrame;

import gameEngine.view.View;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class SellButton extends JButton {
    private static final String NAME="Sell Tower";
    private static final int BUTTON_WIDTH=180;
    private static final int BUTTON_HEIGHT=20;
    
    
    private View view;
    private int towerX, towerY;
    private Utilities utilities;
    
    public SellButton(Utilities utility,View viewer){
        super(NAME);
        this.view=viewer;
        this.utilities=utility;
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                view.sellTower(towerX,towerY);
                utilities.clearDisplay();
            }
        });
        this.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
        this.setVisible(false);
        towerX=-1;
        towerY=-1;
    }
    
    public void setTowerPosition (int mouseX, int mouseY) {
        towerX=mouseX;
        towerY=mouseY;
    }
}
