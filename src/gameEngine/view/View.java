package gameEngine.view;

import java.awt.BorderLayout;
import java.util.ResourceBundle;
import gameEngine.view.initialization.InitializationFrame;
import gameEngine.view.store.TowerStorePanel;
import gameEngine.controller.Controller;


public class View extends Frame {
    Panel canvasPanel;
    Panel statsPanel;
    Panel storePanel;
    private static final String DEFAULT_RESOURCE_PACKAGE = "resources.";
    private static final String USER_DIR = "user.dir";
    private ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE +
                                                                  "Labels");

    public View (Controller controller) {
        super();

        canvasPanel = new CanvasPanel();
        this.add(canvasPanel,BorderLayout.WEST);
        storePanel = new TowerStorePanel();
        this.add(storePanel,BorderLayout.EAST);
        new InitializationFrame(controller, myResources);
        
    }

    public void showGame () {
        pack();
        setVisible(true);

    }

}
