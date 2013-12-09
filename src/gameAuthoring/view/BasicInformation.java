package gameAuthoring.view;

public class BasicInformation {

    private int myGold;
    private int myLives;
    private String mySplashImage;
    private String myGameName;
    private AudioLabel myBackgroundAudio;
    private String myLivesName;
    private String myGoldName;
    private String myBulletName;

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

    public int getGold () {
        return myGold;
    }

    public int getLives () {
        return myLives;
    }

    public String getSplashImage () {
        return mySplashImage;
    }

    public String getGameName () {
        return myGameName;
    }

    public AudioLabel getBackgroundAudio () {
        return myBackgroundAudio;
    }

    public String getLivesName () {
        return myLivesName;
    }

    public String getGoldName () {
        return myGoldName;
    }

    public String getBulletName () {
        return myBulletName;
    }

}
