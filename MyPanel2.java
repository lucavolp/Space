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

public class MyPanel2 extends JPanel //Inizia ad eseguire subito il codice e non quando si clicca avvio
{
    private JLabel pippo;
    public AscoltatoreEsterno as;
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    protected Random rand = new Random();
    private Meteoriti meteoriti;
    private Timer timer;
    
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
        RepaintManager repCmp=new RepaintManager();
        
        
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
        timer = new Timer(velocitaSpawn, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt) 
            {
                meteoriti = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
                add(meteoriti, gbc); // Aggiunge l'oggetto Meteoriti al pannello MyPanel2 
                revalidate();
                repaint();
            }
        });
        timer.start();
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        

        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvSPACESHIPvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        pluto= new JLabel("Spaceship");
        add(pluto);
        spaceship = new Spaceship();
        add(spaceship,gbc); // Aggiunge l'oggetto Spaceship al pannello MyPanel2 
        spaceship.setFocusable(true);
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPACESHIP^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    }
}
