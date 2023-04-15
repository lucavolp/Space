
/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 * posso fare come nella classe projectile per la gestione dell'eliminazione del meteorite dalla lista, richiamo il metodo del pannello verifica
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class Meteoriti extends JLabel //implements Runnable // da modificare e mettere i thread
{
    private MyPanelGioco pannello;
    // Coordinata y del meteorite
    private int y = 0;
    // Coordinata x di spawn del meteorite
    private int posGenerazione;
    // Dimensione colonne dove spawnano i meteoriti
    private int posX;
    // Velocità con cui scende il meteorite
    private int deltaY;
    // Immagine che contiene il meteorite
    private Image met;
    // Variabile per vedere se il meteorite esiste
    private boolean eliminato = false;
    // Thread per il movimento del meteorite
    private Thread movimento;
    //Colpi che servono per distruggere un meteorite
    private int vita;

    public Meteoriti(int pos, int deltaY, int v, MyPanelGioco p) // Gli passo la posizione dove generare il meteorite (random) e la velocità di
                                          // cascata del meteorite
    {
        setPosizioneGenerazione(pos);
        setDeltaY(deltaY);
        vita = v;
        pannello = p;
        // Inserimento e ridimensionamento dell'immagine
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("img/meteorite.png"));
            met = bufferedImage.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIcon(new ImageIcon(met));
        this.setLocation(posGenerazione, 0);

        // Thread per far muovere il meteorite
        /*movimento = new Thread(this, "Meteorite");
        movimento.start();*/

    }

    public void run() // Metodo chiamato dal thread per far muovere il meteorite e gestisce se va fuori dallo schermo
    {
        while (!eliminato) 
        {
            move();

            Point labelLocation = this.getLocation();// Prende la posizione della Label
            if (labelLocation.y > Toolkit.getDefaultToolkit().getScreenSize().height) // Controlla se la Label contenente il meteorite è andata fuori dallo schermo
            {
                //Container parent = getParent(); // ottieni il pannello genitore
                eliminato = true; // Smette di fare il ciclo
                pannello.remove(this); // rimuove il componente dal pannello
                //stopThread(); // Stoppa il thread
            }

            repaint();
            try {
                Thread.sleep(10); // ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void collisioniProiettili() //Ogni metodo ha un suo thread che verifica le collisioni con i proiettili
    {
        int i = 0;
        while (!eliminato)
        {
            //System.out.println(pannello.roberto.proiettili.size()); //Gli elementi nella lista ci sono
            //if (pannello.roberto.proiettili.size() > 0) //significa che ci sono proiettili nel pannello
            while(pannello.roberto.proiettili.size() > 0)
            {
                //for(int i = 0; i < pannello.roberto.proiettili.size(); i++)//scorre tutta la lista e verifica se collidono
                //{
                    //Projectile p = pannello.roberto.proiettili.get(i); //I proiettili li prende
                    
                    if(this.getBounds().intersects(pannello.roberto.proiettili.get(i).getBounds()))
                    {
                        vita--;
                        System.out.println("Collisione con proiettile rilevata");
                        pannello.roberto.proiettili.get(i).destroy();
                        //pannello.remove(p);
                        if(vita <= 0)
                        {
                            eliminato = true;
                            pannello.remove(this);
                            //System.out.println("Vite finite");
                        }
                    }
                    if(i >= pannello.roberto.proiettili.size() - 1)
                        i = 0;
                    else 
                        i++;
                //}
            }
            
            try {
                Thread.sleep(1); //ferma il thread ogni 10 ms, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() // metodo per far muovere il meteorite
    {
        y += deltaY; // Incrementa la y della velocità con cui scende il meteorite
        this.setLocation(posGenerazione, y); // Sposta il meteorite
    }

    private void setPosizioneGenerazione(int pos)// Gestisce lo spawn del meteorite e lo divide nello schermo
    {
        posX = Toolkit.getDefaultToolkit().getScreenSize().width;// Prende la larghezza dello schermo
        posX /= 20;// Divide lo schermo in 20 parti
        int colonne = (int) posX * 5; // Le prime 5 parti le lascio per una colonna con i dati
        switch (pos) {
            case 1:
                posGenerazione = colonne;
                break;
            case 2:
                posGenerazione = colonne + posX;
                break;
            case 3:
                posGenerazione = colonne + posX * 2;
                break;
            case 4:
                posGenerazione = colonne + posX * 3;
                break;
            case 5:
                posGenerazione = colonne + posX * 4;
                break;
            case 6:
                posGenerazione = colonne + posX * 5;
                break;
            case 7:
                posGenerazione = colonne + posX * 6;
                break;
            case 8:
                posGenerazione = colonne + posX * 7;
                break;
            case 9:
                posGenerazione = colonne + posX * 8;
                break;
            case 10:
                posGenerazione = colonne + posX * 9;
                break;
        }
        posGenerazione = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 35;
    }

    public int getPosizioneGenerazione() {
        return posGenerazione;
    }

    private void setDeltaY(int d) {
        deltaY = d;
    }

    public int getDimCol() {
        return posX;
    }
    /*
    public void stopThread() {
        movimento.stop();
    }

    public void startThread() {
        movimento = new Thread(this, "Meteorite");
        movimento.start();
    }*/

    public boolean getEliminato() {
        return eliminato;
    }

    // Metodo per le collisioni
    public Rectangle boundsMeteorite() {
        return this.getBounds();
    }

    public String toString() {
        return "Debug!!";
    }
}
