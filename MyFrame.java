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
        setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
    }
}