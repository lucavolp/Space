/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

//hai cambiato un botto di coses che non me ne ero accorto e ho fatto na figura di merda con la piergio hahah
//ma quando? hahaha

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class MyPanelGioco extends JPanel implements Runnable//Inizia ad eseguire subito il codice e non quando si clicca avvio
{
    
    private JLabel pippo;
    public AscoltatoreEsterno as;
    
    //Variabili per i meteoriti
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    protected Random rand = new Random();
    protected Timer timerMet;
    
    //prove
    private List<Meteoriti> meteoritis;
    private Thread mainThread;
    
    //^^^^^^^^^^^^^^^^^^^^^^^
    //si ti ho copiato le freccette perché sono carine
    
    public int totM=0;      //totale di meteoriti spawnati
    public Timer timerGame;
    
    //Variabili per la navicella
    public Spaceship spaceship;
    private JLabel spaceshipLabel;
    private int spaceshipX, spaceshipY;
    private int spaceshipSpeed;
    private JLabel pluto;
    public int saveX=999999;
    public int saveY=999999;
    public int pos[];
    //^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    
    public MyPanelGioco() 
    {
        super();
        setLayout(null);
        
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvMETEORITIvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv        
        velocitaMeteoriti = 7;
        velocitaSpawn = 2000; //millisecondi
        meteoritis = new ArrayList<Meteoriti>();
        Thread threadGioco = new Thread(this, "Generazione meteoriti");
        threadGioco.start();
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvSPACESHIPvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPACESHIP^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    }
    
    public void run()
    {
        while(true)
        {
            Meteoriti meteorite = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
            meteorite.setBounds(0 , 0, 40, 40);
            add(meteorite); //lo aggiunge al pannello
            meteoritis.add(meteorite); //lo aggiunge alla lista
            revalidate();
            repaint();
            
            try {
                Thread.sleep(velocitaSpawn); //ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void editSpawnSpeed(int ms)
    { //fare i vari controlli per le eccezzioni
        velocitaSpawn = ms;
    }
    
    public void repaintCenter() 
    {
        int centerX = getWidth();
        int centerY =  getHeight();
        Rectangle repaintRect = new Rectangle(0, centerY, centerX, centerY+150);
        
        repaint(repaintRect);
    }
    
    public void stopTimer()
    {
        //timerMet.stop();
        //timerGame.stop();
    }
    
    public int getTotM()
    {
        return totM;
    }
}

