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
import javax.imageio.*;

public class MyPanel extends JPanel //implements ActionListener 
{
    private MyFrame frame;
    private JLabel lillo;   //Label vuota per impaginazione
    private JLabel lillo2;  //Label vuota per impaginazione
    private JLabel lillo3;  //Label vuota per impaginazione
    private JLabel lillo4;  //Label vuota per impaginazione
    public JButton change;
    private JButton chiudi;
    public AscoltatoreEsterno as;
    
    //Sfondo
    private Image backgroundImage;
    
    
    public MyPanel(MyFrame f)
    {
        try {
            backgroundImage = ImageIO.read(new File("img/backgroundHome.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("erorreImmagine!!!!!!!!!!!");
        }
        
        frame = f;
        as= new AscoltatoreEsterno(this, frame);
        lillo = new JLabel("       ");
        lillo2 = new JLabel("       ");
        lillo3 = new JLabel("       ");
        lillo4 = new JLabel("       ");
        //add(lillo);
        change = new JButton("New Game");
        change.addActionListener(as);
        //add(change); //passa di là
        chiudi = new JButton("Chiudi");
        chiudi.addActionListener(as);
        //add(chiudi);
        
        //Layout home
        setLayout(new GridBagLayout());
        
        lillo.setPreferredSize(new Dimension(300,50));
        change.setPreferredSize(new Dimension(300,50));
        chiudi.setPreferredSize(new Dimension(300,50));
        
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        add(lillo2, c);
        c.gridy = 1;
        add(lillo3, c);
        c.gridy = 2;
        add(lillo4, c);
        c.gridy = 3;
        c.gridx = 0;
        add(change, c);
        c.gridy = 3;
        c.gridx = 1;
        add(lillo, c);
        c.gridy = 3;
        c.gridx = 2;
        add(chiudi, c);
        
        /*
        add(lillo);
        add(change);
        add(chiudi);
        */
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}