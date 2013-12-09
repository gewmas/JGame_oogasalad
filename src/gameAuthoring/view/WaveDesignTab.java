package gameAuthoring.view;

import gameAuthoring.JSONObjects.GameData;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


public class WaveDesignTab extends Tab {
    private JScrollPane myCreatedWaves;
    private JPanel myScrollPanel;
    private JPanel myMainPanel;
    private JPanel myContentPane;
    private JButton myTypeButton;
    private JComboBox<String> myEnemyChooser;
    private JTextField myNumberField;
    private JTextField myPeriodField;
    private JTextField myIntervalField;

    private String[] myEnemyOptions = {};

    private final String DEFAULT_TYPE_TEXT = "Select an Enemy Type";

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

    public void createMainPanel () {
        JLabel type = new JLabel("Type of Enemy");
        type.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel quantity = new JLabel("Quantity of Enemy");
        quantity.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel period = new JLabel("Period");
        period.setFont(Constants.DEFAULT_BODY_FONT);
        JLabel interval = new JLabel("Interval");
        interval.setFont(Constants.DEFAULT_BODY_FONT);

        myTypeButton = new JButton(DEFAULT_TYPE_TEXT);
        myTypeButton.setFont(Constants.DEFAULT_BODY_FONT);
        myTypeButton.addMouseListener(createWaveTypeListener());

        myEnemyChooser = new JComboBox<String>();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(myEnemyOptions);
        myEnemyChooser.setModel(model);

        myEnemyChooser.setFont(Constants.DEFAULT_BODY_FONT);
        myEnemyChooser.addMouseListener(createRevalidationListener());
        myEnemyChooser.addActionListener(createDropdownListener());

        myNumberField = new JTextField();
        myNumberField.setPreferredSize(new Dimension(200, 30));
        myNumberField.setFont(Constants.DEFAULT_BODY_FONT);

        myPeriodField = new JTextField();
        myPeriodField.setPreferredSize(new Dimension(200, 30));
        myPeriodField.setFont(Constants.DEFAULT_BODY_FONT);

        myIntervalField = new JTextField();
        myIntervalField.setPreferredSize(new Dimension(200, 30));
        myIntervalField.setFont(Constants.DEFAULT_BODY_FONT);

        JButton createWaveButton = new JButton("Create Wave");
        createWaveButton.setFont(Constants.DEFAULT_BODY_FONT);
        createWaveButton.addMouseListener(createWaveButtonListener());

        myContentPane.setLayout(new MigLayout("wrap 2"));
        myContentPane.add(type);
        myContentPane.add(myTypeButton);
        // this.add(myEnemyChooser);
        myContentPane.add(quantity);
        myContentPane.add(myNumberField);
        myContentPane.add(period);
        myContentPane.add(myPeriodField);
        myContentPane.add(interval);
        myContentPane.add(myIntervalField);
        myContentPane.add(createWaveButton);
        Border b = BorderFactory.createLineBorder(Color.black, 1);
        myContentPane.setPreferredSize(new Dimension(380, 350));
        myContentPane.setBorder(b);
        myContentPane.setOpaque(false);
    }

    public void addWave (String type, int number) {
        JButton waveButton = new JButton(number + " " + type);
        waveButton.setFont(Constants.DEFAULT_BODY_FONT);
        myScrollPanel.add(waveButton);

    }

    @Override
    public void loadJSON (Parser p) {
        JSONArray waves = p.getJSONArray("wave");

        for (int i = 0; i < waves.length(); i++) {
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
