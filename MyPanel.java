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
    private MyPanelGioco panelGioco;
    private MyPanelScore panelScore;
    private MyPanelMenu panelMenu;
    private MyFrame f;

    public MyPanel(MyPanelGioco p2, MyPanelScore p3, MyPanelMenu p4, MyFrame f)
    {
        this.panelGioco = p2;
        this.panelScore = p3;
        this.panelMenu = p4;
        this.f = f;
        
        as= new AscoltatoreEsterno(this, panelGioco, panelScore, panelMenu, f);
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