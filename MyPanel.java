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
    private MyFrame frame;
    private JLabel lillo;
    private JButton change;
    private JButton chiudi;
    public AscoltatoreEsterno as;

    public MyPanel(MyFrame f)
    {
        frame = f;
        as= new AscoltatoreEsterno(this, frame);
        lillo = new JLabel("Panel 1");
        add(lillo);
        change = new JButton("New Game");
        change.addActionListener(as);
        add(change); //passa di là
        chiudi = new JButton("Chiudi");
        chiudi.addActionListener(as);
        add(chiudi);
        
        
        //Layout home
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        add(lillo);
        add(change);
        add(chiudi);
        //--------
        
    }
}