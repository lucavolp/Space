/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class MyPanel2 extends JPanel 
{
    private JLabel pippo;
    public AscoltatoreEsterno as;
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    protected Random rand = new Random();
    private Meteoriti meteoriti;
    private Timer timerMet;
    
    private Timer timerGame;
    private int tGioco=0;
    
    private Spaceship spaceship;
    private JLabel spaceshipLabel;
    private int spaceshipX, spaceshipY;
    private int spaceshipSpeed;
    private JLabel pluto;
    public int saveX=999999;
    public int saveY=999999;
    
    public int pos[];
    
    public MyPanel2() 
    {
        super();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        
        
        /**
         * ogni volta che si invoca il repaint() dei meteoriti la spaceship viene visualizzata al centro fino all'input dopo
        **/
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvMETEORITIvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        velocitaMeteoriti = 7;
        velocitaSpawn = 2000;
        as= new AscoltatoreEsterno();
        pippo= new JLabel("Panel 2");
        add(pippo);
        // Crea un timer che genera un nuovo oggetto Meteoriti ogni x secondi
        timerMet = new Timer(velocitaSpawn, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt) 
            {
                meteoriti = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
                add(meteoriti, gbc); // Aggiunge l'oggetto Meteoriti al pannello MyPanel2 
                //add(meteoriti);
                revalidate();
                repaintCenter();
                
            }
        });
        timerMet.start();
        
        
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        

        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvSPACESHIPvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        pluto= new JLabel("Spaceship");
        add(pluto);
        spaceship = new Spaceship();
        add(spaceship); // Aggiunge l'oggetto Spaceship al pannello MyPanel2 
        spaceship.setFocusable(true);
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPACESHIP^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        
        //TIMER DI GIOCO
        timerGame = new Timer(1000, new ActionListener() 
        {
              public void actionPerformed(ActionEvent evt) 
            {
               tGioco++;
               System.out.println(tGioco);
            }                    
        });
        timerGame.start();
    }
    
    public void repaintCenter() 
    {
        int centerX = getWidth() / 2 - meteoriti.getDimCol()/2;
        int centerY =  getWidth() / 2 - meteoriti.getDimCol()/2;
        Rectangle repaintRect = new Rectangle(centerX, 0, 500, 500);
        
        repaint(repaintRect);
    }
    /*    
    public void paint(Graphics g)
    {
        // Set the color of the lines to red
        g.setColor(Color.RED);
        
        // Draw the first vertical line
        g.drawLine(50, 0, 50, 100);
                
        // Draw the second vertical line
        g.drawLine(100, 0, 100, 100);
    }*/
}
