package gameAuthoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;

public class TempBarrierDesignTab extends Tab{
    private JScrollPane myCreatedTempBarriers;
    private JPanel myScrollPanel;
    private JPanel myMainPanel;
    
    public TempBarrierDesignTab (GameData gameData) {
        super(gameData);
    }
    
    @Override
    public JPanel getTab(){
        myMainPanel = new GradientPanel(new MigLayout("wrap 2"));
        myMainPanel.setPreferredSize(new Dimension(500, 500));
        JLabel title = new JLabel("Temporary Barrier Design");
        title.setFont(new Font("Calibri", Font.PLAIN, 30));
        title.setForeground(new Color(80, 80, 80));
        myMainPanel.add(title, "span 2");
        myScrollPanel = new JPanel(new MigLayout("wrap 4"));
        myScrollPanel.setOpaque(false);
        TempBarrierDesignPanel tempBarrierDesignPanel = new TempBarrierDesignPanel(this);
        myMainPanel.add(tempBarrierDesignPanel);
        myCreatedTempBarriers = new JScrollPane(myScrollPanel);
        myCreatedTempBarriers.getViewport().setOpaque(false);
        myCreatedTempBarriers.setOpaque(false);
        myCreatedTempBarriers.setPreferredSize(new Dimension(380, 350));
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myCreatedTempBarriers.setBorder(BorderFactory
                .createTitledBorder(b, "Created Waves",
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    new Font("Calibri", Font.PLAIN, 20)));
        myMainPanel.add(myCreatedTempBarriers, "aligny center");
        return myMainPanel;
    }
    

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub
        
    }

}
