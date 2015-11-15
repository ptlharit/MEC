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
    
    private SystemCheck systemCheck;
    private FuelMonitor fuelCheck;

    private JButton radioButton;
    private JButton heatingButton;
    private JButton navigationButton;
    private JButton defogButton;
    private JButton updateButton;
    private JButton fuelCheckButton;
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
        fuelCheckButton = new JButton("FUEL CHECK");
        systemCheckButton = new JButton("SYSTEM CHECK");
        resetButton = new JButton("RESET SETTINGS");

        radioButton.addActionListener(e -> { radioSettings(); });
        heatingButton.addActionListener(e -> { heatingSettings(); });
        navigationButton.addActionListener(e -> { navigationSettings(); });
        defogButton.addActionListener(e -> { defoggerSettings(); });
        updateButton.addActionListener(e -> {  });
        systemCheckButton.addActionListener(e -> { systemCheckSettings(); });
        fuelCheckButton.addActionListener(e -> { fuelCheckSettings(); });
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
        
        // SYSTEM CHECK SCREEN
        double[] tires = {32.0, 29.0, 10.0, 57.7};
        systemCheck = new SystemCheck("MEC Car", tires, 1000);
        
        // FUEL CHECK SCREEN
        fuelCheck = new FuelMonitor(700);

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
        navigation.setLoc("0", "0"); lon.setText("0"); lat.setText("0");
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
        window.add(fuelCheckButton);
        fuelCheckButton.setBackground(Color.GRAY.brighter());
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

        heatLevelLabel = new JLabel(String.valueOf(heater.getHeatingLevel()));

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

        lon.setText(lon.getText());
        lat.setText(lat.getText());
        
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
        window = new JPanel(new GridLayout(10, 2, 10, 10));

        JLabel oilLevel = new JLabel("Oil Level: ");
        JLabel oilCheckResult = new JLabel((systemCheck.Oil_Change_Result) ? "GOOD":"LOW");
        JLabel nextChange = new JLabel("Recommended Oil Change: ");
        JLabel oilChangeDist = new JLabel(systemCheck.getKMToNextOilChange() + " KM's");
        
        JLabel tireCheck = new JLabel("Tire Check: ");
        JLabel tFR = new JLabel("Front-Right Tire..... ");
        JLabel tFL = new JLabel("Front-Left Tire..... ");
        JLabel tRR = new JLabel("Rear-Right Tire..... ");
        JLabel tRL = new JLabel("Rear-Left Tire..... ");

        Boolean[] results = systemCheck.Tire_Pressure_Check();
        JLabel tFRresult = new JLabel(results[0] ? "GOOD":"LOW");
        JLabel tFLresult = new JLabel(results[1] ? "GOOD":"LOW");
        JLabel tRRresult = new JLabel(results[2] ? "GOOD":"LOW");
        JLabel tRLresult = new JLabel(results[3] ? "GOOD":"LOW");

        window.add(emptyLabel); window.add(emptyLabel);
        window.add(oilLevel);
        window.add(oilCheckResult);
        window.add(nextChange);
        window.add(oilChangeDist);
        window.add(tireCheck);
        window.add(emptyLabel);
        window.add(tFR); window.add(tFRresult);
        window.add(tFL); window.add(tFLresult);
        window.add(tRR); window.add(tRRresult);
        window.add(tRL); window.add(tRLresult);
        
        createBackButton();

        add(window);
        window.updateUI();
    }

    private void fuelCheckSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(3, 2, 10, 10));
        
        JLabel currentFuelLabel = new JLabel("Current Fuel: ");
        JLabel currentFuel = new JLabel(String.valueOf(fuelCheck.getCurrentFuel()));
        
        JLabel fuelStatusLabel = new JLabel("Current Fuel: ");
        JLabel fuelStatus = new JLabel(String.valueOf(fuelCheck.getStatus()));

        window.add(currentFuelLabel);
        window.add(currentFuel);
        window.add(fuelStatusLabel);
        window.add(fuelStatus);
        
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
