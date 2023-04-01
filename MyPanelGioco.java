/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

//hai cambiato un botto di coses che non me ne ero accorto e ho fatto na figura di merda con la piergio hahah

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class MyPanelGioco extends JPanel //Inizia ad eseguire subito il codice e non quando si clicca avvio
{
    
    
    private JLabel pippo;
    public AscoltatoreEsterno as;
    
    //Variabili per i meteoriti
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    protected Random rand = new Random();
    //protected Meteoriti meteoriti;
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
        
        
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvMETEORITIvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv        
        velocitaMeteoriti = 7;
        velocitaSpawn = 2000;
        
        //test
        meteoritis = new ArrayList<Meteoriti>();
        //Tutto il resto del codice (creazione e aggiunta) lo gestisce nel metodo run()
                
        /*// Crea un timer che genera un nuovo oggetto Meteoriti ogni x secondi
        timerMet = new Timer(velocitaSpawn, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt) 
            {
                Meteoriti meteorite = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
                add(meteorite); //lo aggiunge al pannello
                meteoritis.add(meteorite); //lo aggiunge alla lista
                revalidate();
                repaintCenter();
                totM++;
            }
        });
        timerMet.start();*/
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvSPACESHIPvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        /*spaceship = new Spaceship();
        add(spaceship);
        spaceship.setFocusable(true);*/
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPACESHIP^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        mainThread = new Thread(this);
        mainThread.start();
    }
    
    public void run()
    {
        while(true)
        {
            Meteoriti meteorite = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
            add(meteorite); //lo aggiunge al pannello
            meteoritis.add(meteorite); //lo aggiunge alla lista
            revalidate();
            
            repaint();
            
            System.out.println("Ho eseguito un ciclo");
            try {
                Thread.sleep(50); //ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
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

