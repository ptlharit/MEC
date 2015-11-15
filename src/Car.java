import javax.swing.*;

import org.json.JSONException;

import java.awt.*;
import java.io.IOException;
import java.util.Hashtable;

/// Car User Interface
public class Car extends JFrame {

    private JPanel window;

    public Car() {
        setTitle("McMaster Engineering Competition | 2015 | Programming | Car");
        setSize(800, 400);

        createScreen();

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createScreen() {
        window = new JPanel(new GridLayout(4, 2, 0, 10));

        JLabel radioModeLabel = new JLabel("Radio: ");
        JLabel radioMode = new JLabel("OFF");

        JLabel heatLevelLabel = new JLabel("Heat Level: ");
        JSlider heatLevel = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
        heatLevel.setMinorTickSpacing(1);
        heatLevel.setPaintTicks(true);
        heatLevel.setSnapToTicks(true);

        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(0), new JLabel("0"));
        labelTable.put(new Integer(1), new JLabel("1"));
        labelTable.put(new Integer(2), new JLabel("2"));
        labelTable.put(new Integer(3), new JLabel("3"));
        labelTable.put(new Integer(4), new JLabel("4"));
        labelTable.put(new Integer(5), new JLabel("5"));
        heatLevel.setLabelTable(labelTable);
        heatLevel.setPaintLabels(true);

        JLabel navigationCoordsLabel = new JLabel("Destination Coordinates: ");
        JLabel navigationCoords = new JLabel("( 0.0, 0.0 )");

        JLabel defoggerModeLabel = new JLabel("Defogger: ");
        JLabel defoggerMode = new JLabel("OFF");

        window.add(radioModeLabel);
        window.add(radioMode);

        window.add(heatLevelLabel);
        window.add(heatLevel);

        window.add(navigationCoordsLabel);
        window.add(navigationCoords);

        window.add(defoggerModeLabel);
        window.add(defoggerMode);

        add(window);
    }

    public void createScreen(Phone phone) throws IOException, JSONException {
        window = new JPanel(new GridLayout(4, 2, 0, 10));

        JLabel radioLabel = new JLabel("Radio");
        JLabel radio = new JLabel((phone.getRadioMode() == "ON") ? phone.getRadioMode() + " : " + phone.getRadioStation() : "OFF");

        JLabel heatLevelLabel = new JLabel("Heat Level: ");
        JSlider heatLevel = new JSlider(JSlider.HORIZONTAL, 0, 5, phone.getHeatLevel());
        heatLevel.setMinorTickSpacing(1);
        heatLevel.setPaintTicks(true);
        heatLevel.setSnapToTicks(true);

        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(0), new JLabel("0"));
        labelTable.put(new Integer(1), new JLabel("1"));
        labelTable.put(new Integer(2), new JLabel("2"));
        labelTable.put(new Integer(3), new JLabel("3"));
        labelTable.put(new Integer(4), new JLabel("4"));
        labelTable.put(new Integer(5), new JLabel("5"));
        heatLevel.setLabelTable(labelTable);
        heatLevel.setPaintLabels(true);

        JLabel navigationCoordsLabel = new JLabel("Destination Coordinates: ");
        JLabel navigationCoords = new JLabel("( " + phone.getLon() + ", " + phone.getLat() + " )");

        JLabel defoggerModeLabel = new JLabel("Defogger: ");
        JLabel defoggerMode = new JLabel(phone.getDefoggerMode());

        window.add(radioLabel);
        window.add(radio);

        window.add(heatLevelLabel);
        window.add(heatLevel);

        window.add(navigationCoordsLabel);
        window.add(navigationCoords);

        window.add(defoggerModeLabel);
        window.add(defoggerMode);

        add(window);
    }

    public void update(Phone phone) {
        window.removeAll();
        try { createScreen(phone); } catch (IOException | JSONException e) { }
        window.updateUI();
    }

    public void reset(Phone phone) {
        window.removeAll();
        phone.reset();
        try { createScreen(phone); } catch (IOException | JSONException e) { }
        window.updateUI();
    }
}
