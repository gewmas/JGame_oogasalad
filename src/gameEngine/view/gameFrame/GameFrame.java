package gameEngine.view.gameFrame;

import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.Collection;
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


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj Alex Zhu
 * 
 */
public class GameFrame extends Frame implements GameInitializable{

    private GameFrameMediator mediator;
    private View view;
    private InputAndDisplayFrame cheatCodeFrame;
    private StorePanel storePanel;

    private Utilities utilities;
    private ItemPurchaser itemPurchaser;
    private Collection<GameInitializable> gameInitializerItems;
    private Collection<GameUpdatable> gameUpdatables  ;
    /**
     * @param controller facilitates communication between view and model
     * @param view
     */
    public GameFrame (Controller controller, final View view, GameFrameMediator mediator) {
        super();
        this.mediator = mediator;

        this.view = view;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mediator.addGameFrame(this);
        this.cheatCodeFrame = addCheatCodeFrame(view);
        InfoDisplayPanel infoPanel = addInfoDisplay();
        utilities  = new Utilities(infoPanel,this,view);
        itemPurchaser = new ItemPurchaser(view,utilities);
        storePanel = addStorePanel(utilities,itemPurchaser);
        gameInitializerItems = new ArrayList();
        gameUpdatables = new ArrayList();
        gameUpdatables.add(storePanel);
        addGameTools(infoPanel, storePanel);
        
        setJMenuBar(new Menu(view));
    }

    private InputAndDisplayFrame addCheatCodeFrame (final View view) {
        return new InputAndDisplayFrame("Cheat Sheet", new InputSender() {
            @Override
            public void submit (String cheat) {
                view.activateCheat(cheat);
            }
        });
    }

    

    public void showGame () {
        createGame();
        pack();
        setVisible(true);
    }

    public void createGame () {
        CanvasPanel canvasPanel = new CanvasPanel(view, mediator,itemPurchaser, utilities);
        this.add(canvasPanel, BorderLayout.WEST);
        mediator.addGame(canvasPanel);
        utilities.createRangeDisplay();
    }

    /**
     * Create the store of Towers
     * @param storePanel2 
     * @param infoPanel2 
     */
    private void addGameTools (InfoDisplayPanel infoPanel, StorePanel storePanel) {
        JPanel tools = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        tools.setLayout(borderLayout);
        tools.add(infoPanel, BorderLayout.CENTER);
        tools.add(storePanel, BorderLayout.PAGE_START);
        gameInitializerItems.add(storePanel);
        gameInitializerItems.add(infoPanel);
        this.add(tools, BorderLayout.EAST);
        
        //this.add(new UpgradeButton(mediator),BorderLayout.);
    }

    private StorePanel addStorePanel (Utilities utilities, ItemPurchaser itemPurchaser) {
        StorePanel storePanel = new StorePanel( view,utilities,itemPurchaser);
        mediator.addStore(storePanel);
        return storePanel;
    }

    private InfoDisplayPanel addInfoDisplay () {
        InfoDisplayPanel infoPanel = new InfoDisplayPanel(StyleConstants.resourceBundle
                .getString("ItemInfo"));
        mediator.addInfoPanel(infoPanel);
        return infoPanel;
    }

    @Override
    public void initialize () {
        this.pack();
        
    }




}
