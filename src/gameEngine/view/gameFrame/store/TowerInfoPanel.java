package gameEngine.view.gameFrame.store;

import java.awt.Dimension;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
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
    private JTextPane text;
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
        text = new JTextPane();
        text.setPreferredSize(new Dimension(200,200));
        text.setContentType( "text/html" ); 
        
        JScrollPane listScrollPane = new JScrollPane(text);
        listScrollPane.setSize(10, 10);
        add(listScrollPane);

    }

    /**
     * A method that is called by the Mediator to
     * update the information this panel displays
     * @param displayInformation TODO
     */
    public void displayTowerInfo (String displayInformation) {

        myListModel.clear();
        text.setText(displayInformation);
        
    }

}
