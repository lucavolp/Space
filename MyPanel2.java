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
    
    
    private JLabel spaceshipLabel;
    private int spaceshipX, spaceshipY;
    private int spaceshipSpeed;
    public MyPanel2() 
    {
        super();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        
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
    
        // Initialize the spaceship image
        ImageIcon spaceshipIcon = new ImageIcon("./img/spaceship.jpg");
        spaceshipLabel = new JLabel(spaceshipIcon);
        spaceshipLabel.setSize(70,70);
        spaceshipX = 0;
        spaceshipY = 100;
        spaceshipSpeed = 5;
        add(spaceshipLabel);

        // Create a timer to move the spaceship
        
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        timer.start();
        
        // Add keyboard event listeners to move the spaceship left and right
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    spaceshipSpeed = -5;
                    System.out.println("Sinistra");
                    spaceshipX += spaceshipSpeed;
                    spaceshipLabel.setBounds(spaceshipX, spaceshipY, spaceshipIcon.getIconWidth(), spaceshipIcon.getIconHeight());
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    spaceshipSpeed = 5;
                    System.out.println("Destra");
                    spaceshipX += spaceshipSpeed;
                    spaceshipLabel.setBounds(spaceshipX, spaceshipY, spaceshipIcon.getIconWidth(), spaceshipIcon.getIconHeight());
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                    spaceshipSpeed = 0;
                }
            }
        });
        setFocusable(true);
    }

    public void stopMoving() {
        timer.stop();
    }
    
    //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^SPACESHIP^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
}
//}
