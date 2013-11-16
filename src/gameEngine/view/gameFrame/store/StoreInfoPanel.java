package gameEngine.view.gameFrame.store;

import java.awt.Dimension;
import java.awt.Font;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import javax.swing.BorderFactory;
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
public class StoreInfoPanel extends Panel {

 
    private JTextPane text;
    public StoreInfoPanel () {
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

        text = new JTextPane();
        
        text.setPreferredSize(new Dimension(300,100));
        text.setContentType( "text/html" ); 
        
        JScrollPane listScrollPane = new JScrollPane(text);
//        listScrollPane.setSize(10, 10);
        add(listScrollPane);

    }

    /**
     * A method that is called by the Mediator to
     * update the information this panel displays
     * @param displayInformation TODO
     */
    public void displayTowerInfo (String displayInformation) {

        text.setText(displayInformation);
        
    }

}
