package gameEngine.view.gameFrame;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.GameInfo;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
import gameEngine.view.View;
import gameEngine.view.gameFrame.gameObjects.FrameRateSlider;
import gameEngine.view.gameFrame.gameObjects.RangeDisplay;
import gameEngine.view.gameFrame.towerUpdater.TowerUpgrader;
import java.awt.Dimension;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jgame.Highscore;
import jgame.JGColor;
import jgame.JGObject;
import jgame.JGPoint;
import jgame.platform.StdGame;


/**
 * @author Lalita Maraj Alex Zhu
 *         Displays the Game setting using JGEngine to facilitate
 *         graphics rendering
 */
public class Game extends StdGame {
    private static final String[] DISPLAY_KEYS = { GameEngineConstant.PURCHASE_INFO_NAME,
                                                  GameEngineConstant.TOWER_DAMAGE,
                                                  GameEngineConstant.TOWER_ATTACK_SPEED,
                                                  GameEngineConstant.TOWER_ATTACK_AMOUNT,
                                                  GameEngineConstant.TOWER_RANGE,
                                                  GameEngineConstant.TOWER_MAGIC,
                                                  GameEngineConstant.TOWER_MAGIC_FACTOR,
                                                  GameEngineConstant.TOWER_BOOST_FACTOR,
                                                  GameEngineConstant.TOWER_SELL_PRICE,
                                                  GameEngineConstant.TOWER_UPGRADE_PRICE,
                                                  GameEngineConstant.PURCHASE_INFO_DESCRIPTION };

    private int WIDTH = 600;
    private int HEIGHT = 600;

    private View view;
    private TowerUpgrader utilities;

    private FrameRateSlider frameRateSlider;
    private JGObject frameRateBar;
    private ItemPurchaser itemPurchaser;
    private Map<String, String> valuesToDisplay;

    private Collection<GameInitializable> gameInitializerItems;

    private Collection<GameUpdatable> gameUpdatables;

    private Map<String, KeyActivationItem> keyActivationItems;

    private GameInfo gameInfo;

    public Game (View view,
                 ItemPurchaser itemPurchaser,
                 TowerUpgrader utilities,
                 Collection<GameInitializable> gameInitializerItems,
                 Collection<GameUpdatable> gameUpdatables,
                 Map<String, KeyActivationItem> keyActivationItems) {
        this.view = view;
        this.keyActivationItems = keyActivationItems;
        this.itemPurchaser = itemPurchaser;
        this.utilities = utilities;
        this.gameInitializerItems = gameInitializerItems;
        this.gameUpdatables = gameUpdatables;
        initEngineComponent(WIDTH, HEIGHT);
    }

    @Override
    public void initCanvas () {
        gameInfo = view.getGameInfo();

        this.setMoneyTitle("TEST");
        this.setLivesTitle("TEST");
        Dimension size = gameInfo.getDimension();// view.getGameSize();

        setCanvasSettings(size.width, size.height, WIDTH / size.width,
                          HEIGHT / size.height, null, JGColor.white, null);
    }

    @Override
    public void initGame () {
        setFrameRate(30, 2);
        defineMedia("mygame.tbl");

        initial_lives = gameInfo.getLife();// view.getLives();
        lives = initial_lives;// view.getLives();
        score = gameInfo.getGold();// view.getMoney();
        defineImage("RESERVEDslider_bar", "sb", 256, "slider_bar.png", "-");
        defineImage("RESERVEDslider_toggle", "sb", 256, "slider_toggle.png", "-");
        String background = view.getGameInfo().getBGImage();// gameInfo.getBGImage();//view.getBGImage();

        setBGImage(background);
        setHighscores(10, new Highscore(0, "aaa"), 3);
        startgame_ingame = true;
        List<Tile> pathList = view.getPath();
        int tileCount = 0;
        for (Tile tile : pathList) {
            defineImage("tile" + String.valueOf(tileCount), "#" + String.valueOf(tileCount), 256,
                        tile.getPathImage(), "-");
            JGPoint tilePos = getTileIndex(tile.getX(), tile.getY());
            setTile(tilePos.x, tilePos.y, "#" + String.valueOf(tileCount));
            tileCount++;
        }

        this.game_title = gameInfo.getMyName();

        valuesToDisplay = new LinkedHashMap<String, String>();
        for (String str : DISPLAY_KEYS) {
            valuesToDisplay.put(str, "black");
        }
    }

