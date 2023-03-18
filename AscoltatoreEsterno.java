/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AscoltatoreEsterno extends Test implements ActionListener
{
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "New Game")
        {
            change();
        }
        if(e.getActionCommand() == "Home")
        {
        }
    }
}
