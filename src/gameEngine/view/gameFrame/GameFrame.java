package gameEngine.view.gameFrame;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.view.ViewConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.inputFrame.InputFrame;
import gameEngine.view.gameFrame.inputFrame.InputSender;
import gameEngine.view.gameFrame.menu.Menu;
import gameEngine.view.gameFrame.store.StorePanel;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;
import gameEngine.view.gameFrame.towerUpgrader.ItemOptionsDisplayer;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj Alex Zhu
 * 
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements GameInitializable {

    private ControllerToViewInterface controller;
    private StorePanel storePanel;
    private InfoDisplayPanel infoPanel;
    private CanvasPanel canvasPanel;
    private ItemOptionsDisplayer towerUpgrader;
    private ItemPurchaser itemPurchaser;
    private Collection<KeyActivationItem> gameKeyActivationItems;
    private CompositeGameInitializable gameInitializerItems;
    private CompositeGameUpdatable gameUpdatables;

    /**
     * @param controller facilitates communication between view and model
     * @param controller
     */
    public GameFrame (ControllerToViewInterface controller, View view) {
        super();

        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CheatFrame cheatCodeFrame = addCheatCodeFrame();

        this.gameKeyActivationItems = new ArrayList<KeyActivationItem>();
        gameKeyActivationItems.add(cheatCodeFrame);

        infoPanel = addInfoDisplay();
        towerUpgrader = new ItemOptionsDisplayer(infoPanel, controller);
        itemPurchaser = new ItemPurchaser(controller, this);
        storePanel = addStorePanel(towerUpgrader, itemPurchaser);
        gameInitializerItems = new CompositeGameInitializable();
        gameInitializerItems.add(towerUpgrader);
        gameUpdatables = new CompositeGameUpdatable();
        gameUpdatables.add(storePanel);
        addGameTools(infoPanel, storePanel);

        setJMenuBar(new Menu(view));
    }

    /**
     * Creates the cheat frame
     */
    private CheatFrame addCheatCodeFrame () {
        InputFrame inputFrame = new InputFrame( new InputSender() {
            @Override
            public void submit (String cheat) {
                controller.activateCheat(cheat);
            }
        },ViewConstants.resourceBundle.getString("CheatFrame"));
        return new CheatFrame(inputFrame);
    }

    /**
     * Initializes the entire gameFrame
     */
    public void showGame () {
        createGame();
        pack();
        setVisible(true);
    }

    /**
     * Creates the jgame instance
     */
    public void createGame () {
        gameInitializerItems.add(this);
        canvasPanel =
                new CanvasPanel(controller, itemPurchaser, towerUpgrader, gameInitializerItems,
                                gameUpdatables, gameKeyActivationItems);
        this.add(canvasPanel, BorderLayout.WEST);

        towerUpgrader.createRangeDisplay();
    }

    /**
     * Create the store of Towers
     */
    private void addGameTools (InfoDisplayPanel infoPanel, StorePanel storePanel) {
        JPanel tools = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        tools.setLayout(borderLayout);
        tools.add(infoPanel, BorderLayout.CENTER);
        tools.add(storePanel, BorderLayout.PAGE_START);
        gameInitializerItems.add(storePanel);
        this.add(tools, BorderLayout.EAST);
    }

    private StorePanel addStorePanel (ItemOptionsDisplayer towerUpgrader,
                                      ItemPurchaser itemPurchaser) {
        StorePanel storePanel = new StorePanel(controller, towerUpgrader, itemPurchaser);
        return storePanel;
    }

    private InfoDisplayPanel addInfoDisplay () {
        InfoDisplayPanel infoPanel = new InfoDisplayPanel(ViewConstants.resourceBundle
                .getString("ItemInfo"));
        return infoPanel;
    }

    @Override
    public void initialize () {
        infoPanel.setVisible(true);
        this.pack();
    }

    /**
     * Destroys the jgame instance so that it can be reloaded
     */
    public void quitGame () {
        canvasPanel.quitGame();
    }

    public void endGame () {
        infoPanel.clearDisplay();
        canvasPanel.endGame();
        storePanel.endGame();
        this.pack();
    }
}