    public void startInGame () {
        view.startModel();
        for (GameInitializable item : gameInitializerItems) {
            item.initialize();
        }
        frameRateSlider =
                new FrameRateSlider("slider", true, pfWidth() / 2, pfHeight() - 40, 256,
                                    "slider_toggle");
        frameRateBar =
                new JGObject("sliderbar", true, pfWidth() / 2 - 84, pfHeight() - 30, 256,
                             "slider_bar");
        frameRateBar.resume_in_view = false;
        toggleFrameRateBar();
    }

    public void doFrameInGame () {
        moveObjects();
        gameInfo = view.getGameInfo();
        checkCollision(GameEngineConstant.BULLET_CID, GameEngineConstant.ENEMY_CID);
        checkCollision(GameEngineConstant.ENEMY_CID, GameEngineConstant.BULLET_CID);
        checkCollision(0, 0);
        checkUserInteractions();
        updateGameStats();
        for (GameUpdatable updatable : gameUpdatables) {
            updatable.update();
        }
        for (String key : keyActivationItems.keySet()) {
            KeyActivationItem item = keyActivationItems.get(key);
            if (getKey(key.charAt(0))) {
                clearKey(key.charAt(0));
                item.activate();
            }
        }

        if (getKey(KeyEsc)){
            endGame();
        }
        
        if (getKey('F')){
            clearKey('F');
            toggleFrameRateBar();
        }
        if (gameInfo.getIsWin()) {
            wonGame();
        }
        if (lives <= 0) endGame();
    }

    /**
     * Checks if the user has clicked on the screen, and if so, either tries to
     * buy a tower at the clicked mouse position or tries to get the info of
     * the tower at the clicked mouse position
     */
    public void checkUserInteractions () {
        if (getMouseButton(1) && getMouseInside()) {
            clearMouseButton(1);
            JGPoint mousePosition = getMousePos();
            if (itemPurchaser.checkAndPlaceTower(mousePosition)) {
                System.out.format("Buying at: %d,%d\n", mousePosition.x, mousePosition.y);
            }
            else {
                PurchaseInfo tower = view.getTowerInfo(mousePosition.x, mousePosition.y);
                if (tower == null) {
                    System.out.println("No tower here");
                }
                else {
                    utilities.displayCheckedInformation(tower.getInfo(), valuesToDisplay,
                                                        mousePosition.x, mousePosition.y);
                    System.out.println("Checking tower");
                }
            }
        }
    }

    /**
     * Updates the number of lives and money remaining in the game
     */
    public void updateGameStats () {
        lives = gameInfo.getLife();
        score = gameInfo.getGold();
    }

    /**
     * Call if the user has won the game, will display VICTORY and go
     * to title screen
     */
<<<<<<< HEAD
    public void wonGame () {
        // removeGameObjects();
=======
    public void wonGame(){
        view.stopWaves();
//        removeGameObjects();
>>>>>>> e55d8a32641e38e830b680c66e8f0369ff1eecc8
        gameWon();
    }

    /**
     * Call if the user has lost the game, will display
     * GAME OVER and go to title screen
     */
<<<<<<< HEAD
    public void endGame () {
        // view.startModel();
=======
    public void endGame(){
        view.stopWaves();
        //view.startModel();
>>>>>>> e55d8a32641e38e830b680c66e8f0369ff1eecc8
        gameOver();
    }

    /**
     * Removes all JGObjects and JGTimers
     */
    public void removeGameObjects () {
        this.removeAllTimers();
        removeObjects(null, 0);
    }

    /**
     * Suspends or resumes frame rate bar
     */
    public void toggleFrameRateBar () {
        if (frameRateSlider.is_suspended) {
            frameRateSlider.resume();
            frameRateBar.resume();
        }
        else {
            frameRateSlider.suspend();
            frameRateBar.suspend();
        }
    }

}
