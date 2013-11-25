package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;

public class WaveDesignPanel extends JPanel{
    private WaveDesignTab myWaveDesignTab;
    
    private JButton myTypeButton;
    private JTextField myNumberField;
    private JTextField myPeriodField;
    private JTextField myIntervalField;

    
    private final String DEFAULT_TYPE_TEXT = "Select an Enemy Type";

    
    public WaveDesignPanel (WaveDesignTab waveDesignTab) {

            myWaveDesignTab = waveDesignTab;
            JLabel type = new JLabel("Type of Enemy");
            type.setFont(Constants.defaultBodyFont);
            JLabel quantity= new JLabel("Quantity of Enemy");
            quantity.setFont(Constants.defaultBodyFont);
            JLabel period = new JLabel("Period");
            period.setFont(Constants.defaultBodyFont);
            JLabel interval = new JLabel("Interval");
            interval.setFont(Constants.defaultBodyFont); 
            
            myTypeButton = new JButton(DEFAULT_TYPE_TEXT);
            myTypeButton.addMouseListener(createWaveTypeListener());
            
            myNumberField = new JTextField();
            myNumberField.setPreferredSize(new Dimension(200, 30));
            myNumberField.setFont(Constants.defaultBodyFont);

            myPeriodField = new JTextField();
            myPeriodField.setPreferredSize(new Dimension(200, 30));
            myPeriodField.setFont(Constants.defaultBodyFont);

            myIntervalField = new JTextField();
            myIntervalField.setPreferredSize(new Dimension(200, 30));
            myIntervalField.setFont(Constants.defaultBodyFont);

  

            JButton createWaveButton = new JButton("Create Wave");
            createWaveButton.setFont(Constants.defaultBodyFont);
            createWaveButton.addMouseListener(createWaveButtonListener());

            this.setLayout(new MigLayout("wrap 2"));
            this.add(type);
            this.add(myTypeButton);
            this.add(quantity);
            this.add(myNumberField);
            this.add(period);
            this.add(myPeriodField);
            this.add(interval);
            this.add(myIntervalField);
            this.add(createWaveButton);
            Border b = BorderFactory.createLineBorder(Color.black, 1);
            this.setPreferredSize(new Dimension(380, 350));
            this.setBorder(b);
            this.setOpaque(false);
        }
    
  

    
        public MouseAdapter createWaveTypeListener () {

            MouseAdapter listener = new MouseAdapter() {
                GameData myGameData = myWaveDesignTab.getGameData();
                @Override
                public void mouseClicked (MouseEvent e) {  
                    JSONArray enemyList = myGameData.getEnemyList();
                    String[] enemyOptions = new String[enemyList.length()];
                    for (int i=0; i < enemyOptions.length; i++){
                       JSONObject enemy = (JSONObject) enemyList.get(i);
                       enemyOptions[i] = enemy.getString("id");
                    }
                    
                    String choice =
                            (String) JOptionPane.showInputDialog(
                                                                 null,
                                                                 "Please select an enemy type",
                                                                 "Wave enemy type chooser",
                                                                 JOptionPane.PLAIN_MESSAGE,
                                                                 null,
                                                                 enemyOptions,
                                                                 "");
                    
                    myTypeButton.setText(choice);
                   
                }
            };
            return listener;
        
        }

 

        public MouseAdapter createWaveButtonListener () {
            MouseAdapter listener = new MouseAdapter() {
                GameData myGameData = myWaveDesignTab.getGameData();
                @Override
                public void mouseClicked (MouseEvent e) {   
                    
                    String type = myTypeButton.getText();
                    int number = Integer.parseInt(myNumberField.getText());
                    double period = Double.parseDouble(myPeriodField.getText());
                    int interval = Integer.parseInt(myIntervalField.getText());
                    
                    myGameData.addWave(type, number, period, interval);
                    myWaveDesignTab.addWave(type, number);
                    
                    myTypeButton.setText(DEFAULT_TYPE_TEXT);
                    myNumberField.setText("");
                    myPeriodField.setText("");
                    myIntervalField.setText("");
                }
            };
            return listener;
        }
    

}
