package gameAuthoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

public class WaveDesignTab extends Tab{
    private JScrollPane myCreatedWaves;
    private JPanel myScrollPanel;
    private JPanel myMainPanel;


    public WaveDesignTab (GameData gameData) {
        super(gameData);
    }
    
    @Override
    public JPanel getTab () {
        myMainPanel = new GradientPanel(new MigLayout("wrap 2"));
        myMainPanel.setPreferredSize(new Dimension(500, 500));
        JLabel title = new JLabel("Wave Design");
        title.setFont(new Font("Calibri", Font.PLAIN, 30));
        title.setForeground(new Color(80, 80, 80));
        myMainPanel.add(title, "span 2");
        myScrollPanel = new JPanel(new MigLayout("wrap 4"));
        myScrollPanel.setOpaque(false);
        WaveDesignPanel waveDesignPanel = new WaveDesignPanel(this);
        myMainPanel.add(waveDesignPanel);
        myCreatedWaves = new JScrollPane(myScrollPanel);
        myCreatedWaves.getViewport().setOpaque(false);
        myCreatedWaves.setOpaque(false);
        myCreatedWaves.setPreferredSize(new Dimension(380, 350));
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myCreatedWaves.setBorder(BorderFactory
                .createTitledBorder(b, "Created Waves",
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    new Font("Calibri", Font.PLAIN, 20)));
        myMainPanel.add(myCreatedWaves, "aligny center");
        return myMainPanel;
    }

    public void addWave (String type, int number) {
        JButton waveButton = new JButton(number + " " + type);
        waveButton.setFont(Constants.DEFAULT_BODY_FONT);
        myScrollPanel.add(waveButton);

    }


    @Override
    public void loadJSON (Parser p) {
        JSONArray waves = p.getJSONArray("wave");
        
        for (int i = 0; i < waves.length(); i++){
            JSONObject wave = (JSONObject) waves.get(i);
            
            String type = (String) wave.get("type");
            int number = (int) wave.get("number");
            double period = (double) wave.get("period");
            int interval = (int) wave.get("interval");
            
            myGameData.addWave(type, number, period, interval);
            
            addWave(type, number);
        }
    }

}
