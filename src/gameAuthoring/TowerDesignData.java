package gameAuthoring;

import java.util.ArrayList;
import java.util.List;


public class TowerDesignData {
    // Tower Info
    // Index of each list corresponds to one tower
    // Example: Tower 0 will have tower ID that is index 0 in myTowerIDs, tower damage that is index
    // 0 in myTowerDamages, attack speed that is index 0 in myTowerAttackSpeeds...
    private List<String> myTowerIDs = new ArrayList<String>();
    private List<String> myTowerImages = new ArrayList<String>();
    private List<Integer> myTowerDamages = new ArrayList<Integer>();
    private List<Integer> myTowerAttackSpeeds = new ArrayList<Integer>();
    private List<Integer> myTowerAttackRanges = new ArrayList<Integer>();
    private List<Integer> myTowerCosts = new ArrayList<Integer>();
    private List<Integer> myTowerRecyclePrices = new ArrayList<Integer>();
    private List<Integer> myTowerLives = new ArrayList<Integer>();

    public TowerDesignData () {
        // TODO Auto-generated constructor stub
    }

    public void addTowerID (String towerID) {
        myTowerIDs.add(towerID);
    }

    public List<String> getTowerIDs () {
        return myTowerIDs;
    }

    public void addTowerImage (String towerImage) {
        myTowerImages.add(towerImage);
    }

    public List<String> getTowerImages () {
        return myTowerImages;
    }

    public void addTowerDamage (int towerDamage) {
        myTowerDamages.add(towerDamage);
    }

    public List<Integer> getTowerDamages () {
        return myTowerDamages;
    }

    public void addTowerAttackSpeed (int towerAttackSpeed) {
        myTowerAttackSpeeds.add(towerAttackSpeed);
    }

    public List<Integer> getTowerAttackSpeeds () {
        return myTowerAttackSpeeds;
    }

    public void addTowerCost (int towerCost) {
        myTowerCosts.add(towerCost);
    }

    public List<Integer> getTowerCosts () {
        return myTowerCosts;
    }

    public void addTowerRecyclePrice (int towerRecyclePrice) {
        myTowerRecyclePrices.add(towerRecyclePrice);
    }

    public List<Integer> getTowerRecyclePrices () {
        return myTowerRecyclePrices;
    }

    public void addTowerLife (int towerLife) {
        myTowerLives.add(towerLife);
    }

    public List<Integer> getTowerLives () {
        return myTowerLives;
    }

}
