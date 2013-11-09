package gameEngine.view.store;


import gameEngine.model.TowerInfo;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.border.Border;


public class TowerInfoPanel extends Panel {


    private JLabel label;

    public TowerInfoPanel () {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.myResources.getString("TowerInfo"));
        setBorder(valuePanelBorder);
        label = new JLabel();    
        add(label);

    }

    @Override
    public void displayTowerInfo (TowerInfo tower){
        ImageIcon icon = new ImageIcon(tower.getImage()); 

        label.setIcon(icon);
        String initialText = "<html><h1>" +
                tower.getName() + "</h1><ul>" +

                "<li><font color=red>Cost: </font>" + tower.getCost() + "</li>" +
                "<li><font color=blue>Description: </font>" + tower.getDescription() + "</li></html>";
        label.setText(initialText);
        
    }

}
