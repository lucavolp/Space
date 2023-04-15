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

public class MyPanelMenu extends JPanel implements Runnable
{
    private MyPanelGioco pannelloGioco;
    
    public JButton pause; // mette in pausa il gioco, ferma il timer e gli asteroidi
    public JButton resume; //toglie il gioco dalla pausa
    private JButton restart; // resetta il punteggio e ricomincia una nuova partita
    private JButton back; // torna al menù principale
    private AscoltatoreEsterno as;
    private MyPanelGioco pg;
    private MyPanelScore ps;
    private MyPanel p;
    protected JLabel timer;
    private int tMin=0;
    private int tSec=0;
    public boolean isGamePaused=false;
    
    private Thread time;
    
    public MyPanelMenu(MyPanelGioco pg, MyPanelScore ps, MyPanel p)
    {
        super();
        
        this.pg = pg;
        this.p = p;
        this.ps = ps;
        
        as = new AscoltatoreEsterno(p, pg, ps, this);
        
        pause = new JButton("Pausa");
        pause.addActionListener(as);
        
        resume = new JButton("Riprendi");
        resume.addActionListener(as);
        
        restart = new JButton("Restart");
        restart.addActionListener(as);
        
        back = new JButton("Torna al menù principale");
        back.addActionListener(as);
        
        timer= new JLabel("00:00");
        
        // Crea un nuovo GridBagLayout e imposta il layout del pannello su di esso
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        
        // Crea un oggetto GridBagConstraints per impostare le proprietà di posizionamento dei bottoni
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10); // Aggiunge uno spazio vuoto intorno ai bottoni
        c.fill = GridBagConstraints.BOTH;
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
        
        c.gridy = 4;
        layout.setConstraints(timer, c);
        
        
        startThread();
    
    
        // Aggiunge al pannello
        add(pause);
        add(resume);
        add(restart);
        add(back);
        add(timer);
        resume.setVisible(false);

    }

    //Thread che gestisce il timer
    public void run() 
    {
        while(true) //Gestire sto isPaused una volta finito tutto
        {
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
                
            try {
                Thread.sleep(1000); //ogni secondo va avanti di un secondo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void startThread(){
        time = new Thread(this,"timerGioco");
        time.start();
    }
    
    public void stopThread(){
        time.stop();
    }
}

