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

public class MyPanelScore extends JPanel
{
    private MyPanelGioco pannelloGioco;
    private JLabel punteggio;
    private MyPanelGioco p2;
    private Timer tUpPunti;     //timer di update dei punti
    private long pt;
    private int tUpdate;        //ms di aggiornamento e aggiunta punti
    private Timer update;
    
    public MyPanelScore(MyPanelGioco p2)
    {
        super();
        this.p2 = p2;
        
        pannelloGioco = p2;
        
        punteggio = new JLabel("0");       
        
        add(punteggio);
        
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
    }
        
    public void aggPunti()
    {
        pt++;
        punteggio.setText("SCORE: "+(p2.getTotM()+pt)+"");
    }
}
