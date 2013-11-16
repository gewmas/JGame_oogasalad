package gameEngine.view.gameFrame.store;

import java.util.List;
import gameEngine.model.tower.TowerInfo;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;

public class TowerOptionsPanel extends StoreOptionsPanel{

    protected TowerOptionsPanel (GameFrameMediator mediator, View engineView) {
        super(mediator, engineView);
        
    }

    @Override
    protected List<TowerInfo> getItems () {
        // TODO Auto-generated method stub
        return view.getTowers();
    }
  

}