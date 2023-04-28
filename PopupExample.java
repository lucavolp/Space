import javax.swing.*;

public class PopupExample {

    public static void main(String[] args) {
        // Crea una finestra JFrame vuota
        JFrame frame = new JFrame();

        // Visualizza una finestra di dialogo con un messaggio
        JOptionPane.showMessageDialog(frame, "Benvenuto!");

        // Visualizza una finestra di dialogo con una domanda a cui l'utente può rispondere con "Sì" o "No"
        int result = JOptionPane.showConfirmDialog(frame, "Vuoi uscire dall'applicazione?");

        // Verifica la risposta dell'utente
        if (result == JOptionPane.YES_OPTION) {
            // Chiude l'applicazione
            System.exit(0);
        }
    }
}
