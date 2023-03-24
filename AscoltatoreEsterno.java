/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AscoltatoreEsterno implements ActionListener
{
    MyPanel p;
    MyPanelGioco p2;
    MyFrame f;
    
    public AscoltatoreEsterno(MyPanel p, MyPanelGioco p2, MyFrame f)
    {
        this.p = p;
        this.p2 = p2;
        this.f = f;
    }
    
    public AscoltatoreEsterno()
    {}
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "New Game")
        {
            change();
        }
        if(e.getActionCommand() == "Chiudi")
        {
             System.exit(0);
        }
    }
    
    //Cambia pannello e lo switcha a quello contenente il gioco
    public void change()
    {
        f.remove(p);
        f.add(p2);
        f.revalidate();
        f.repaint();
    }
}
