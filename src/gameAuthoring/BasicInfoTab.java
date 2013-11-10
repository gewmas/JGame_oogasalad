package gameAuthoring;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


public class BasicInfoTab extends Tab {

    private JTextField myGameName;
    private JTextField myGold;
    private JTextField myLives;

    public BasicInfoTab (GameData gameData) {
        super(gameData);
        getTab();
    }

    // TO DO: Get rid of magic number
    @Override
    public JPanel getTab () {
        JPanel panel = new JPanel();
        JLabel gameName = new JLabel("Game Name");
        JLabel gold = new JLabel("Starting Gold");
        JLabel lives = new JLabel("Starting Lives");
        
        JButton setSplashImageButton = new JButton("Choose Splash Image");

        JButton setInfoButton = new JButton("Set Info");
        setInfoButton.addMouseListener(setInfoListener());

        myGameName = new JTextField();
        myGameName.setPreferredSize(new Dimension(200, 30));
        myGold = new JTextField();
        myGold.setPreferredSize(new Dimension(200, 30));
        myLives = new JTextField();
        myLives.setPreferredSize(new Dimension(200, 30));

        panel.setLayout(new MigLayout("wrap 2, align center"));
        panel.add(gameName);
        panel.add(myGameName);
        panel.add(gold);
        panel.add(myGold);
        panel.add(lives);
        panel.add(myLives);
        panel.add(setSplashImageButton);
        panel.add(setInfoButton);

        return panel;
    }

    public MouseAdapter setInfoListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                myGameData.setGameName(myGameName.getText());
                myGameData.setGold(Integer.parseInt(myGold.getText()));
                myGameData.setLives(Integer.parseInt(myLives.getText()));
            }
        };
        return listener;

    }
}
