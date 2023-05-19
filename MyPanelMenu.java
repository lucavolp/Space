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
import java.util.Timer;
import javax.imageio.*;

public class MyPanelMenu extends JPanel
{
    public JButton pause; // mette in pausa il gioco, ferma il timer e gli asteroidi
    public JButton resume; //toglie il gioco dalla pausa
    public JButton restart; // resetta il punteggio e ricomincia una nuova partita
    public JButton back; // torna al menù principale
    
    private AscoltatoreEsterno as;
    private MyPanelGioco pannelloGioco;
    private MyPanelScore pannelloScore;
    private MyPanel p;
    private MyFrame frame;
    
    //Sfondo
    private Image backgroundImage;
    
    //Font
    private Font font;
    
    public MyPanelMenu(MyFrame f, MyPanel p)
    {
        super();
        
        //Immagine sfondo banda laterale
        try {
            backgroundImage = ImageIO.read(new File("img/sfondoLaterale2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Impostazione Font
        try
        {
            try
            { 
                font = Font.createFont(Font.TRUETYPE_FONT, new File("font/astro/Futuristic Font/Astro.ttf"));
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
        catch (FontFormatException ffe)
        {
            ffe.printStackTrace();
        }
        
        this.p = p;
        this.frame = f;
        
        
        
        pause = new JButton("Pausa");
        pause.setFont(font.deriveFont(12f));
        
        
        resume = new JButton("Riprendi");
        resume.setFont(font.deriveFont(12f));
        
        
        restart = new JButton("Restart");
        restart.setFont(font.deriveFont(12f));
        
        
        back = new JButton("Torna alla home");
        back.setFont(font.deriveFont(12f));
        
        
        
        // Crea un nuovo GridBagLayout e imposta il layout del pannello su di esso
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        
        // Crea un oggetto GridBagConstraints per impostare le proprietà di posizionamento dei bottoni
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10); // Aggiunge uno spazio vuoto intorno ai bottoni
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        
        layout.setConstraints(pause, c);
        
        c.gridy = 1;
        layout.setConstraints(resume, c);
        
        c.gridy = 2;
        layout.setConstraints(restart, c);
        
        c.gridy = 3;
        layout.setConstraints(back, c);
        
        //c.gridy = 4;
        //layout.setConstraints(timer, c);
        
        
        //startThread();
    
    
        // Aggiunge al pannello
        add(pause);
        add(resume);
        add(restart);
        add(back);
        //add(timer);
        resume.setVisible(false);
        /*
        pause.setDefaultCapable(false);
        restart.setDefaultCapable(false);
        back.setDefaultCapable(false);
        resume.setDefaultCapable(false);*/
    }
    
    //Per l'immagine di sfondo
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    
    public void setPanelScore(MyPanelScore ps){
        pannelloScore = ps;
    }
    
    public void setPanelGioco(MyPanelGioco pg){
        pannelloGioco = pg;
        as = new AscoltatoreEsterno(frame, p, pannelloGioco, pannelloScore, this);
        pause.addActionListener(as);
        resume.addActionListener(as);
        restart.addActionListener(as);
        back.addActionListener(as);
    }
}

