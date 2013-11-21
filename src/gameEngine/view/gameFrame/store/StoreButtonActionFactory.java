package gameEngine.view.gameFrame.store;

import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.gameFrame.PuchaseSimulator;

public class StoreButtonActionFactory {
   private PuchaseSimulator itemPlacer;
   private  InfoDisplayPanel infoPanel;
    public StoreButtonActionFactory(PuchaseSimulator itemPlacer, InfoDisplayPanel infoPanel){
        this.itemPlacer = itemPlacer;
        this.infoPanel = infoPanel;
    }
    public StoreButtonAction createStoreButtionAction(String action, final PurchaseInfo tower ){
        if (action.equals("hoverEnter")){
            return new StoreButtonAction() {
                @Override
                public void executeAction () {
          
                    infoPanel.displayInformation(tower.getInfo());
                }
            };
            
        }
        if (action.equals("hoverExit")){
            return new StoreButtonAction() {

                @Override
                public void executeAction () {
                   infoPanel.clearDisplay();

                }

            };
        }
        if (action.equals("click")){
            return new StoreButtonAction() {

                @Override
                public void executeAction () {
                    itemPlacer.placeItem(tower);

                }

            };
        }
        return null;
        
    }
}
