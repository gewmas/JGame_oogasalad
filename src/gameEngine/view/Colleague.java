package gameEngine.view;

import gameEngine.model.TowerInfo;


public interface Colleague {

    public void addMediator (Mediator mediator);

    public void placeTower (TowerInfo towerInfo);

    public void purchaseTower ();

    public void displayTowerInfo (TowerInfo tower);

}
