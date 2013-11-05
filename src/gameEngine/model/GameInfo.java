package gameEngine.model;

public class GameInfo {
    private int myGold;
    private int myLife;
    private int myWave;

    public GameInfo (int defaultGold, int defaultLife, int waveNum) {
        myGold = defaultGold;
        myLife = defaultLife;
        myWave = waveNum;
    }

    public int getGold () {
        return myGold;
    }

    public void setGold(int gold){
        myGold=gold;
    }
    
    public void addGold (int gold) {
        myGold += gold;
    }

    public void loseGold (int gold) {
        myGold -= gold;
    }

    public int getLife () {
        return myLife;
    }

    public void setLife(int life){
        myLife=life;
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
    
    public void setWave(int numWave){
        myWave=numWave;
    }
    
    public int getWaves(){
        return myWave;
    }

}
