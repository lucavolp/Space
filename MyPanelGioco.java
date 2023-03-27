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
    //^^^^^^^^^^^^^^^^^^^^^^^
    //si ti ho copiato le freccette perché sono carine
    
    public Timer timerGame;
    private int tGioco=0;
    
    //Variabili per la navicella
    private Spaceship spaceship;
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
        //SENZA LAYOUT SEMBRA CHE ALCUNI METEORITI RIMANGONO E NON ELIMINA LE LABEL
        
        
        /*setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        as= new AscoltatoreEsterno();
        pippo= new JLabel("Panel 2");
        add(pippo);*/
        
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
                
            }
        });
        timerMet.start();
        
        
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        

        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvSPACESHIPvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        //pluto= new JLabel("Spaceship");
        //add(pluto);
        spaceship = new Spaceship();
        this.add(spaceship); // Aggiunge l'oggetto Spaceship al pannello MyPanel2 
        //set();
        spaceship.setFocusable(true);
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPACESHIP^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        
        //TIMER DI GIOCO
        timerGame = new Timer(1000, new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt) 
            {
               tGioco++;
               //System.out.println(tGioco);
            }                    
        });
        timerGame.start();
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
        timerGame.stop();
    }
}

