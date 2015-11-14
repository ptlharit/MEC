import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Hashtable;

public class Phone extends JFrame {

    private Car car;
    private Radio radio;

    private JPanel window = new JPanel();

    private JRadioButton on;
    private JRadioButton off;
    private ButtonGroup radioButtons;

    private JButton radioButton;
    private JButton heatingButton;
    private JButton navigationButton;
    private JButton defrostButton;

    private JButton backButton;

    public Phone(Car car) {
        this.car = car;

        setTitle("McMaster Engineering Competition | 2015 | Programming | Phone");
        setSize(400, 600);

        // HOME SCREEN
        radioButton = new JButton("Radio");
        heatingButton = new JButton("Heating");
        navigationButton = new JButton("Navigation");
        defrostButton = new JButton("Defrost");

        radioButton.addActionListener(e -> { radioSettings(); });
        heatingButton.addActionListener(e -> { heatingSettings(); });
        navigationButton.addActionListener(e -> { navigationSettings(); });
        defrostButton.addActionListener(e -> { defrostSettings(); });

        // RADIO SCREEN
        radio = new Radio();
        on = new JRadioButton("ON");
        off = new JRadioButton("OFF");

        on.addActionListener(e -> { radio.TurnON(); car.update(this); });
        off.addActionListener(e -> { radio.TurnOFF(); car.update(this); });

        radioButtons = new ButtonGroup();
        radioButtons.add(on);
        radioButtons.add(off);

        home();

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public String getRadioStatus() { return radio.getCurrent_radio_mode(); }

    private void home() {
        window.removeAll();
        window = new JPanel(new GridLayout(2, 2, 10, 10));

        window.add(radioButton);
        window.add(heatingButton);
        window.add(navigationButton);
        window.add(defrostButton);

        add(window);
        window.updateUI();
    }

    private void radioSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel radioModeLabel = new JLabel("RADIO: ");

        if (on.isSelected()) {
            radio.TurnON();
            on.setSelected(true);
        } else {
            radio.TurnOFF();
            off.setSelected(true);
        }

        JLabel radioStationLabel = new JLabel("STATION: ");
        JLabel emptyLabel = new JLabel("");


        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1.50, 0.00, 9.99, 0.05));
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor)spinner.getEditor();
        DecimalFormat format = editor.getFormat();
        format.setMinimumFractionDigits(3);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);

        spinner.addChangeListener(e -> { car.update(this); });

        window.add(radioModeLabel);
        window.add(on);
        window.add(off);

        window.add(radioStationLabel);
        window.add(spinner);
        window.add(emptyLabel);

        createBackButton();

        add(window);
        window.updateUI();
    }
    
    private void heatingSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(4, 1, 10, 10));

        JSlider level = new JSlider(JSlider.VERTICAL, 0, 5, 0);
        level.setMinorTickSpacing(1);
        level.setPaintTicks(true);
        level.setSnapToTicks(true);

        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(0), new JLabel("0"));
        labelTable.put(new Integer(1), new JLabel("1"));
        labelTable.put(new Integer(2), new JLabel("2"));
        labelTable.put(new Integer(3), new JLabel("3"));
        labelTable.put(new Integer(4), new JLabel("4"));
        labelTable.put(new Integer(5), new JLabel("5"));
        level.setLabelTable(labelTable);

        level.setPaintLabels(true);
        window.add(level);

        window.add(new JLabel("1"));
        window.add(new JLabel("2"));
        window.add(new JLabel("3"));
        createBackButton();
        add(window);
        window.updateUI();
    }
    
    private void navigationSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(4, 1, 10, 10));
        window.add(new JLabel("1"));
        window.add(new JLabel("2"));
        window.add(new JLabel("3"));
        createBackButton();
        add(window);
        window.updateUI();
    }
    
    private void defrostSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(4, 1, 10, 10));
        window.add(new JLabel("1"));
        window.add(new JLabel("2"));
        window.add(new JLabel("3"));
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
