package gameEngine.view.gameFrame;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
    private StorePanel storePanel;
    private InfoDisplayPanel infoPanel;
    private Utilities utilities;
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mediator.addGameFrame(this);
        this.setFocusable(true);
        infoPanel = addInfoDisplay();
        utilities  = new Utilities(infoPanel,this);
        
        storePanel = addStorePanel(utilities);
        
      
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
        tools.add(infoPanel, BorderLayout.CENTER);
        tools.add(storePanel, BorderLayout.PAGE_START);
        this.add(tools, BorderLayout.EAST);
    }

    private StorePanel addStorePanel (Utilities utilities) {
        StorePanel storePanel = new StorePanel(mediator, view,utilities);
        mediator.addStore(storePanel);
        return storePanel;
    }

    private InfoDisplayPanel addInfoDisplay () {
        InfoDisplayPanel infoPanel = new InfoDisplayPanel(StyleConstants.resourceBundle
                .getString("ItemInfo"));
        mediator.addInfoPanel(infoPanel);
        return infoPanel;
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
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

}
