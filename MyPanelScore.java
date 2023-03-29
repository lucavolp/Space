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
    private int dimY;
    private MyPanelGioco p2;
    private String tUpdate="1";
    
    private Timer update;
    
    public MyPanelScore(MyPanelGioco p2)
    {
        super();
        this.p2 = p2;
        
        pannelloGioco = p2;
        
        punteggio = new JLabel("0");       
        
        add(punteggio);
        
        
        //TIMER AGGIORNA PUNTI 1/s
        Timer timer = new Timer();
        timer.schedule(new TimerTask() 
        {
            public void run() {
                aggPunti();
            }
        }, 0, 1000);
    }
        
    public void aggPunti()
    {
        punteggio.setText(p2.getTotM()+"");
    }
}
