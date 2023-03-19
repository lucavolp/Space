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

public class MyPanel extends JPanel //implements ActionListener 
{
    private JLabel lillo;
    private JButton change;
    public AscoltatoreEsterno as;
    private MyPanel2 p2;
    private MyFrame f;

    public MyPanel(MyPanel2 p2, MyFrame f)
    {
        this.p2 = p2;
        this.f = f;
        
        as= new AscoltatoreEsterno(this, p2, f);
        
        lillo = new JLabel("Panel 1");
        add(lillo);
        change = new JButton("New Game");
        change.addActionListener(as);
        add(change); //cambiamento
        
    }

}