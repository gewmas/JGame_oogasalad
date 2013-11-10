package gameAuthoring;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class TowerDesignDialog extends JDialog {
    private TowerDesignTab myTowerDesignTab;
    
    private JLabel myName;
    private JLabel myDamage;
    private JLabel myAttackSpeed;
    private JLabel myRange;
    private JLabel myCost;
    private JLabel myRecyclePrice;

    private JTextField myNameField;
    private JTextField myDamageField;
    private JTextField myAttackSpeedField;
    private JTextField myRangeField;
    private JTextField myCostField;
    private JTextField myRecyclePriceField;

    public TowerDesignDialog (TowerDesignTab towerDesignTab) {
        myTowerDesignTab = towerDesignTab;
        myName = new JLabel("Name");
        myDamage = new JLabel("Damage");
        myAttackSpeed = new JLabel("Attack Speed");
        myRange = new JLabel("Range");
        myCost = new JLabel("Cost");
        myRecyclePrice = new JLabel("Recycle Price");

        myNameField = new JTextField();
        myDamageField = new JTextField();
        myAttackSpeedField = new JTextField();
        myRangeField = new JTextField();
        myCostField = new JTextField();
        myRecyclePriceField = new JTextField();

        JButton createTowerButton = new JButton("Create Tower");

        this.setTitle("Creating new Tower");
        this.setLayout(new GridLayout(7, 2));
        this.add(myName);
        this.add(myNameField);
        this.add(myDamage);
        this.add(myDamageField);
        this.add(myAttackSpeed);
        this.add(myAttackSpeedField);
        this.add(myRange);
        this.add(myRangeField);
        this.add(myCost);
        this.add(myCostField);
        this.add(myRecyclePrice);
        this.add(myRecyclePriceField);
        this.add(createTowerButton);

    }

    public MouseAdapter createTowerButtonListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                 
            }
        };
        return listener;
    }

}
