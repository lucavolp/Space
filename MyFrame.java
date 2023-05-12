/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */


import javax.swing.*;
import java.awt.Toolkit; 

public class MyFrame extends JFrame 
{
    public MyFrame(String titolo) {
        super(titolo); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true); //Rimuove i bordi e i pulsanti di base (chiusura, ridimensiona..)
        //setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        setExtendedState(JFrame.MAXIMIZED_BOTH); //Imposta la finestra in fullscreen
        pack();
        this.setFocusableWindowState(true);
    }
}