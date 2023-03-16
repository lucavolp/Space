/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
 
public class MyPanel2 extends JPanel
{
    private JLabel pippo;
    private JButton change;
    public AscoltatoreEsterno as;

    public MyPanel2()
    {
        
        as= new AscoltatoreEsterno();
        
        pippo= new JLabel("Panel 2");
        add(pippo);
        change = new JButton("Home");
        change.addActionListener(as);
        add(change); //cambiamento
    }
}
