
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
import java.util.Random;

public class Meteoriti extends JLabel implements Runnable // da modificare e mettere i thread
{
    //Frame dove c'è il pannello
    private MyFrame frame;
    //Pannello del gioco 
    private MyPanelGioco pannello;
    // Coordinata y del meteorite
    private int y = 0;
    // Coordinata x di spawn del meteorite
    private int posGenerazione;
    // Dimensione colonne dove spawnano i meteoriti
    private int posX;
    // Velocità con cui scende il meteorite
    private int speed;
    // Immagine che contiene il meteorite
    private Image met;
    // Variabile per vedere se il meteorite esiste
    private boolean eliminato = false;
    // Thread per il movimento del meteorite
    private Thread movimento;
    //Colpi che servono per distruggere un meteorite
    private int vita;

    public Meteoriti(int speed, int v, MyPanelGioco p, MyFrame f) // Gli passo la posizione dove generare il meteorite (random) e la velocità di cascata del meteorite
    {
        frame = f;
        pannello = p;
        setPosizioneGenerazione();
        setVelocita(speed);
        vita = v;
        setBounds(0 , 0, 19, 55);
        // Inserimento e ridimensionamento dell'immagine
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("img/meteorite.png"));
            met = bufferedImage.getScaledInstance(19, 55, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIcon(new ImageIcon(met));
        
        this.setLocation(posGenerazione, 0);
        
        startThread();
    }

    public void run() // Metodo chiamato dal thread per far muovere il meteorite e gestisce se va fuori dallo schermo
    {
        while (!eliminato && !pannello.gameStatus()) 
        {
            if(!pannello.getPause()) //Se il pannello non è in pausa o in game over
            {
                //Chiama il metodo per far muovere la navicella
                move();
                //Se il meteorite colpisce la navicella è game over
                if(this.getBounds().intersects(pannello.roberto.getBounds()))
                    GameOver();
            }
            
            try {
                Thread.sleep(10); // ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void move() // metodo per far muovere il meteorite
    {
        y += speed; // Incrementa la y della velocità con cui scende il meteorite
        this.setLocation(posGenerazione, y); // Sposta il meteorite
        
        if(y > Toolkit.getDefaultToolkit().getScreenSize().height)
            destroy();
        pannello.repaint();
    }

    private void GameOver()
    {
        pannello.setGameStatus(true); //Imposta il gioco in gameover
        pannello.verificaEliminati(); //Elimina gli ultimi meteoriti
        //pannello.stopThread();
        
        //Creazione del pannello del game over
        GameOver gg = new GameOver();
        Container c = frame.getContentPane();
        c.add(gg);
        //System.out.println("GameOver!!");
    }
    
    private void setPosizioneGenerazione()// Gestisce lo spawn del meteorite e lo divide nello schermo
    {
        Random random = new Random();
        Dimension size = pannello.getSize();
        
        //If per vedere se è troppo attaccato al margine destro o al sinistro
        if(size.width < 50)
            posGenerazione = random.nextInt(size.width + 70);
        else if(size.width > pannello.getWidth() - 70)
        {
            posGenerazione = random.nextInt(size.width - 70);
        }
        else
            posGenerazione = random.nextInt(size.width + 1);
    }
    
    //Damage per poter implementare più avanti con dei powerup per proiettili che fanno più danno
    public void hit(int damage)
    {
        vita -= damage;
        if(vita <= 0)
        {
            pannello.pannelloScore.addScore(5); //aggiunge un punteggio di 5 quando si elimina un meteorite
            destroy();
        }
    }

    private void setVelocita(int d) {
        speed= d;
    }

    public void startThread() {
        movimento = new Thread(this, "Meteorite");
        movimento.start();
    }

    public boolean getEliminato() {
        return eliminato;
    }
    
    public void destroy()
    {
        pannello.remove(this);
        pannello.repaint();
        eliminato = true;
        pannello.verificaEliminati();
    }
}
