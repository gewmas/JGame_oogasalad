package gameEngine.view.gameFrame.store;


import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.Border;


/**
 * Panel that displays tower informations
 * when user hovers over a tower option
 * 
 * @author Lalita Maraj
 * 
 */
public class TowerInfoPanel extends Panel {

    private JList myList;
    private DefaultListModel myListModel;

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
        myListModel = new DefaultListModel();
        myList = new JList(myListModel);

        JScrollPane listScrollPane = new JScrollPane(myList);
        listScrollPane.setSize(10, 10);
        add(listScrollPane);

    }


    /** A method that is called by the Mediator to 
     * update the information this panel displays
     */
    public void displayTowerInfo (TowerFactory tower) {
        // ImageIcon icon = new ImageIcon(tower.getImage());
        myListModel.clear();
        myListModel.addElement(TowerInfoFields.NAME + tower.getName());
        myListModel.addElement(TowerInfoFields.COST + Integer.toString(tower.getCost()));
        myListModel.addElement(TowerInfoFields.DESCRIPTION + tower.getDescription());

    }

}
