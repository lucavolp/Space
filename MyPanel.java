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
    private JButton chiudi;
    public AscoltatoreEsterno as;
    private MyPanelGioco p2;
    private MyFrame f;

    public MyPanel(MyPanelGioco p2, MyFrame f)
    {
        this.p2 = p2;
        this.f = f;
        
        as= new AscoltatoreEsterno(this, p2, f);
        lillo = new JLabel("Panel 1");
        add(lillo);
        change = new JButton("New Game");
        change.addActionListener(as);
        add(change); //cambiamento
        chiudi = new JButton("Chiudi");
        chiudi.addActionListener(as);
        add(chiudi);
        
        
        //Layout home
        
        this.setAlignmentX(Component.CENTER_ALIGNMENT);         //sto coso dovrebbe centrare al centro dello schermo ma non centra :)
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(change);
        this.add(Box.createVerticalStrut(10)); //spazio fra i tasti senno sono brutti
        this.add(chiudi);
        //--------
    }
}