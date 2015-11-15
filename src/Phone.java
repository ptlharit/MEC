import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

public class Phone extends JFrame {

    private Car car;
    private Radio radio;
    private Heating heater;
    private CarGPS navigation;
    private Defogger defogger;

    private JPanel window = new JPanel();
    private JLabel emptyLabel = new JLabel("");

    private JRadioButton rON;
    private JRadioButton rOFF;
    private ButtonGroup radioButtons;
    private JSpinner spinner;

    private JButton decHEAT;
    private JLabel heatLevelLabel;
    private JButton incHEAT;

    private JLabel lonLabel;
    private JLabel latLabel;
    private JTextField lon;
    private JTextField lat;
    private JButton setAsDest;

    private JRadioButton dON;
    private JRadioButton dOFF;
    private ButtonGroup defoggerButtons;

    private JButton radioButton;
    private JButton heatingButton;
    private JButton navigationButton;
    private JButton defogButton;
    private JButton updateButton;
    private JButton systemCheckButton;
    private JButton resetButton;

    private JButton backButton;

    public Phone(Car car) {
        this.car = car;

        setTitle("McMaster Engineering Competition | 2015 | Programming | Phone");
        setSize(400, 600);

        // HOME SCREEN
        radioButton = new JButton("Radio");
        heatingButton = new JButton("Heating");
        navigationButton = new JButton("Navigation");
        defogButton = new JButton("Defogger");
        updateButton = new JButton("UPDATE");
        systemCheckButton = new JButton("SYSTEM CHECK");
        resetButton = new JButton("RESET SETTINGS");

        radioButton.addActionListener(e -> { radioSettings(); });
        heatingButton.addActionListener(e -> { heatingSettings(); });
        navigationButton.addActionListener(e -> { navigationSettings(); });
        defogButton.addActionListener(e -> { defoggerSettings(); });
        updateButton.addActionListener(e -> {  });
        systemCheckButton.addActionListener(e -> { systemCheckSettings(); });
        resetButton.addActionListener(e -> { car.reset(this); });

        // RADIO SCREEN
        radio = new Radio();
        rON = new JRadioButton("ON");
        rOFF = new JRadioButton("OFF");

        rON.addActionListener(e -> { spinner.setEnabled(true); radio.TurnON(); car.update(this); });
        rOFF.addActionListener(e -> { spinner.setEnabled(false); radio.TurnOFF(); car.update(this); });

        radioButtons = new ButtonGroup();
        radioButtons.add(rON);
        radioButtons.add(rOFF);

        // HEATING SCREEN
        heater = new Heating();
        decHEAT = new JButton("-");
        heatLevelLabel = new JLabel(String.valueOf(heater.getHeatingLevel()));
        incHEAT = new JButton("+");

        decHEAT.addActionListener(e -> {
            heater.decreaseHeat();
            car.update(this);
            if (heater.getHeatingLevel() == 0) { decHEAT.setEnabled(false); }
            else decHEAT.setEnabled(true);
            heatLevelLabel = new JLabel(String.valueOf(heater.getHeatingLevel()));
            heatingSettings();
        });
        incHEAT.addActionListener(e -> {
            heater.increaseHeat();
            car.update(this);
            if (heater.getHeatingLevel() == 5) { incHEAT.setEnabled(false); }
            else incHEAT.setEnabled(true);
            heatLevelLabel = new JLabel(String.valueOf(heater.getHeatingLevel()));
            heatingSettings();
        });

        // NAVIGATION SCREEN
        navigation = new CarGPS();
        lonLabel = new JLabel("Longitude: ");
        latLabel = new JLabel("Latitude: ");

        lon = new JTextField();
        lat = new JTextField();
        setAsDest = new JButton("Set As Destination");

        setAsDest.addActionListener(e -> {
            navigation.setLoc(lon.getText(), lat.getText());
            car.update(this);
        });

        // DEFOGGER SCREEN
        defogger = new Defogger();
        dON = new JRadioButton("ON");
        dOFF = new JRadioButton("OFF");

        dON.addActionListener(e -> { defogger.TurnON(); car.update(this); });
        dOFF.addActionListener(e -> { defogger.TurnOFF(); car.update(this); });

        defoggerButtons = new ButtonGroup();
        defoggerButtons.add(dON);
        defoggerButtons.add(dOFF);

        home();

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public String getRadioMode() { return radio.getCurrent_radio_mode(); }
    public String getRadioStation() { return String.valueOf(radio.getCurrent_radio_frequency()); }
    public int getHeatLevel() { return heater.getHeatingLevel(); }
    public String getLon() { return String.valueOf(lon.getText()); }
    public String getLat() { return String.valueOf(lat.getText()); }
    public String getDefoggerMode() { return defogger.getCurrent_defogger_mode(); }

    public void reset() {
        radio.TurnOFF();
        heater.setHeat(0);
        navigation.setLoc("0", "0");
        defogger.TurnOFF();
    }

    private void home() {
        window.removeAll();
        window = new JPanel(new GridLayout(4, 2, 10, 10));

        window.add(radioButton);
        window.add(heatingButton);
        window.add(navigationButton);
        window.add(defogButton);
        window.add(updateButton);
        updateButton.setBackground(Color.GRAY.brighter());
        window.add(systemCheckButton);
        systemCheckButton.setBackground(Color.GRAY.brighter());
        window.add(emptyLabel);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.RED);
        window.add(resetButton);

        add(window);
        window.updateUI();
    }

