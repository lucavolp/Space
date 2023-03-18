import javax.swing.*;
import java.awt.event.*;
import java.awt.Point;

public class Meteoriti extends JFrame {
    private JLabel label;
    private Point labelLocation;

    public Meteoriti() {
        label = new JLabel("Hello World!");
        add(label);

        Timer timer = new Timer(10, new ActionListener() {
            int x = 0;
            int deltaX = 1;

            public void actionPerformed(ActionEvent evt) {
                x += deltaX;
                labelLocation = label.getLocation();//Prende la posizione della Label
                
                if (labelLocation.y > getHeight()) {
                    remove(label);
                    revalidate(); // Aggiorna il pannello per mostrare le modifiche
                    repaint();    // Ridisegna il pannello per mostrare le modifiche
                    System.out.println("Ho eliminato la Label");
                }
                label.setLocation(1, x);
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
