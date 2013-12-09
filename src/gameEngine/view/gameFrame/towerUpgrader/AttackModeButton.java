package gameEngine.view.gameFrame.towerUpgrader;

import gameEngine.controller.ControllerToViewInterface;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;


public class AttackModeButton extends TowerUpgraderButton {
    private static final String NAME = "Change Attack Mode";
    private static final String DIALOG="Please select an attack mode:";
    private static final String[] ATTACK_MODES={"1: Closest", "2: Farthest", "3: Least Life", "4: Most Life"};
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 20;

    private ControllerToViewInterface controller;
    private int towerX, towerY;
    private ItemOptionsDisplayer towerUpgrader;
    private JList<String> myAttackModeList;
    private DefaultListModel<String> myAttackModeListModel;
    private int numAttackModes=4;    

    public AttackModeButton (ItemOptionsDisplayer utility, ControllerToViewInterface controller_in) {
        super(NAME);
        this.controller = controller_in;
        this.towerUpgrader = utility;
        myAttackModeListModel=new DefaultListModel<String>();
        myAttackModeList=new JList<String>(myAttackModeListModel);
        for (int i=0;i<numAttackModes;i++){
            myAttackModeListModel.addElement(ATTACK_MODES[i]);
        }

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                buttonPushed();
                int selectedItem=myAttackModeList.getSelectedIndex();
                controller.setTowerAttackMode(towerX, towerY, selectedItem);
            }
        });
        this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.setVisible(false);
        towerX = -1;
        towerY = -1;
    }

    public void buttonPushed(){
        JOptionPane.showMessageDialog(this, myAttackModeList, DIALOG, JOptionPane.PLAIN_MESSAGE);
    }

    public void setTowerPosition (Map<String, String> information, int mouseX, int mouseY) {
        towerX = mouseX;
        towerY = mouseY;
    }

}
