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
        setLayout(new GridLayout(2,1));
        
        /*try {
            backgroundImage = ImageIO.read(new File("img/latosx.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        
        punteggio = new JLabel("SCORE: 0");       
        add(punteggio);
        
        timer= new JLabel("Tempo di Gioco: 00:00");
        add(timer);
        /*
        //TIMER AGGIORNA PUNTI .5/s
        tUpPunti = new Timer();
        tUpPunti.schedule(new TimerTask() 
        {
            public void run() {
                aggPunti();
            }
        }, 0, 500);
        */
        
        
        
        punti = new Thread(this, "Score");
        punti.start();
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
                punteggio.setText("SCORE: "+(pannelloGioco.getTotM()+pt)+"");
                
                tSec++;
                if(tSec>59)
                {
                    tSec=0;
                    tMin++;
                }
                if(tSec>9)
                    timer.setText("Tempo di Gioco: "+tMin+":"+tSec);
                else
                    timer.setText("Tempo di Gioco: "+tMin+":0"+tSec);
            }
            
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void stop(){
        punti.stop();
    }
}
