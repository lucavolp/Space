import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JListExample {

    private static DefaultListModel<String> model;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JListExample::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Esempio JList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultListModel<>();
        JList<String> jList = new JList<>(model);
        jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //jList.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(jList);

        JButton removeButton = new JButton("Rimuovi");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = jList.getSelectedIndex();
                if (selectedIndex != -1) {
                    model.remove(selectedIndex);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(200, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Aggiungi elementi alla JList
        ArrayList<String> elementi = new ArrayList<>();
        elementi.add("Elemento 1");
        elementi.add("Elemento 2");
        elementi.add("Elemento 3");
        model.addAll(elementi);
    }
}
