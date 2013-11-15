package gameEngine.view.gameFrame.store;

import java.awt.Dimension;
import java.awt.Label;
import gameEngine.model.tower.TowerInfo;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;


/**
 * Panel that displays tower informations
 * when user hovers over a tower option
 * 
 * @author Lalita Maraj
 * 
 */
public class TowerInfoPanel extends Panel {

    private JList myInfoList;
    private StoreListModel myInfoListModel;
    
    private JList myUpgradeList;
    private StoreListModel myUpgradeListModel;

    public TowerInfoPanel () {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.resourceBundle
                        .getString("TowerInfo"));
        setBorder(valuePanelBorder);

        initializeContents();

    }

    /**
     * Initlizes JList contents and adds
     */
    private void initializeContents () {

        myInfoListModel = new StoreListModel();
        myInfoList = new JList(myInfoListModel);
        myInfoList.setVisibleRowCount(15);
        JScrollPane listScrollPane = new JScrollPane(myInfoList);   
        add(listScrollPane);
    
        


        
    }

    /**
     * A method that is called by the Mediator to
     * update the information this panel displays
     */
    public void displayTowerInfo (TowerInfo tower) {
    
        myInfoListModel.clear();
    
        myInfoListModel.addItem((TowerInfoFields.NAME.addValue(tower.getTowerName())), tower.getTowerName());
        myInfoListModel.addItem(TowerInfoFields.COST.addValue(tower.getCost() ),tower.getCost());
        myInfoListModel.addItem(TowerInfoFields.DESCRIPTION + tower.getDescription(), tower.getDescription());
        
        myInfoListModel.addItem(TowerInfoFields.ATTACKSPEED.addValue(tower.getAttackSpeed()),tower.getAttackSpeed() );
        myInfoListModel.addItem(TowerInfoFields.ATTACKMODE.addValue(tower.getAttackMode()), tower.getAttackMode());
        myInfoListModel.addItem(TowerInfoFields.DAMAGE.addValue(tower.getDamage()) ,tower.getDamage());

        
        myInfoListModel.addItem(TowerInfoFields.RANGE.addValue(tower.getRange()),tower.getRange() );//.getName());
        myInfoListModel.addItem(TowerInfoFields.RECYCLE.addValue(tower.getRecyclePrice()) ,tower.getRecyclePrice());
     

    }

}
