package gameEngine.model;

import gameEngine.parser.Parser;
import java.awt.Dimension;


/*
 * Author wenxin shi
 */

public class GameInfo {
    private String myName;
    private int myGold;
    private int myLife;
    private int myWave;

    private String myBGImage;

    private Dimension myDimension;
    // private List<TowerInfo> myTowerInfo;

    private Parser myParser;

    public GameInfo (Parser parser) {
          myGold=parser.getInt("gold");
          myLife =parser.getInt("numberOfLives");
//        wave will be changed when the different levels are load.
        myWave = 1000;
        int x = parser.getInt("widthOfWindow");
        int y = parser.getInt("heightOfWindow");
        int numPerRow =parser.getInt("tilesPerRow");
//      myDimension=new Dimension(x/numPerRow,y/numPerRow);
//      right now I just put the 20*20 dimension
        myDimension = new Dimension(20, 20);
        myBGImage = parser.getString("BGImage");
        myName = parser.getString("name");
        // myTowerInfo = new ArrayList<TowerInfo>();
        
    //    System.out.println(myGold+";"+myLife+";"+myWave+";"+x+";"+y+";"+myBGImage);
        
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


    public String getMyName () {
        return myName;
    }


    // public void addTowerInfo (TowerInfo tower) {
    // myTowerInfo.add(tower);
    // }
    //
    // public void removeTowerInfo (TowerInfo tower) {
    // myTowerInfo.remove(tower);
    // }
    //
    // public void removeAllTowerInfo () {
    // myTowerInfo = new ArrayList<TowerInfo>();
    // }
    //
    // public List<TowerInfo> getTowerInfo () {
    // return myTowerInfo;
    // }

}
