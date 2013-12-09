package gameEngine.view.gameFrame;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gameEngine.view.ViewConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.inputFrame.InputFrame;
import gameEngine.view.gameFrame.inputFrame.InputSender;
import gameEngine.view.gameFrame.menu.Menu;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;
import gameEngine.view.gameFrame.tools.store.StorePanel;
import gameEngine.view.gameFrame.towerUpdrader.ItemOptionsDisplayer;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj Alex Zhu
 * 
 */
public class GameFrame extends JFrame implements GameInitializable {

    private View view;
    private CheatFrame cheatCodeFrame;
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
     * @param view
     */
    public GameFrame (final View view) {
        super();

        this.view = view;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cheatCodeFrame = addCheatCodeFrame(view);

        this.gameKeyActivationItems = new ArrayList();
        gameKeyActivationItems.add(cheatCodeFrame);

        infoPanel = addInfoDisplay();
        towerUpgrader = new ItemOptionsDisplayer(infoPanel, view);
        itemPurchaser = new ItemPurchaser(view, this);
        storePanel = addStorePanel(towerUpgrader, itemPurchaser);
        gameInitializerItems = new CompositeGameInitializable();
        gameUpdatables = new CompositeGameUpdatable();
        gameUpdatables.add(storePanel);
        addGameTools(infoPanel, storePanel);

        setJMenuBar(new Menu(view));
    }

    private CheatFrame addCheatCodeFrame (final View view) {
        InputFrame inputFrame = new InputFrame("Cheat Sheet", new InputSender() {
            @Override
            public void submit (String cheat) {
                view.activateCheat(cheat);
            }
        });
        return new CheatFrame(inputFrame);
    }

    public void showGame () {
        createGame();
        pack();
        setVisible(true);
    }

    public void createGame () {
        gameInitializerItems.add(this);
        canvasPanel =
                new CanvasPanel(view, itemPurchaser, towerUpgrader, gameInitializerItems,
                                gameUpdatables, gameKeyActivationItems);
        this.add(canvasPanel, BorderLayout.WEST);

        towerUpgrader.createRangeDisplay();
    }

    /**
     * Create the store of Towers
     * 
     * @param storePanel
     * @param infoPanel
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
        StorePanel storePanel = new StorePanel(view, towerUpgrader, itemPurchaser);
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
    }
}
