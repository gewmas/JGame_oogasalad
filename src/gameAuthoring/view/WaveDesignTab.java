package gameAuthoring.view;

import gameAuthoring.JSONObjects.WaveJSONObject;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;


public class WaveDesignTab extends Tab implements Observer {
    private JScrollPane myCreatedWaves;
    private JPanel myScrollPanel;
    private JPanel myMainPanel;
    private JPanel myContentPanel;
    private JButton myTypeButton;
    private JTextField myNumberField;
    private JTextField myPeriodField;
    private JTextField myIntervalField;
    private List<String> myEnemyList = new ArrayList<String>();
    private String[] myEnemyOptions = {};
    private static final String DEFAULT_TYPE_TEXT = "Select an Enemy Type";
    private static final Dimension DEFAULT_PANEL_DIMENSION = new Dimension(380, 350);
    private static final String SCROLL_PANEL_WRAP_MODE = "wrap 4";
    private static final String CREATED_WAVES_ALIGNMENT = "aligny center";
    private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(200, 30);

    public WaveDesignTab () {
    }

    @Override
    public JPanel getTab () {
        myMainPanel = new GradientPanel(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myMainPanel.setPreferredSize(StyleConstants.DEFAULT_PANEL_SIZE);
        myContentPanel = new JPanel();
        myContentPanel.setLayout(new MigLayout(StyleConstants.DEFAULT_WRAP_MODE));
        myContentPanel.setPreferredSize(DEFAULT_PANEL_DIMENSION);
        myContentPanel.setBorder(StyleConstants.DEFAULT_PANEL_BORDER);
        myContentPanel.setOpaque(false);
        myScrollPanel = new JPanel(new MigLayout(SCROLL_PANEL_WRAP_MODE));
        myScrollPanel.setOpaque(false);
        addTitle();
        addEnemyType();
        addEnemyAmount();
        addPeriod();
        addInterval();
        addWaveCreationButton();
        myMainPanel.add(myContentPanel);
        addCreatedWaves();
        return myMainPanel;
    }

    private void addCreatedWaves () {
        myCreatedWaves = new JScrollPane(myScrollPanel);
        myCreatedWaves.getViewport().setOpaque(false);
        myCreatedWaves.setOpaque(false);
        myCreatedWaves.setPreferredSize(DEFAULT_PANEL_DIMENSION);
        myCreatedWaves.setBorder(BorderFactory
                .createTitledBorder(StyleConstants.DEFAULT_PANEL_BORDER,
                                    StyleConstants.resourceBundle.getString("CreatedWaves"),
                                    TitledBorder.CENTER,
                                    TitledBorder.TOP,
                                    StyleConstants.TITLE_FONT_2));
        myMainPanel.add(myCreatedWaves, CREATED_WAVES_ALIGNMENT);
    }

    private void addTitle () {
        JLabel title = new JLabel(StyleConstants.resourceBundle.getString("WavesTitle"));
        title.setFont(StyleConstants.DEFAULT_TITLE_FONT);
        myMainPanel.add(title, StyleConstants.DEFAULT_SPAN_MODE);
    }

    private void addEnemyType () {
        JLabel type = new JLabel(StyleConstants.resourceBundle.getString("WavesEnemyType"));
        type.setFont(StyleConstants.DEFAULT_BODY_FONT);
        type.setToolTipText(StyleConstants.resourceBundle.getString("WavesEnemyTypeTip"));
        myTypeButton = new JButton(DEFAULT_TYPE_TEXT);
        myTypeButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myTypeButton.addMouseListener(createWaveTypeListener());
        myContentPanel.add(type);
        myContentPanel.add(myTypeButton);
    }

    private void addEnemyAmount () {
        JLabel quantity = new JLabel(StyleConstants.resourceBundle.getString("WavesEnemyQuantity"));
        quantity.setFont(StyleConstants.DEFAULT_BODY_FONT);
        quantity.setToolTipText(StyleConstants.resourceBundle.getString("WavesEnemyQuantityTip"));
        myNumberField = new JTextField();
        myNumberField.setPreferredSize(TEXT_FIELD_DIMENSION);
        myNumberField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(quantity);
        myContentPanel.add(myNumberField);
    }

    private void addPeriod () {
        JLabel period = new JLabel(StyleConstants.resourceBundle.getString("WavesPeriod"));
        period.setFont(StyleConstants.DEFAULT_BODY_FONT);
        period.setToolTipText(StyleConstants.resourceBundle.getString("WavesPeriodTip"));
        myPeriodField = new JTextField();
        myPeriodField.setPreferredSize(TEXT_FIELD_DIMENSION);
        myPeriodField.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myContentPanel.add(period);
        myContentPanel.add(myPeriodField);
    }

    private void addInterval () {
        JLabel interval = new JLabel(StyleConstants.resourceBundle.getString("WavesInterval"));
        interval.setFont(StyleConstants.DEFAULT_BODY_FONT);
        interval.setToolTipText(StyleConstants.resourceBundle.getString("WavesIntervalTip"));
        myIntervalField = new JTextField();
        myIntervalField.setPreferredSize(TEXT_FIELD_DIMENSION);
        myIntervalField.setFont(StyleConstants.DEFAULT_BODY_FONT);
    }

    private void addWaveCreationButton () {
        JButton createWaveButton =
                new JButton(StyleConstants.resourceBundle.getString("WaveCreation"));
        createWaveButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        createWaveButton.addMouseListener(createWaveButtonListener());
        createWaveButton
                .setToolTipText(StyleConstants.resourceBundle.getString("WaveCreationTip"));
        myContentPanel.add(createWaveButton);
    }

    private void addWave (String type, int number) {
        JButton waveButton = new JButton(number + " " + type);
        waveButton.setFont(StyleConstants.DEFAULT_BODY_FONT);
        myScrollPanel.add(waveButton);

    }

    private MouseAdapter createWaveTypeListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                String[] enemyOptions = new String[myEnemyList.size()];
                if (myEnemyList.size() > myEnemyOptions.length) {
                    enemyOptions = new String[myEnemyList.size()];
                    for (int i = 0; i < myEnemyList.size(); i++) {
                        enemyOptions[i] = myEnemyList.get(i);
                    }
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

    private MouseAdapter createWaveButtonListener () {
        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                String type = myTypeButton.getText();
                int number = Integer.parseInt(myNumberField.getText());
                double period = Double.parseDouble(myPeriodField.getText());
                int interval = Integer.parseInt(myIntervalField.getText());
                if (!type.equals(DEFAULT_TYPE_TEXT) && type != null && number > 0 && interval > 0 &&
                    0.0 < period && period < 1.0) {
                    WaveJSONObject wave = new WaveJSONObject(type, number, period, interval);
                    setChanged();
                    notifyObservers(wave);
                    clearChanged();
                    addWave(type, number);
                    myTypeButton.setText(DEFAULT_TYPE_TEXT);
                    myNumberField.setText("");
                    myPeriodField.setText("");
                    myIntervalField.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null,
                                                  StyleConstants.resourceBundle.getString("WaveInvalidSubmission));
                }
            }
        };
        return listener;
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
            // myGameData.addWave(type, number, period, interval);
            addWave(type, number);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update (Observable arg0, Object arg1) {
        System.out.println("WaveDesignTab received update from EnemyWaveController");
        myEnemyList = (ArrayList<String>) arg1;
    }

}
