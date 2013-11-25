package gameEngine.view.gameFrame;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
import gameEngine.view.View;
import gameEngine.view.gameFrame.gameObjects.FrameRateSlider;
import gameEngine.view.gameFrame.gameObjects.RangeDisplay;
import java.awt.Dimension;
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
    private static final String[] DISPLAY_KEYS={GameEngineConstant.PURCHASE_INFO_NAME,
                                                GameEngineConstant.TOWER_DAMAGE,
                                                GameEngineConstant.TOWER_ATTACK_SPEED,
                                                GameEngineConstant.TOWER_ATTACK_AMOUNT,
                                                GameEngineConstant.TOWER_RANGE,
                                                GameEngineConstant.TOWER_MAGIC,
                                                GameEngineConstant.TOWER_MAGIC_FACTOR,
                                                GameEngineConstant.TOWER_BOOST_FACTOR,
                                                GameEngineConstant.TOWER_SELL_PRICE,
                                                GameEngineConstant.TOWER_UPGRADE_PRICE,
                                                GameEngineConstant.PURCHASE_INFO_DESCRIPTION};
    
    private int WIDTH = 600;
    private int HEIGHT = 600;

    private View view;
    private PurchaseInfo towerToPurchase;
    private Utilities utilities;
    private GameFrameMediator mediator;
    private FrameRateSlider frameRateSlider;
    private JGObject frameRateBar;
    private ItemPurchaser itemPurchaser;
    private Map<String,String> valuesToDisplay;

    public Game (View view, GameFrameMediator mediator, ItemPurchaser itemPurchaser, Utilities utilities) {
        this.view = view;
        this.mediator = mediator;
        this.itemPurchaser = itemPurchaser;
        this.utilities=utilities;
        initEngineComponent(WIDTH, HEIGHT);
    }

    @Override
    public void initCanvas () {
        Dimension size = view.getGameSize();
        setCanvasSettings(size.width, size.height, WIDTH / size.width,
                          HEIGHT / size.height, null, JGColor.white, null);
    }

    @Override
    public void initGame () {
        setFrameRate(30, 2);
        defineMedia("mygame.tbl");
        initial_lives = view.getLives();
        lives = view.getLives();
        score = view.getMoney();
        defineImage("RESERVEDslider_bar","sb",256,"slider_bar.png","-");
        defineImage("RESERVEDslider_toggle","sb",256,"slider_toggle.png","-");
        String background=view.getBGImage();
        
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
        frameRateBar=new JGObject("zfrzsliderbar", true, pfWidth()/2-84, pfHeight()-30, 256, "RESERVEDslider_bar");
        frameRateSlider=new FrameRateSlider("frslider", true, pfWidth()/2, pfHeight()-40,256,"RESERVEDslider_toggle");
        frameRateBar.resume_in_view=false;
        toggleFrameRateBar();
  
        this.game_title=view.getGameTitle();
        
        valuesToDisplay=new LinkedHashMap<String,String>();
        for (String str:DISPLAY_KEYS){
            valuesToDisplay.put(str, "black");
        }
    }
    
    public void startInGame() {
        view.startModel();
        mediator.openStore();
        mediator.openInfoPanel();
        mediator.updateStoreStatus();
        
    }

    public void doFrameInGame () {
        moveObjects();
        checkCollision(GameEngineConstant.BULLET_CID, GameEngineConstant.ENEMY_CID);
        checkCollision(GameEngineConstant.ENEMY_CID, GameEngineConstant.BULLET_CID);
        checkCollision(0, 0);
        checkUserInteractions();
        updateGameStats();
        mediator.updateStoreStatus();
        if (getKey(KeyEsc)) {
            clearKey(KeyEsc);
            lives = 0;
        }
        if (getKey('F')){
            clearKey('F');
            toggleFrameRateBar();
        }
        if (lives<=0) endGame();
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
            //if (purchasing) {
                itemPurchaser.checkAndPlaceTower(mousePosition);
                System.out.format("Buying at: %d,%d\n",mousePosition.x, mousePosition.y);
            //}
            //else {
                PurchaseInfo tower=view.getTowerInfo(mousePosition.x, mousePosition.y);
                if (tower==null) {
                    System.out.println("No tower here");
                } else {
                    utilities.displayCheckedInformation(tower.getInfo(),valuesToDisplay,mousePosition.x,mousePosition.y);
                    JGPoint tileCoords=this.getTileCoord(getTileIndex(mousePosition.x, mousePosition.y));
                    System.out.println("Checking tower");
                }
                //System.out.format("Checking tower at: %d,%d\n", mousePosition.x, mousePosition.y);
            //}
        }
    }

    /**
     * Updates the number of lives and money remaining in the game
     */
    public void updateGameStats () {
        lives = view.getLives();
        score = view.getMoney();
    }

    @Override
    public void paintFrame () {
        super.paintFrame();
        // drawString("Money " + String.valueOf(sc), pfWidth() - 10, 10, 1);
    }
    
    public void endGame(){
        //view.startModel();
        removeGameObjects();
        gameOver();
        removeGameObjects();
    }
    
    public void removeGameObjects(){
        this.removeAllTimers();
        removeObjects(null,0);
    }
    
    public void toggleFrameRateBar(){
        if (frameRateSlider.is_suspended){
            frameRateSlider.resume();
            frameRateBar.resume();
        } else {
            frameRateSlider.suspend();
            frameRateBar.suspend();
        }
    }

}
