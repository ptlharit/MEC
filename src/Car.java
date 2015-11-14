import javax.swing.*;
import java.awt.*;

public class Car extends JFrame {

    private JPanel window;

    public Car() {
        setTitle("McMaster Engineering Competition | 2015 | Programming | Car");
        setSize(800, 400);

        createScreen();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createScreen() {
        window = new JPanel(new GridLayout(1, 2, 10, 10));

        JLabel radio = new JLabel("Radio");
        JLabel radioStatus = new JLabel("OFF");

        window.add(radio);
        window.add(radioStatus);

        add(window);
    }

    public void createScreen(Phone phone) {
        window = new JPanel(new GridLayout(1, 2, 10, 10));

        JLabel radio = new JLabel("Radio");
        JLabel radioStatus = new JLabel(phone.getRadioStatus());

        window.add(radio);
        window.add(radioStatus);

        add(window);
    }

    public void update(Phone phone) {
        window.removeAll();
        createScreen(phone);
        window.updateUI();
    }
}
