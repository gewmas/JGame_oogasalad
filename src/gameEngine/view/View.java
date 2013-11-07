package gameEngine.view;

import java.awt.BorderLayout;
import java.util.ResourceBundle;
import javax.swing.JFrame;
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
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvasPanel = new CanvasPanel();
        this.add(canvasPanel,BorderLayout.WEST);
        storePanel = new TowerStorePanel();
        this.add(storePanel,BorderLayout.EAST);
//        new InitializationFrame(controller, myResources);
        showGame ();
    }

    public void showGame () {
        pack();
        setVisible(true);

    }

}
