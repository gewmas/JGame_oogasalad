package gameEngine.view.gameFrame.store;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import gameEngine.view.Frame;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;


public class ToolsFrame extends Frame {

    public ToolsFrame(GameFrameMediator mediator, View view){
        super();
        JPanel toolsMainPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
       
        toolsMainPanel. setLayout(borderLayout);
        StorePanel storePanel = new StorePanel(mediator, view);
        mediator.addStore(storePanel);
        this.add(storePanel, BorderLayout.PAGE_START);
        
        InfoDisplayPanel infoPanel = new InfoDisplayPanel(StyleConstants.resourceBundle
                                                          .getString("ItemInfo"));
                                                  mediator.addInfoPanel(infoPanel);
       mediator.addInfoPanel(infoPanel);                                         
       toolsMainPanel.add(infoPanel, BorderLayout.CENTER);                                      
        add(toolsMainPanel);
    }
}
