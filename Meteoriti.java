import javax.swing.*;
import java.awt.event.*;

public class Meteoriti extends JFrame {
    private JLabel label;

    public Meteoriti() {
        label = new JLabel("Hello World!");
        add(label);

        Timer timer = new Timer(10, new ActionListener() {
            int x = 0;
            int deltaX = 1;

            public void actionPerformed(ActionEvent evt) {
                x += deltaX;
                /*if (x + label.getWidth() > getWidth() || x < 0) {
                    deltaX *= -1;
                }*/
                label.setLocation(0, x);
            }
        });

        timer.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Meteoriti());
    }
}
