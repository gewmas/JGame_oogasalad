package gameAuthoring;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


public class EnemyDesignTab extends Tab {

    private JScrollPane myCreatedEnemies;
    private JPanel myScrollPanel;

    public EnemyDesignTab (GameData gameData) {
        super(gameData);
    }

    @Override
    public JPanel getTab () {
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2")) {
            @Override
            protected void paintComponent (Graphics grphcs) {
                super.paintComponent(grphcs);
                grphcs.setColor(Color.red);
                Graphics2D g2d = (Graphics2D) grphcs;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                     RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp =
                        new GradientPaint(0, 0,
                                          getBackground().brighter().brighter(), 0, getHeight(),
                                          getBackground().darker().darker().darker());

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(grphcs);
            }
        };
        mainPanel.setPreferredSize(new Dimension(500, 500));
        JLabel title = new JLabel("Enemy Design");
        title.setFont(new Font("Calibri", Font.PLAIN, 30));
        title.setForeground(new Color(80, 80, 80));
        mainPanel.add(title, "span 2");
        myScrollPanel = new JPanel(new MigLayout("wrap 4"));
        myScrollPanel.setOpaque(false);
        EnemyDesignPanel enemyDesignPanel = new EnemyDesignPanel(this);
        mainPanel.add(enemyDesignPanel, "span 2");
        myCreatedEnemies = new JScrollPane(myScrollPanel);
        myCreatedEnemies.getViewport().setOpaque(false);
        myCreatedEnemies.setOpaque(false);
        myCreatedEnemies.setPreferredSize(new Dimension(380, 350));
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myCreatedEnemies.setBorder(BorderFactory
                .createTitledBorder(b, "Created Enemies",
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    new Font("Calibri", Font.PLAIN, 20)));
        mainPanel.add(myCreatedEnemies, "aligny center");
        mainPanel.setOpaque(false);
        return mainPanel;
    }

    public void addEnemy (File imgSource, String enemyName) {
        JLabel enemyIcon = new JLabel();
        try {
            Image enemyImage = ImageIO.read(imgSource);
            enemyIcon.setIcon(new ImageIcon(enemyImage));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        JLabel enemyNameLabel = new JLabel(enemyName);
        myScrollPanel.add(enemyNameLabel);
        myScrollPanel.add(enemyIcon);
    }

    @Override
    public void loadJSON (Parser p) {
        // TODO Auto-generated method stub

    }

}
