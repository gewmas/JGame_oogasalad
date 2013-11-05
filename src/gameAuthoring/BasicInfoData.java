package gameAuthoring;

public class BasicInfoData {

    // Basic Game Info
    // Game name, splash image, etc.
    private String myGameName;
    private String mySplashImage;
    private int myGold;
    private int myLives;
    private int myNumLevels;

    public BasicInfoData () {
        // TODO Auto-generated constructor stub
    }

    public void setGameName (String gameName) {
        myGameName = gameName;
    }

    public String getGameName () {
        return myGameName;
    }

    public void setSplashImage (String splashImage) {
        mySplashImage = splashImage;
    }

    public String getSplashImage () {
        return mySplashImage;
    }

    public void setGold (int gold) {
        myGold = gold;
    }

    public int getGold () {
        return myGold;
    }

    public void setLives (int lives) {
        myLives = lives;
    }

    public int getLives () {
        return myLives;
    }

    public void setNumLevels (int numLevels) {
        myNumLevels = numLevels;
    }

    public int getNumLevels () {
        return myNumLevels;
    }

}
