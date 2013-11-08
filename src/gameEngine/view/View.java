package gameEngine.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.UIManager;
import gameEngine.view.initialization.InitializationFrame;
import gameEngine.view.store.TowerStorePanel;
import gameEngine.controller.Controller;


public class View extends Frame {
    Panel canvasPanel;
    Panel statsPanel;
    Panel storePanel;
    private Controller controller;
    private Mediator mediator;

    public View (Controller controller) {
        super();
        setUIStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mediator = new Mediator();
        this.controller = controller;

        canvasPanel = new CanvasPanel();
        this.add(canvasPanel, BorderLayout.WEST);
        mediator.addColleague(MediatorConstants.GAME_KEY, canvasPanel);

        // new InitializationFrame(controller, StyleConstants.myResources);
        showGame();
    }

    /**
     * Setting the Look and Feel of the UI
     */
    private void setUIStyle () {
        Font f = new Font(StyleConstants.BUTTON_FONT, StyleConstants.BUTTON_FONT_STYLE,
                          StyleConstants.BUTTON_FONT_SIZE);
        UIManager.put(StyleConstants.BUTTON_FONT_KEY, f);
    }

    public void showGame () {
        createStore();
        pack();
        setVisible(true);

    }

    /**
     * Create the store of Towers
     */
    private void createStore () {
        storePanel = new TowerStorePanel(mediator, controller);
        this.add(storePanel, BorderLayout.EAST);
    }

}
