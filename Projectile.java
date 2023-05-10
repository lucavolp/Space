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
    private Thread gestioneProiettile;
    // Immagine che contiene il meteorite
    private Image prt;
    
    private MyPanelGioco pannello;
    
    private Spaceship spaceship;
    
    public Projectile(int posX, int posY, int velocity, MyPanelGioco p) 
    {
        super();
        this.x = posX + 50;
        this.y = posY;
        this.velocity = velocity;
        pannello = p;
        
        spaceship = pannello.roberto; //prende la label con la navicella
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
        while (!getEliminato()) 
        {
            if(!pannello.getPause() && !pannello.gameStatus()) //Se non è in pausa o in game over
            {
                move();
                pannello.repaint();
            }
            
            try {
                Thread.sleep(10); // ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void move()
    {
        y-= velocity;
        this.setLocation(x, y); // muove il proiettile verso l'alto
        
        for (Meteoriti meteorite : pannello.meteoritis) 
        {
            if (this.getBounds().intersects(meteorite.getBounds())) 
            { // verifica la collisione
                destroy();
                meteorite.destroy(); // chiama il metodo destroy del meteorite
                break; // esci dal ciclo for se c'è stata una collisione
            }
        }
    
        if (y < 0) { // controlla se il proiettile è uscito dallo schermo
            destroy();
        }
    }
    
    public boolean getEliminato() {
        return eliminato;
    }
    
    public void destroy(){
        pannello.remove(this);
        pannello.repaint();
        eliminato = true;
        spaceship.verificaPEliminati();
    }
    
    public void startThread()
    {
        gestioneProiettile = new Thread(this, "Proiettile");
        gestioneProiettile.start();
    }
    /*
    public void stopThread()
    {
        gestioneProiettile.stop();
    }*/
}