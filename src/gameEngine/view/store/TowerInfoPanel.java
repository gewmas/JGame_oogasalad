package gameEngine.view.store;

import gameEngine.model.TowerInfo;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.Border;



/**
 * Panel that displays tower information
 * when user hovers over a tower option
 * 
 * @author Lalita Maraj
 * 
 */
public class TowerInfoPanel extends Panel {


    private JList<String> myList;
    private DefaultListModel<String> myListModel;
    public TowerInfoPanel () {
        super();

        Border valuePanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.resourceBundle
                        .getString("TowerInfo"));
        setBorder(valuePanelBorder);

        initializeContents ();

    }
    
    /**
     * Initlizes JList contents and adds
     */
    private void initializeContents () {
        myListModel = new DefaultListModel<String>();
        myList = new JList<String>(myListModel);
        
        JScrollPane listScrollPane = new JScrollPane(myList);
        listScrollPane.setSize(10, 10);
        add(listScrollPane);

    }

    @Override
    /** A method that is called by the Mediator to 
     * update the information this panel displays
     */
    public void displayTowerInfo (TowerInfo tower) {
//        ImageIcon icon = new ImageIcon(tower.getImage());
        myListModel.clear();
        myListModel.addElement("Tower: "+ tower.getName());
        myListModel.addElement("Cost: "+Integer.toString(tower.getCost()));
        myListModel.addElement("Description: "+tower.getDescription());

    }

}
