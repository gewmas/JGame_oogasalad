package gameEngine.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;
import gameEngine.model.TowerInfo;
import gameEngine.view.menu.Menu;
import gameEngine.view.store.TowerStorePanel;
import gameEngine.controller.Controller;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj
 * 
 */
public class GameFrame extends Frame implements Colleague {

    private Controller controller;
    private Mediator mediator;

    /**
     * @param controller facilitates communication between view and model
     * @param engineView
     */
    public GameFrame (Controller controller, View engineView) {
        super();
        mediator = new Mediator();
        this.controller = controller;

        setUIStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        mediator.addColleague("view", this);
        Panel statsPanel = new StatsPanel();
        add(statsPanel, BorderLayout.SOUTH);

        setJMenuBar(new Menu(engineView, controller));

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
        pack();
        setVisible(true);

    }

    public void createGame(){
        Panel canvasPanel = new CanvasPanel(this);
        this.add(canvasPanel, BorderLayout.WEST);
        mediator.addColleague(MediatorConstants.GAME_KEY, canvasPanel);
    }
    
    /**
     * Create the store of Towers
     */
    private void createStore () {
        Panel storePanel = new TowerStorePanel(mediator, controller);
        this.add(storePanel, BorderLayout.EAST);
    }

    @Override
    /**
     * Changes the default cursor to the image of the tower to be placed
     */
    public void placeTower (TowerInfo towerInfo) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(towerInfo.getImage());
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "tower");
        setCursor(c);

    }

    /**
     * Tells the controller to send tower purchase instructions to the model
     * and then reset the cursor
     */
    public void buyTower (int x, int y, String tower) {
        controller.purchaseTower(x, y, tower);
        purchaseTower();
    }

    /**
     * Requests tower information for the tower at the given location
     */
    public void getTowerInfo (int x, int y) {
        controller.getTowerInfo(x, y);
    }

    @Override
    /**
     * After tower is purchased, the cursor is set to the default cursor.
     */
    public void purchaseTower () {
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Gets the dimensions of the game on initialization
     */
    public Dimension getGameSize () {
        return controller.getGameSize();
    }

    public int getScore () {
        return controller.getScore();
    }

    /**
     * Gets the background image of the game upon initialization
     */
    public String getBGImage () {
        return controller.getBGImage();
    }

    public int getMoney () {
        return controller.getMoney();
    }

    public int getLives () {
        return controller.getLives();
    }

    @Override
    public void displayTowerInfo (TowerInfo towerInfo) {
        // No behavior

    }
}
