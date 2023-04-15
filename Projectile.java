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

public class Projectile extends JLabel //implements Runnable
{
    private int velocity; //velocità a cui va il proiettile
    private int x = 0;
    private int y = 0;
    private boolean eliminato = false;
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
        //startThread();
    }
    
    public void run() // Metodo chiamato dal thread per far muovere il proiettile e gestisce se va fuori dallo schermo
    {
        while (!eliminato) {
            move();

            Point labelLocation = this.getLocation();// Prende la posizione della Label
            if (labelLocation.y < 0) // Controlla se la Label contenente il proiettile è andata fuori dallo schermo
            {
                //Container parent = getParent(); // ottieni il pannello genitore
                eliminato = true; // Smette di fare il ciclo
                pannello.remove(this); // rimuove il componente dal pannello
                spaceship.verificaPEliminati();
                //stopThread(); // Stoppa il thread
            }
            

            pannello.repaint();
            try {
                Thread.sleep(10); // ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    //Metodo per verificare le collisioni con i meteoriti - "funziona"
    public void collisioneMeteoriti()
    {
        int i = 0;
        
        while(!eliminato)
        {
            while(pannello.meteoritis.size()>0)
            {
                if(this.getBounds().intersects(pannello.meteoritis.get(i).getBounds()))
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
    }

    public void move() {
        y-= velocity;
        this.setLocation(x, y); // move the projectile upwards
        MyPanelGioco a;
    }
    
    /**
    public void stopThread() {
        movimento.stop();
    }

    public void startThread() {
        movimento = new Thread(this, "Proiettile");
        movimento.start();
    }*/
    
    public boolean getEliminato() {
        return eliminato;
    }
    
    public void destroy(){
        eliminato = true;
        spaceship.verificaPEliminati();
        pannello.remove(this);
        pannello.repaint();
    }
    
    public void setIdThMovimento(long id){
         IdThreadMovimento = id;
    }
    
    public void setIdThCollisioni(long id){
        IdThreadCollisioni = id;
    }
    
    public long getIdThMovimento(){
        return IdThreadMovimento;
    }
    
    public long getIdThCollisioni(){
        return IdThreadCollisioni;
    }
    
    
}