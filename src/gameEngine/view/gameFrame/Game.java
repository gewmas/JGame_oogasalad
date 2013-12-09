package gameEngine.view.gameFrame;

import gameEngine.constant.GameEngineConstant;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.model.GameInfo;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
import gameEngine.view.View;
import gameEngine.view.gameFrame.gameObjects.FrameRateSlider;
import gameEngine.view.gameFrame.tools.DisplayValue;
import gameEngine.view.gameFrame.towerUpdrader.ItemOptionsDisplayer;
import java.awt.Dimension;
import java.util.ArrayList;
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

    private ControllerToViewInterface controller;
    private ItemOptionsDisplayer utilities;

    private FrameRateSlider frameRateSlider;
    private JGObject frameRateBar;
    private ItemPurchaser itemPurchaser;
    private Map<String, String> valuesToDisplay;

    private GameInitializable gameInitializerItems;
    private GameUpdatable gameUpdatables;
    private Collection<KeyActivationItem> keyActivationItems;

    private GameInfo gameInfo;

    public Game (ControllerToViewInterface controller,
                 ItemPurchaser itemPurchaser,
                 ItemOptionsDisplayer utilities,
                 GameInitializable gameInitializerItems,
                 GameUpdatable gameUpdatables,
                 Collection<KeyActivationItem> keyActivationItems) {
        this.controller = controller;
        this.keyActivationItems = keyActivationItems;
        this.itemPurchaser = itemPurchaser;
        this.utilities = utilities;
        this.gameInitializerItems = gameInitializerItems;
        this.gameUpdatables = gameUpdatables;
        initEngineComponent(WIDTH, HEIGHT);
    }

    @Override
    public void initCanvas () {

        gameInfo = controller.getGameInfo();

        this.setMoneyTitle(gameInfo.getMyGoldName());
        this.setLivesTitle(gameInfo.getMyLivesName());
        Dimension size = gameInfo.getDimension();// view.getGameSize();

        setCanvasSettings(size.width, size.height, WIDTH / size.width,
                          HEIGHT / size.height, null, JGColor.white, null);
    }

    @Override
    public void initGame () {
        setFrameRate(30, 2);
        defineMedia("mygame.tbl");

        setHighscores(
                      10, // number of highscores
                      new Highscore(0, "nobody"), // default entry for highscore
                      25 // max length of the player name
                );

        initial_lives = gameInfo.getLife();// view.getLives();
        lives = initial_lives;// view.getLives();
        score = gameInfo.getGold();// view.getMoney();
        defineImage("RESERVEDslider_bar", "sb", 256, "slider_bar.png", "-");
        defineImage("RESERVEDslider_toggle", "sb", 256, "slider_toggle.png", "-");
        String background = controller.getGameInfo().getBGImage();// gameInfo.getBGImage();//view.getBGImage();

        setBGImage(background);
        startgame_ingame = true;
        List<Tile> pathList = controller.getPath();
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
        controller.startGame();

        gameInitializerItems.initialize();

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
        gameInfo = controller.getGameInfo();
        checkGameCollisions();
        checkUserInteractions();
        updateGameStats();
        gameUpdatables.update();

        for (KeyActivationItem item : keyActivationItems) {

            if (getKey(item.getActivationKey().getKeyChar())) {
                clearKey(item.getActivationKey().getKeyChar());
                item.activate();
            }
        }

        if (getKey(KeyEsc)) {
            clearKey(KeyEsc);
            loseGame();
        }

        if (getKey(ActivationKey.FRAME.getKeyChar())) {
            clearKey(ActivationKey.FRAME.getKeyChar());
            toggleFrameRateBar();
        }
        if (gameInfo.getIsWin()) {
            wonGame();
        }
        if (lives <= 0 || gameInfo.getGold() < 0) loseGame();
    }

    private void checkGameCollisions () {
        checkCollision(GameEngineConstant.BULLET_CID, GameEngineConstant.ENEMY_CID);
        checkCollision(GameEngineConstant.ENEMY_CID, GameEngineConstant.BULLET_CID);
        checkCollision(0, 0);
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
            System.out.println(mousePosition.x);
            System.out.println(mousePosition.y);
            itemPurchaser.checkAndPlaceTower(mousePosition);
            if (itemPurchaser.isPurchasing()){
                PurchaseInfo tower = controller.getTowerInfo(mousePosition.x, mousePosition.y);
                List<DisplayValue> display = new ArrayList<DisplayValue>();
                if (tower != null) {
                    for (String key : valuesToDisplay.keySet()) {
                        if (tower.getInfo().get(key) != null) {
                            String field = key;
                            String value = tower.getInfo().get(key);
                            String color = valuesToDisplay.get(key);

                            display.add(new DisplayValue(field, value, color));
                        }
                    }
                    utilities.displayTowerInformation(tower.getInfo(), display,
                                                      mousePosition.x, mousePosition.y);
                    // utilities.displayCheckedInformation(tower.getInfo(), valuesToDisplay,
                    // mousePosition.x, mousePosition.y);
                }
            }

        }
    }

    /**
     * Updates the number of lives and money remaining in the game
     */
    public void updateGameStats () {
        lives = gameInfo.getLife();
        money = gameInfo.getGold();
        score = money + gameInfo.getCurrentWaveNumber() * 100 + lives;
    }

    /**
     * Call if the user has won the game, will display VICTORY and go
     * to title screen
     */

    public void wonGame () {
        endGame();
        gameWon();
    }

    /**
     * Call if the user has lost the game, will display
     * GAME OVER and go to title screen
     */

    public void loseGame () {
        endGame();
        gameOver();
    }

    /**
     * Standard routine whenever the game ends
     */

    public void endGame () {
        controller.stopWaves();
        gameUpdatables.endGame();

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