    private void radioSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel radioModeLabel = new JLabel("RADIO: ");

        if (rON.isSelected()) {
            radio.TurnON();
            rON.setSelected(true);
        } else {
            radio.TurnOFF();
            rOFF.setSelected(true);
        }

        JLabel radioStationLabel = new JLabel("STATION: ");

        spinner = new JSpinner(new SpinnerNumberModel(1.50, 0.00, 9.99, 0.05));
        radio.setCurrent_radio_frequency(1.50);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spinner.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(3);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);

        if (rOFF.isSelected()) { spinner.setEnabled(false); }
        if (rON.isSelected()) { spinner.setEnabled(true); }

        spinner.addChangeListener(e -> {
            radio.setCurrent_radio_frequency((double) spinner.getValue());
            car.update(this);
        });

        window.add(radioModeLabel);
        window.add(rON);
        window.add(rOFF);

        window.add(radioStationLabel);
        window.add(spinner);
        window.add(emptyLabel);

        createBackButton();

        add(window);
        window.updateUI();
    }
    
    private void heatingSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(2, 3, 10, 10));

        if (heater.getHeatingLevel() == 0) { decHEAT.setEnabled(false); }
        else if (heater.getHeatingLevel() == 5) { incHEAT.setEnabled(false); }
        else { decHEAT.setEnabled(true); incHEAT.setEnabled(true); }

        window.add(decHEAT);
        window.add(heatLevelLabel);
        window.add(incHEAT);

        createBackButton();
        window.add(emptyLabel);
        window.add(emptyLabel);

        add(window);
        window.updateUI();
    }
    
    private void navigationSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(4, 2, 10, 10));

        window.add(lonLabel);
        window.add(lon);
        window.add(latLabel);
        window.add(lat);
        window.add(setAsDest);
        setAsDest.setAlignmentX(Component.CENTER_ALIGNMENT);
        window.add(emptyLabel);

        createBackButton();
        window.add(emptyLabel);

        add(window);
        window.updateUI();
    }
    
    private void defoggerSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(2, 3, 10, 10));

        JLabel defoggerModeLabel = new JLabel("DEFOGGER: ");

        if (dON.isSelected()) {
            defogger.TurnON();
            dON.setSelected(true);
        } else {
            defogger.TurnOFF();
            dOFF.setSelected(true);
        }

        window.add(defoggerModeLabel);
        window.add(dON);
        window.add(dOFF);

        window.add(emptyLabel);
        createBackButton();
        window.add(emptyLabel);

        add(window);
        window.updateUI();
    }

    private void systemCheckSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(2, 3, 10, 10));

        createBackButton();

        add(window);
        window.updateUI();
    }

    private void createBackButton() {
        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> { home(); });
        window.add(backButton);
    }
}
