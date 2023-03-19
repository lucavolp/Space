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
    
    public MyPanel2() 
    {
        super();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        velocitaMeteoriti = 5;
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
    }
}
