import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class Phone extends JFrame {

    private Car car;

    private JPanel window = new JPanel();

    private JRadioButton on;
    private JRadioButton off;
    private ButtonGroup radioStatus;

    private JButton radioButton;
    private JButton heatingButton;
    private JButton navigationButton;
    private JButton defrostButton;

    private JButton backButton;

    public Phone(Car car) {
        this.car = car;

        setTitle("McMaster Engineering Competition | 2015 | Programming | Phone");
        setSize(400, 600);

        home();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public String getRadioStatus() {
        return (on.isSelected()) ? "ON" : "OFF";
    }

    private void home() {
        window.removeAll();
        window = new JPanel(new GridLayout(2, 2, 10, 10));

        radioButton = new JButton("Radio");
        heatingButton = new JButton("Heating");
        navigationButton = new JButton("Navigation");
        defrostButton = new JButton("Defrost");

        radioButton.addActionListener(e -> { radioSettings(); });
        heatingButton.addActionListener(e -> { heatingSettings(); });
        navigationButton.addActionListener(e -> { navigationSettings(); });
        defrostButton.addActionListener(e -> { defrostSettings(); });

        window.add(radioButton);
        window.add(heatingButton);
        window.add(navigationButton);
        window.add(defrostButton);

        add(window);
        window.updateUI();
    }

    private void radioSettings() {
        window.removeAll();
        window = new JPanel(new GridLayout(4, 1, 10, 10));

        on = new JRadioButton("ON");
        off = new JRadioButton("OFF");
        off.setSelected(true);

        on.addActionListener(e -> { car.update(this); });
        off.addActionListener(e -> { car.update(this); });

        radioStatus = new ButtonGroup();
        radioStatus.add(on);
        radioStatus.add(off);

        window.add(on);
        window.add(off);

        window.add(new JLabel("1"));
        window.add(new JLabel("2"));
        window.add(new JLabel("3"));
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
