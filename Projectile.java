/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Projectile extends JLabel implements Runnable
{
    private int velocity; //velocità a cui va il proiettile
    private int x = 0;
    private int y = 0;
    private boolean eliminato = false;
    // Thread per il movimento del proiettile
    private Thread movimento;
    // Immagine che contiene il meteorite
    private Image prt;
    
    private MovingLabel spaceship;

    public Projectile(int posX, int posY, int velocity, MovingLabel a) 
    {
        super();
        this.x = posX + 50;
        this.y = posY;
        this.velocity = velocity;
        spaceship = a;
        
        // Inserimento e ridimensionamento dell'immagine
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("img/proiettile.png"));
            prt = bufferedImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIcon(new ImageIcon(prt));
        
        this.setLocation(x, y);
        startThread();
    }
    
    public void run() // Metodo chiamato dal thread per far muovere il proiettile e gestisce se va fuori dallo schermo
    {
        while (!eliminato) {
            move();

            Point labelLocation = this.getLocation();// Prende la posizione della Label
            if (labelLocation.y < 0) // Controlla se la Label contenente il proiettile è andata fuori dallo schermo
            {
                Container parent = getParent(); // ottieni il pannello genitore
                eliminato = true; // Smette di fare il ciclo
                parent.remove(this); // rimuove il componente dal pannello
                spaceship.verificaPEliminati();
                stopThread(); // Stoppa il thread
            }
            

            repaint();
            try {
                Thread.sleep(10); // ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        y-= velocity;
        this.setLocation(x, y); // move the projectile upwards
    }

    public Rectangle proiettileBounds() {
        return this.getBounds();
    }
    
    public void stopThread() {
        movimento.stop();
    }

    public void startThread() {
        movimento = new Thread(this, "Proiettile");
        movimento.start();
    }
    
    public boolean getEliminato() {
        return eliminato;
    }
    
    public void destroy(){
        eliminato = true;
    }
}