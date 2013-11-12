package gameEngine.view.gameFrame;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.UIManager;
import gameEngine.view.Frame;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.menu.Menu;
import gameEngine.view.gameFrame.store.TowerStorePanel;
import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.tower.TowerInfo;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj
 * 
 */
public class GameFrame extends Frame  {

    private Controller controller;
    private GameFrameMediator mediator;
    private View engineView;
    private Panel storePanel;

    /**
     * @param controller facilitates communication between view and model
     * @param engineView
     */
    public GameFrame (Controller controller, View engineView) {
        super();
        mediator = new GameFrameMediator();
        this.controller = controller;
        this.engineView = engineView;

        setUIStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mediator.addGameFrame( this);
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
        createGame();
        createStore();
//        createStats();
        createMenu();
        pack();
        setVisible(true);
    }
    
    public void createGame () {
        CanvasPanel canvasPanel = new CanvasPanel(engineView,mediator);
        this.add(canvasPanel, BorderLayout.WEST);
        mediator.addGame(canvasPanel);
    }
    
//    public void createStats(){
//        Panel statsPanel = new StatsPanel();
//        add(statsPanel, BorderLayout.SOUTH);
//    }
    
    public void createMenu(){
        setJMenuBar(new Menu(engineView, controller));
    }

    /**
     * Create the store of Towers
     */
    private void createStore () {
        storePanel = new TowerStorePanel(mediator, engineView);
        this.add(storePanel, BorderLayout.EAST);
    }


    /**
     * Changes the default cursor to the image of the tower to be placed
     */
    public void placeTower (TowerInfo towerInfo) {
       
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(towerInfo.getImage());
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "tower");
        setCursor(c);
    }
//
//    /**
//     * Tells the controller to send tower purchase instructions to the model
//     * and then reset the cursor
//     */
//    public void buyTower (int x, int y, String tower) {
//        controller.purchaseTower(x, y, tower);
//        purchaseTower();
//    }
//
//    /**
//     * Requests tower information for the tower at the given location
//     */
//    public Tower getTowerInfo (int x, int y) {
//        return controller.getTowerInfo(x, y);
//    }
//
//    @Override
//    /**
//     * After tower is purchased, the cursor is set to the default cursor.
//     */
//    public void purchaseTower () {
//        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//    }
//
//    /**
//     * Gets the dimensions of the game on initialization
//     */
//    public Dimension getGameSize () {
//        return controller.getGameSize();
//    }
//    
//    public List<Tile> getPath(){
//        return controller.getPath();
//    }
//
//    /**
//     * Gets the background image of the game upon initialization
//     */
//    public String getBGImage () {
//        return controller.getBGImage();
//    }
//
//    public int getMoney () {
//     
//         return controller.getMoney();
//    }
//
//    public int getLives () {
//        return controller.getLives();
//    }
//
//    @Override
//    public void displayTowerInfo (TowerFactory towerInfo) {
//        // No behavior
//    }
//
//    public List<TowerFactory> getTowers () {
//        return controller.getTowerFactory();
//    }

//    public boolean newGame (File file) {
//        try {
//            controller.newGame(file);
//            engineView.loadNewGame();
//        }
//        catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        
//
//        return true;
//    }



    
    public void purchaseTower () {
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      
  }
}
