package gameEngine.model;

import gameEngine.parser.Parser;
import gameEngine.constant.*;
import java.awt.Dimension;


/*
 * Author wenxin shi
 */

public class GameInfo {
    private String myName;
    private int myGold;
    private int myLife;
    private int myWave;
    private boolean isWin;
    private int myCurrentWaveNumber=0;
    private String myBGImage;
    private String myGoldName;
    private String myLivesName;
    
    private Dimension myDimension;
    // private List<TowerInfo> myTowerInfo;

    private Parser myParser;

    public GameInfo (Parser parser) {
          myGold=parser.getInt("gold");
          myLife =parser.getInt("numberOfLives");
//        wave will be changed when the different levels are load.
        myWave = 1000;
        int x = GameEngineConstant.GRID_WIDTH;
        int y = GameEngineConstant.GRID_HEIGHT;
        int numPerRow = GameEngineConstant.TILES_PER_ROW;
//      right now I just put the 20*20 dimension
        myDimension = new Dimension(20, 20);
        myBGImage = parser.getString("BGImage");
        myName = parser.getString("name");
        myGoldName = parser.getString("goldName");
        myLivesName = parser.getString("livesName");
    }


    public Dimension getDimension () {
        return myDimension;
    }



    public int getGold () {
        return myGold;
    }

    public int getLife () {
        return myLife;
    }

    public int getWave () {
        return myWave;
    }

    public void setGold (int gold) {
        myGold = gold;
    }

    public void addGold (int gold) {
        myGold += gold;
    }

    public void loseGold (int gold) {
        myGold -= gold;
    }

    public void setLife (int life) {
        myLife = life;
    }

    public void addLife () {
        myLife++;
    }
    
    public void addLife (int amt) {
        myLife += amt;
    }

    public void loseLife (int damage) {
        myLife-=damage;
    }

    public void nextWave () {
        myWave--;
    }

    public void setWave (int numWave) {
        myWave = numWave;
    }

    public void setBGImage (String BGImage) {
        myBGImage = BGImage;
    }


    public String getBGImage () {
        return myBGImage;
    }


    public String getMyName () {
        return myName;
    }

    public void SetIsWin(boolean b){
        isWin=b;
    }
    public boolean getIsWin(){
        return isWin;
    }
    public void SetCurrentWaveNumber(int n){
        myCurrentWaveNumber=n;
    }
    public int getCurrentWaveNumber(){
        return myCurrentWaveNumber;
    }

    // @Author: Fabio
    public String getMyGoldName () {
        return myGoldName;
    }

    // @Author: Fabio
    public String getMyLivesName () {
        return myLivesName;
    }

}
