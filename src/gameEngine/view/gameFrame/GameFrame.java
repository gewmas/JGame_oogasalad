package gameEngine.view.gameFrame;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.UIManager;
import gameEngine.view.Frame;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.menu.Menu;
import gameEngine.view.gameFrame.store.TowerStorePanel;
import gameEngine.model.tower.TowerInfo;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj Alex Zhu
 * 
 */
public class GameFrame extends Frame {

    private GameFrameMediator mediator;
    private View view;
    private TowerStorePanel storePanel;

    /**
     * @param controller facilitates communication between view and model
     * @param engineView
     */
    public GameFrame (View engineView) {
        super();
        this.mediator = new GameFrameMediator();
        this.view = engineView;

        setUIStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mediator.addGameFrame(this);
    }

    /**
     * Setting the Look and Feel of the UI
     */
    private void setUIStyle () {
        Font f = new Font(StyleConstants.BUTTON_FONT, StyleConstants.BUTTON_FONT_STYLE,
                          StyleConstants.BUTTON_FONT_SIZE);
        UIManager.put(StyleConstants.BUTTON_FONT_KEY, f);

    }

    public void showGame () {
        createAndPalceGame();
        createAndPlaceStore();
        createMenu();
        pack();
        setVisible(true);
    }

    public void createAndPalceGame () {
        CanvasPanel canvasPanel = new CanvasPanel(view, mediator);
        mediator.addGamePanel(canvasPanel);
        this.add(canvasPanel, BorderLayout.WEST);
        
    }

    /**
     * Create the store of Towers
     */
    private void createAndPlaceStore () {
        storePanel = new TowerStorePanel(mediator, view);
        mediator.addStorePanel(storePanel);
        this.add(storePanel, BorderLayout.EAST);
    }
    
    
    
    

    // public void createStats(){
    // Panel statsPanel = new StatsPanel();
    // add(statsPanel, BorderLayout.SOUTH);
    // }

    public void createMenu () {
        setJMenuBar(new Menu(view));
    }


    /**
     * Changes the default cursor to the image of the tower to be placed
     */
    public void placeTower (TowerInfo towerInfo) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        System.out.println(towerInfo.getImage());
        Image image = toolkit.getImage("resources/img/" + towerInfo.getImage() + ".png");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "tower");
        setCursor(c);
    }
    
    /**
     * Reverts the cursor to default cursor after user makes a purchase
     */
    public void purchaseTower () {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

//    public boolean newGame (File file) {
//        try {
//            view.newGame(file);
//            view.loadNewGame();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return true;
//    }

  

}
