package gameEngine.view.gameFrame;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import gameEngine.view.Frame;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.inputAndDisplay.InputAndDisplayFrame;
import gameEngine.view.gameFrame.inputAndDisplay.InputSender;
import gameEngine.view.gameFrame.menu.Menu;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;
import gameEngine.view.gameFrame.tools.store.StorePanel;
import gameEngine.controller.Controller;
import gameEngine.model.purchase.PurchaseInfo;


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
    private InputAndDisplayFrame cheatCodeFrame;

    /**
     * @param controller facilitates communication between view and model
     * @param view
     */
    public GameFrame (Controller controller, final View view, GameFrameMediator mediator) {
        super();
        this.mediator = mediator;

        this.view = view;
        this.cheatCodeFrame = new InputAndDisplayFrame("Cheat Sheet", new InputSender() {
            @Override
            public void submit (String cheat) {
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
        addGameTools();
        createMenu();
        pack();
        setVisible(true);
    }

    public void createGame () {
        CanvasPanel canvasPanel = new CanvasPanel(view, mediator);
        this.add(canvasPanel, BorderLayout.WEST);
        mediator.addGame(canvasPanel);
    }

    public void createMenu () {
        setJMenuBar(new Menu(view));
    }

    /**
     * Create the store of Towers
     */
    private void addGameTools () {
        JPanel tools = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        tools.setLayout(borderLayout);
        InfoDisplayPanel infoPanel = new InfoDisplayPanel(StyleConstants.resourceBundle
                .getString("ItemInfo"));
        mediator.addInfoPanel(infoPanel);
        tools.add(infoPanel, BorderLayout.CENTER);
        mediator.addInfoPanel(infoPanel);
        StorePanel storePanel = new StorePanel(mediator, view);
        mediator.addStore(storePanel);
        tools.add(storePanel, BorderLayout.PAGE_START);
        this.add(tools, BorderLayout.EAST);
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
