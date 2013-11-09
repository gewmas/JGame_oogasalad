package gameEngine.model;


import java.util.ArrayList;
import java.util.List;
/*
 * Author wenxin shi
 */


public class GameInfo {
    private int myGold;
    private int myLife;
    private int myWave;

    private String myBGImage;
    private List<TowerInfo> myTowerInfo;

    public GameInfo (int defaultGold, int defaultLife, int waveNum, String BGImage) {
        myGold = defaultGold;
        myLife = defaultLife;
        myWave = waveNum;
        myBGImage = BGImage;
        myTowerInfo = new ArrayList<TowerInfo>();
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

    public void loseLife () {
        myLife--;
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

    public void addTowerInfo (TowerInfo tower) {
        myTowerInfo.add(tower);
    }

    public void removeTowerInfo (TowerInfo tower) {
        myTowerInfo.remove(tower);
    }
    
    public void removeAllTowerInfo(){
        myTowerInfo=new ArrayList<TowerInfo>();
    }

    
    public List<TowerInfo> getTowerInfo () {
        return myTowerInfo;
    }


}
