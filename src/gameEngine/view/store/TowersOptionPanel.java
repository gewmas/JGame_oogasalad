package gameEngine.view.store;

import java.awt.Color;
import java.awt.Font;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * 
 * Acts as a container that displays the different types of user input.
 * Each element of displayed information is clickable and copy/paste enabled
 * This class gathers the information to be displayed from the Model and displays it
 * using a JList
 * 
 * @author Lalita Maraj
 * @author Susan Zhang
 * 
 */
@SuppressWarnings("serial")
public class TowersOptionPanel extends JPanel {

    private JList<String> myList;
    private DefaultListModel<String> myListModel;

    private InfoPanel infoPanel;

    // private JList sampleJList;

    protected TowersOptionPanel (String name, InfoPanel infoPanel) {

        super();

        this.infoPanel = infoPanel;

        initializeDisplay();
        myList.setVisibleRowCount(4);
        Font displayFont = new Font("Serif", Font.BOLD, 18);
        myList.setFont(displayFont);

        JScrollPane listPane = new JScrollPane(myList);

        setBackground(Color.white);
        Border listPanelBorder =
                BorderFactory.createTitledBorder("Store");
        setBorder(listPanelBorder);
        add(listPane);

    }

    /**
     * Initializes settings relevant to display
     */
    private void initializeDisplay () {
        setBackground(Color.white);
        // setName();
        initializeContents();
        // setPreferredSize(new Dimension(100,
        // 150));
    }

    /**
     * Updates the content of JList options
     */
    protected void updateContent (Collection<String> listData) {
        myListModel.clear();

        for (String moduleData : listData) {
            myListModel.addElement(moduleData);

        }
    }

    /**
     * Initlizes JList contents and adds
     */
    private void initializeContents () {
        myListModel = new DefaultListModel<String>();
        myList = new JList<String>(myListModel);
        myListModel.addElement("test like omg!");
        myListModel.addElement("test like omg!");
        myListModel.addElement("test like omg!");
        myListModel.addElement("test like omg!");
        myListModel.addElement("test like omg!");
        myListModel.addElement("test like omg!");
        addSelectionListener(myList);
        JScrollPane listScrollPane = new JScrollPane(myList);
        add(listScrollPane);

    }

    /**
     * Adds a selection listener to a JList
     * 
     * @param list Jlist selection listener needs to be added to
     */
    private void addSelectionListener (final JList<String> list) {

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && list.getSelectedValue() != null) {

                    String selected = list.getSelectedValue();
                    infoPanel.update(selected);

                }

            }

        });
    }

}
