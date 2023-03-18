import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwitchPanels extends JFrame {
    private JPanel panel1, panel2;
    private CardLayout cardLayout;

    public SwitchPanels() {
        super("Switch Panels Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panels
        panel1 = new JPanel();
        panel1.add(new JLabel("Panel 1"));
        panel2 = new JPanel();
        panel2.add(new JLabel("Panel 2"));

        // Create the card layout and add the panels to it
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        getContentPane().add(panel1, "panel1");
        getContentPane().add(panel2, "panel2");

        // Create a button to switch between the panels
        JButton switchButton = new JButton("Switch Panels");
        switchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(getContentPane());
            }
        });

        // Add the button to the frame
        getContentPane().add(switchButton, BorderLayout.SOUTH);

        // Set the size and make the frame visible
        setSize(200, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SwitchPanels();
    }
}
