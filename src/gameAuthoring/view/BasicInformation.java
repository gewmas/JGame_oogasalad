package gameAuthoring.view;

/**
 * @author Rebecca Lai & Susan Zhang
 *         BasicInformation is a data container that allows for use of Java's Observer/Observable
 *         interface. BasicInformation is passed from an Observable to an Observer. The Observer
 *         (most likely a controller) can then extract information from BasicInformation.
 */
public class BasicInformation {

    private int myGold;
    private int myLives;
    private String mySplashImage;
    private String myGameName;
    private AudioLabel myBackgroundAudio;
    private String myLivesName;
    private String myGoldName;
    private String myBulletName;

    /**
     * @param gold is the starting amount of gold in the game
     * @param lives is the starting number of lives in the game
     * @param splashImage is the path of the game's splash image
     * @param gameName is the user-defined name for the game
     * @param backgroundAudio is the background audio for the game
     * @param livesName is the alternate name for live
     * @param goldName is the alternate name for gold
     * @param bulletName is the alternate name for bullets
     */
    public BasicInformation (int gold,
                             int lives,
                             String splashImage,
                             String gameName,
                             AudioLabel backgroundAudio,
                             String livesName,
                             String goldName,
                             String bulletName) {
        myGold = gold;
        myLives = lives;
        mySplashImage = splashImage;
        myGameName = gameName;
        myBackgroundAudio = backgroundAudio;
        myLivesName = livesName;
        myGoldName = goldName;
        myBulletName = bulletName;
    }

    /**
     * @return starting amount of gold in game
     */
    public int getGold () {
        return myGold;
    }

    /**
     * @return starting number of lives in game
     */
    public int getLives () {
        return myLives;
    }

    /**
     * @return path of game's splash image
     */
    public String getSplashImage () {
        return mySplashImage;
    }

    /**
     * @return game name
     */
    public String getGameName () {
        return myGameName;
    }

    /**
     * @return background audio as represented by an AudioLabel
     */
    public AudioLabel getBackgroundAudio () {
        return myBackgroundAudio;
    }

    /**
     * @return user-defined alternate name for lives
     */
    public String getLivesName () {
        return myLivesName;
    }

    /**
     * @return user-defined alternate name for gold
     */
    public String getGoldName () {
        return myGoldName;
    }

    /**
     * @return user-defined alternate name for bullets
     */
    public String getBulletName () {
        return myBulletName;
    }

}
