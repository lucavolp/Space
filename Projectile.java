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
    // Thread per il movimento del proiettile
    //private Thread movimento;
    // Immagine che contiene il meteorite
    private Image prt;
    
    private MyPanelGioco pannello;
    
    private MovingLabel spaceship;
    
    private long IdThreadMovimento;
    private long IdThreadCollisioni;
    
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
        while (!getEliminato()) {
            move();
            pannello.repaint();
            try {
                Thread.sleep(10); // ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /*
     * 
    Codice per verificare se va fuori dallo schermo ma "deprecato"
    Point labelLocation = this.getLocation();// Prende la posizione della Label
    if (labelLocation.y < 0) // Controlla se la Label contenente il proiettile è andata fuori dallo schermo
    {
        //Container parent = getParent(); // ottieni il pannello genitore
        eliminato = true; // Smette di fare il ciclo
        pannello.remove(this); // rimuove il componente dal pannello
        spaceship.verificaPEliminati();
        //stopThread(); // Stoppa il thread
    }
    
    //Metodo per verificare le collisioni con i meteoriti - "funziona"
    public void collisioneMeteoriti()
    {
        int i = 0;
        
        while(!getEliminato())
        {
            while(pannello.meteoritis.size()>0)
            {
                if(this.getBounds().intersects(pannello.meteoritis.get(i).getBounds())) //Da sempre errore perchè o this.getBounds non esiste più o il meteorite non esiste più 
                {
                    System.out.println("Collisione con proiettile rilevata");
                    destroy();
                    pannello.meteoritis.get(i).destroy(); //chiama il metodo destroy del meteorite
                }
                
                if(i >= pannello.roberto.proiettili.size() - 1)
                        i = 0;
                    else 
                        i++;
            }
        }
    }*/

    public void move()
    {
        y-= velocity;
        this.setLocation(x, y); // muove il proiettile verso l'alto
        
        for (Meteoriti meteorite : pannello.meteoritis) 
        { // itera sulla lista di meteoriti
            if (this.getBounds().intersects(meteorite.getBounds())) { // verifica la collisione
                //System.out.println("Collisione con proiettile rilevata");
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
    
    public void stopThread()
    {
        gestioneProiettile.stop();
    }
}