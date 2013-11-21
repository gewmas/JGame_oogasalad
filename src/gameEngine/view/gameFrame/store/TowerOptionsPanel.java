package gameEngine.view.gameFrame.store;

import java.util.List;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;


public class TowerOptionsPanel extends StoreOptionsPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected TowerOptionsPanel (GameFrameMediator mediator, View view) {
        super(mediator, view);

    }

    @Override
    protected List<PurchaseInfo> getItems () {
        // TODO Auto-generated method stub
        return view.getTowers();
    }

}
