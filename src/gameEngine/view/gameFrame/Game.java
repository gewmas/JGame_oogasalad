package gameEngine.view.gameFrame;

import gameEngine.Constant.Constant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
import gameEngine.view.View;
import gameEngine.view.gameFrame.gameObjects.FrameRateSlider;
import java.awt.Dimension;
import java.util.List;
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
    
    private int WIDTH = 600;
    private int HEIGHT = 600;

    private View view;
    private boolean purchasing;
    private String towerToPurchase;
    private GameFrameMediator mediator;
    private FrameRateSlider frameRateSlider;
    private JGObject frameRateBar;

    public Game (View view, GameFrameMediator mediator) {
        this.view = view;
        this.mediator = mediator;
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
        String bgImage = "space_background.jpg";
        defineImage("background", "bg", 256, bgImage, "-");
        defineImage("RESERVEDslider_bar","sb",256,"slider_bar.png","-");
        defineImage("RESERVEDslider_toggle","sb",256,"slider_toggle.png","-");
        String background=view.getBGImage();
        
        setBGImage(background);
        purchasing = false;
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
    }
    
    public void startInGame() {
        view.startModel();
        mediator.openStore();
        mediator.updateStoreStatus();
        
    }

    public void doFrameInGame () {
        moveObjects();
        checkCollision(Constant.BULLET_CID, Constant.ENEMY_CID);
        checkCollision(Constant.ENEMY_CID, Constant.BULLET_CID);
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
            JGPoint tilePosition = getTileIndex(mousePosition.x, mousePosition.y);
            if (purchasing) {
                if (view.buyTower(mousePosition.x, mousePosition.y, towerToPurchase)){
                    towerToPurchase=null;
                    purchasing = false;
                    mediator.exitPurchase();
                }
                System.out.format("Buying %s at: %d,%d\n",towerToPurchase, mousePosition.x, mousePosition.y);
            }
            else {
                PurchaseInfo tower=view.getTowerInfo(mousePosition.x, mousePosition.y);
                if (tower==null) {
                    System.out.println("No tower here");
                } else {
                    mediator.displayTowerInfo(tower.getInfo());
                    System.out.println("Checking tower");
                }
                System.out.format("Checking tower at: %d,%d\n", mousePosition.x, mousePosition.y);
            }
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

    /**
     * Indicates that the user wants to buy a tower
     */
    public void placeTower (PurchaseInfo purchaseInfo) {
        // setBGColor(JGColor.red);
        String towerName=purchaseInfo.getInfo().get("Name");
        if (towerName==null || towerName.equals(towerToPurchase)){
            mediator.exitPurchase();
            System.out.println("Tower cancelled");
            towerToPurchase=null;
            purchasing=false;
            return;
        }
        mediator.setCursorImage(purchaseInfo);
        purchasing = true;
        towerToPurchase = towerName;
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
