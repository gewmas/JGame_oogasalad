package gameEngine.view.gameFrame.towerUpdrader;

import gameEngine.constant.GameEngineConstant;
import gameEngine.view.View;
import gameEngine.view.gameFrame.tools.DisplayValue;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class UpgradeButton extends TowerUpgraderButton {
    private static final String NAME = "Upgrade";
    private static final String NORMAL_KEY = "black";
    private static final String UPGRADE_KEY = "red";
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 20;
    
    private static final String[] DISPLAY_NORMAL_COLORS = { NORMAL_KEY, NORMAL_KEY, NORMAL_KEY,
                                                           NORMAL_KEY, NORMAL_KEY,
                                                           NORMAL_KEY, NORMAL_KEY, NORMAL_KEY,
                                                           NORMAL_KEY, NORMAL_KEY, NORMAL_KEY };

    private static final String[] DISPLAY_UPGRADED_COLORS = { NORMAL_KEY, UPGRADE_KEY, UPGRADE_KEY,
                                                             NORMAL_KEY, NORMAL_KEY, NORMAL_KEY,
                                                             UPGRADE_KEY, UPGRADE_KEY, NORMAL_KEY,
                                                             NORMAL_KEY, NORMAL_KEY };
    private ItemOptionsDisplayer towerUpgrader;
    private Map<String, String> upgradedValuesToDisplay;
    private Map<String, String> normalValuesToDisplay;
    private View view;
    private int towerX, towerY;
    private Map<String, String> information;
    private boolean isActive;

    public UpgradeButton (ItemOptionsDisplayer utility, View viewer) {
        super(NAME);
        this.towerUpgrader = utility;
        this.view = viewer;
        normalValuesToDisplay = new LinkedHashMap<String, String>();
        upgradedValuesToDisplay = new LinkedHashMap<String, String>();
        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered (MouseEvent e) {
            	updateValuesToDisplay();
                updateDisplayInformation(upgradedValuesToDisplay);

            }

            @Override
            public void mouseExited (MouseEvent e) {
            	updateValuesToDisplay();
                updateDisplayInformation(normalValuesToDisplay);

            }

            @Override
            public void mouseClicked (MouseEvent e) {
            	updateValuesToDisplay();
                if (isActive) {
                    view.upgradeTower(towerX, towerY);

                    updateDisplayInformation(upgradedValuesToDisplay);
                }
                checkActive();
            }

        });
        this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.setVisible(false);
        towerX = -1;
        towerY = -1;
    }

    protected void updateValuesToDisplay() {
    	for (int i = 0; i < GameEngineConstant.UPGRADE_DISPLAY_KEYS().length; i++) {
            normalValuesToDisplay.put(GameEngineConstant.NORMAL_DISPLAY_KEYS()[i], DISPLAY_NORMAL_COLORS[i]);
        }
        for (int i = 0; i < GameEngineConstant.UPGRADE_DISPLAY_KEYS().length; i++) {
            upgradedValuesToDisplay.put(GameEngineConstant.UPGRADE_DISPLAY_KEYS()[i], DISPLAY_UPGRADED_COLORS[i]);
        }
	}

	public void checkActive () {

        int money = view.getGameInfo().getGold();
        isActive =
                money >= Double
                        .parseDouble(information.get(GameEngineConstant.TOWER_UPGRADE_PRICE));

        setEnabled(isActive);
    }

    public void setTowerPosition (Map<String, String> information, int mouseX, int mouseY) {
        towerX = mouseX;
        towerY = mouseY;
        this.information = information;
        checkActive();
    }

    private void updateDisplayInformation (Map<String, String> valuesToDisplay) {
        List<DisplayValue> display = new ArrayList();

        for (String key : valuesToDisplay.keySet()) {
            if (information.get(key) != null) {
                String field = key;
                String value = information.get(key);
                String color = valuesToDisplay.get(key);

                display.add(new DisplayValue(field, value, color));
            }
        }
        towerUpgrader.updateDisplay(display);
    }
}
