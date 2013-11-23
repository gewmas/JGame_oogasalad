package gameEngine.view.gameFrame;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import gameEngine.view.Frame;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.inputAndDisplay.InputAndDisplayFrame;
import gameEngine.view.gameFrame.inputAndDisplay.InputSender;
import gameEngine.view.gameFrame.menu.Menu;
import gameEngine.view.gameFrame.store.StorePanel;
import gameEngine.view.gameFrame.store.ToolsFrame;
import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.purchase.PurchaseInfo;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj Alex Zhu
 * 
 */
public class GameFrame extends Frame {

    private Controller controller;
    private GameFrameMediator mediator;
    private View view;
    private StorePanel storePanel;
    private InputAndDisplayFrame cheatCodeFrame;

    /**
     * @param controller facilitates communication between view and model
     * @param view
     */
    public GameFrame (Controller controller, final View view, GameFrameMediator mediator) {
        super();
        this.mediator = mediator;
        this.controller = controller;
        this.view = view;
        this.cheatCodeFrame = new InputAndDisplayFrame("Cheat Sheet",new InputSender(){
            @Override
            public  void submit(String cheat) {
                view.activateCheat(cheat);
            }
        });
    
        setUIStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mediator.addGameFrame(this);
        this.setFocusable(true);

        this.addKeyListener(new KeyListener() {
            public void keyPressed (KeyEvent e) {

                if (e.isControlDown() && e.getKeyChar() != 't' && e.getKeyCode() == 84) {
                    cheatCodeFrame.showFrame();
                }
            }

            public void keyReleased (KeyEvent e) {
            }

            public void keyTyped (KeyEvent e) {
            }
        });
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
        // createStats();
        createMenu();
        pack();
        setVisible(true);
    }

    public void createGame () {
        CanvasPanel canvasPanel = new CanvasPanel(view, mediator);
        this.add(canvasPanel, BorderLayout.WEST);
        mediator.addGame(canvasPanel);
    }

    // public void createStats(){
    // Panel statsPanel = new StatsPanel();
    // add(statsPanel, BorderLayout.SOUTH);
    // }

    public void createMenu () {
        setJMenuBar(new Menu(view));
    }

    /**
     * Create the store of Towers
     */
    private void createStore () {
//        storePanel = new StorePanel(mediator, view);
//        mediator.addStore(storePanel);
//        this.add(storePanel, BorderLayout.EAST);
        Frame tools = new ToolsFrame(mediator,view);
        tools.showFrame();
    }

    /**
     * Changes the default cursor to the image of the tower to be placed
     */
    public void placeTower (PurchaseInfo towerInfo) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("resources/img/" + towerInfo.getImage() + ".png");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "tower");
        setCursor(c);
    }

    public boolean newGame (File file) {
        try {
            view.newGame(file);
            view.loadNewGame();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public void restoreDefaultCursor () {
        System.out.println("Restore");
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
    

}
