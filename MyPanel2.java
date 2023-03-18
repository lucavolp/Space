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
    private Timer timer;
    protected Dimension panelSize;//Dimensione del pannello
    protected Random rand = new Random();
    
    
    public MyPanel2() {
        velocitaMeteoriti = 1;
        as= new AscoltatoreEsterno();
        pippo= new JLabel("Panel 2");
        add(pippo);
        
        // Crea un timer che genera un nuovo oggetto Meteoriti ogni 5 secondi
        timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Meteoriti m; 
                m = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
                add(m); // Aggiunge l'oggetto Meteoriti al pannello MyPanel2
                repaint(); // Ridisegna il pannello per visualizzare l'oggetto Meteoriti
                System.out.println("Ho creato il meteorite");
            }
        });
        timer.start();
    }
}
