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
    private long pt;
    private int tUpdate;        //ms di aggiornamento e aggiunta punti
    private Font font;
    private Thread punti;
    
    
    protected JLabel timer;
    private int tMin=0;
    private int tSec=0;
    private int minPrec = 0;
    private int incr; //Incremento di punti 
    
    //Sfondo
    private Image backgroundImage;
    
    public MyPanelScore(MyPanelGioco p2)
    {
        super();
        this.pannelloGioco = p2;
        
        //Immagine di sfondo
        /*try {
            backgroundImage = ImageIO.read(new File("img/latosx.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            backgroundImage = ImageIO.read(new File("img/sfondoLaterale1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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

        incr = 1;
        tUpdate = 1000;
        
        //Layout
        setLayout(new GridBagLayout()); // imposta il layout GridBagLayout

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

        startThread();
    }
    
    //Per l'immagine di sfondo
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
        
    public void run()
    {
        while(!pannelloGioco.gameStatus())
        {
            if(!pannelloGioco.getPause())
            {
                pt += incr;
                punteggio.setText("SCORE "+pt);
                
                tSec++;
                if(tSec>59)
                {
                    tSec=0;
                    tMin++;
                }
                if(tSec>9)
                    timer.setText("Tempo di Gioco "+tMin+":"+tSec);
                else
                    timer.setText("Tempo di Gioco "+tMin+" 0"+tSec);

                aumentaDifficolta();
            }
            
            try {
                Thread.sleep(tUpdate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        punti.interrupt();
    }
    
    public void aumentaDifficolta()
    {
        /*if(tMin > 10) //Dopo 10 min perdi per forza hahah
                    pannelloGioco.editSpawnSpeed(1);*/
                    
        if(pannelloGioco.spawnSpeed() >= 100)
            if(tSec % 20 == 0) //Ogni 20 secondi cambia la velocità di spawn
            {
                pannelloGioco.editSpawnSpeed(pannelloGioco.spawnSpeed() - 35); //Diminuisce di 35 millesimi
                pannelloGioco.roberto.incrVMov();   //aumenta il moltiplicatore di velocità di spostamento della navicella
            }
        
        if(tMin == 0 && tSec == 45) //Ai primi 45 sec aggiunge una vita al proiettile e diminuisce di 50ms
        {
            pannelloGioco.addVitaMeteoriti();
            pannelloGioco.roberto.editWaitShot(100);
        }
            
        if(tMin - 1 == minPrec) //Ogni minuto fa cose
        {
            //System.out.println("Aggiunta velocità");
            pannelloGioco.incrVelocitaMeteoriti(); //Aggiunge una vita al meteorite
            minPrec = tMin; //Per scorrere il minuto
            incr += 2;
        }
        
        if(tMin % 2 == 0 && tMin != 0 && tSec == 0) //Ogni due minuti fa cose
        {
            //System.out.println("Aggiunta vita e diminuito tempo shot");
            pannelloGioco.roberto.editWaitShot(100); //Riduce di 100ms il tempo di attesa prima di sparare
            pannelloGioco.addVitaMeteoriti();
            pannelloGioco.roberto.incrProjectileSpeed(1);
            pannelloGioco.aumentaSpawn();
        }
    }
    
    public void addScore(int score){
        pt += score;
    }
    
    public void startThread(){
        punti = new Thread(this, "Score");
        punti.start();
    }
}
