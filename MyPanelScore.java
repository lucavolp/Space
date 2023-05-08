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

public class MyPanelScore extends JPanel implements Runnable
{
    private JLabel punteggio;
    private MyPanelGioco pannelloGioco;
    private Timer tUpPunti;     //timer di update dei punti
    private long pt;
    private int tUpdate;        //ms di aggiornamento e aggiunta punti
    private Timer update;
    private Font font;
    private Thread punti;
    
    
    protected JLabel timer;
    private int tMin=0;
    private int tSec=0;
    
    //Sfondo
    private Image backgroundImage;
    
    public MyPanelScore(MyPanelGioco p2)
    {
        super();
        this.pannelloGioco = p2;
        setLayout(new GridBagLayout()); // imposta il layout GroupLayout
        //GroupLayout layout = (GroupLayout)getLayout();
        
        //Immagine di sfondo
        /*try {
            backgroundImage = ImageIO.read(new File("img/latosx.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        
        //Impostazione font Astro
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
        
        GridBagConstraints l = new GridBagConstraints();
        
        l.weighty = 1;
        l.gridy = 0;
        l.anchor = GridBagConstraints.CENTER;
        
        punteggio = new JLabel("SCORE 0"); 
        punteggio.setFont(font.deriveFont(25f));
        add(punteggio, l);
        
        l.gridy = 1;
        l.anchor = GridBagConstraints.NORTH;
        timer= new JLabel("Tempo di Gioco 00:00");
        timer.setFont(font.deriveFont(20f));
        add(timer, l);
        
        /*
        // crea un oggetto di tipo GroupLayout.SequentialGroup per la colonna verticale
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(20, 20, 20); // aggiunge un margine superiore di 10 pixel
        vGroup.addComponent(punteggio); // aggiunge la JLabel punteggio
        vGroup.addGap(10, 10, 10); // aggiunge un margine di 10 pixel tra le JLabel
        vGroup.addComponent(timer); // aggiunge la JLabel timer
        vGroup.addGap(10, 10, 10); // aggiunge un margine inferiore di 10 pixel
        
        // crea un oggetto di tipo GroupLayout.ParallelGroup per la colonna orizzontale
        GroupLayout.ParallelGroup hGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
        hGroup.addComponent(punteggio); // centra la JLabel punteggio orizzontalmente
        hGroup.addComponent(timer); // centra la JLabel timer orizzontalmente
        
        // imposta le due colonne (verticale e orizzontale) del layout
        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);*/

        startThread();
    }
    
    //Per l'immagine di sfondo
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
        
    public void run()
    {
        try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        while(!pannelloGioco.gameStatus())
        {
            if(!pannelloGioco.getPause())
            {
                pt++;
                punteggio.setText("SCORE "+(pannelloGioco.getTotM()+pt)+"");
                
                tSec++;
                if(tSec>59)
                {
                    tSec=0;
                    tMin++;
                }
                if(tSec>9)
                    timer.setText("Tempo di Gioco "+tMin+":"+tSec);
                else
                    timer.setText("Tempo di Gioco "+tMin+":0"+tSec);
            }
            
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void startThread(){
        punti = new Thread(this, "Score");
        punti.start();
    }
    
    public void stop(){
        punti.stop();
    }
}
