package gameAuthoring;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
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
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2"));
        JPanel subPanel = new JPanel(new MigLayout("wrap 2"));
        JLabel gameName = new JLabel("Game Name");
        JLabel gold = new JLabel("Starting Gold");
        JLabel lives = new JLabel("Starting Lives");
        JLabel title = new JLabel("Basic Game Info");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        mainPanel.add(title, "span 2");

        JButton setSplashImageButton = new JButton("Choose Splash Image");

        JButton setInfoButton = new JButton("Set Info");
        setInfoButton.addMouseListener(setInfoListener());

        myGameName = new JTextField();
        myGameName.setPreferredSize(new Dimension(200, 30));
        myGold = new JTextField();
        myGold.setPreferredSize(new Dimension(200, 30));
        myLives = new JTextField();
        myLives.setPreferredSize(new Dimension(200, 30));

        subPanel.add(gameName);
        subPanel.add(myGameName);
        subPanel.add(gold);
        subPanel.add(myGold);
        subPanel.add(lives);
        subPanel.add(myLives);
        subPanel.add(setSplashImageButton, "span 2");
        subPanel.add(setInfoButton);
        Border b = BorderFactory.createLoweredBevelBorder();
        subPanel.setBorder(b);
        mainPanel.add(subPanel, "align center");

        return mainPanel;
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
