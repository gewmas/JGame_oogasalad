package gameAuthoring;

import javax.swing.JPanel;


public class BasicInfoTab extends Tab {

    private BasicInfoData myBasicInfoData;

    public BasicInfoTab (BasicInfoData basicInfoData) {
        myBasicInfoData = basicInfoData;
    }

    // TO DO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        return panel;
    }
}
