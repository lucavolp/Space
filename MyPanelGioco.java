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

public class MyPanelGioco extends JPanel //Inizia ad eseguire subito il codice e non quando si clicca avvio
{
    
    
    private JLabel pippo;
    public AscoltatoreEsterno as;
    
    //Variabili per i meteoriti
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    protected Random rand = new Random();
    protected Meteoriti meteoriti;
    protected Timer timerMet;
    
    //prove
    //private List<Meteoriti> meteoriti;
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
        
        /**
         * ogni volta che si invoca il repaint() dei meteoriti la spaceship viene visualizzata al centro fino all'input dopo
        **/
        
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvMETEORITIvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv        
        velocitaMeteoriti = 7;
        velocitaSpawn = 2000;
        
        // Crea un timer che genera un nuovo oggetto Meteoriti ogni x secondi
        timerMet = new Timer(velocitaSpawn, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt) 
            {
                meteoriti = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
                //add(meteoriti, gbc); // Aggiunge l'oggetto Meteoriti al pannello MyPanel2 
                add(meteoriti);
                revalidate();
                repaintCenter();
                totM++;
            }
        });
        timerMet.start();
        
        
        
        
        
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        

        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvSPACESHIPvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        
        /*
        spaceship = new Spaceship();
        add(spaceship);
        spaceship.setFocusable(true);
        */
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPACESHIP^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
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
        timerMet.stop();
        //timerGame.stop();
    }
    
    
    public int getTotM()
    {
        return totM;
    }
}

