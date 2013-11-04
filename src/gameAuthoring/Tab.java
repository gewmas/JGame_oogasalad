package gameAuthoring;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class Tab {

    public JTabbedPane getTab () {
        JTabbedPane tab = new JTabbedPane();
        JPanel tabPanel = new JPanel();
        tab.addTab("tab", tabPanel);
        return tab;
    }

}
