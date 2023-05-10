/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.*;
import java.io.*;

public class MyPanelGioco extends JPanel implements Runnable
{
    private MyFrame frame;
    public AscoltatoreEsterno as;
    //Thread principale per far eseguire il metodo run dentro questa classe
    private Thread mainThread;
    private boolean GameOver = false;
    private boolean isPaused = false;
    
    //Variabili per i meteoriti
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    public List<Meteoriti> meteoritis;
    private int vitaMeteoriti;
    //^^^^^^^^^^^^^^^^^^^^^^^
    //si ti ho copiato le freccette perché sono carine
    
    public int totM=0;      //totale di meteoriti spawnati
    
    //Variabili per la navicella
    Spaceship roberto;
    //^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    //Sfondo
    private Image backgroundImage;
    
    public MyPanelGioco(MyFrame f) 
    {
        super();
        setLayout(null);
        frame = f;
        
        try {
            backgroundImage = ImageIO.read(new File("img/background_game.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvMETEORITIvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        velocitaMeteoriti = 1;
        vitaMeteoriti = 4;
        velocitaSpawn = 2000; //millisecondi
        meteoritis = new ArrayList<Meteoriti>();
        totM = 0;
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        //Label con la navicella
        roberto = new Spaceship(this);
        add(roberto);
        
        mainThread = new Thread(this, "Space");
        mainThread.start();
    }
    
    //Per l'immagine di sfondo
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    
    public void run()
    {
        while(!gameStatus())
        {
            if(!getPause())
            {
                Meteoriti meteorite = new Meteoriti(velocitaMeteoriti, vitaMeteoriti, this, frame);
                meteorite.setBounds(0 , 0, 50, 55);
                totM++; //contatore di meteoriti utilizzato per il punteggio
                add(meteorite); //lo aggiunge al pannello
                meteoritis.add(meteorite); //lo aggiunge alla lista, aggiunge in coda
                revalidate();
                repaint();
                verificaEliminati();
            }
            
            //Dopo la pausa riprende a spawnare meteoriti dopo velocitaSpawn tempo
            try {
                Thread.sleep(velocitaSpawn); //ferma il thread ogni velocitaSpawn, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void verificaEliminati() //Metodo per eliminare dalla lista i meteoriti eliminati dal pannello
    {
        for (int i = meteoritis.size() - 1; i >= 0; i--) 
        {
            Meteoriti m = meteoritis.get(i);
            if(m.getEliminato())
            {
                meteoritis.remove(i);
            }
        }
    }
    
    public void editSpawnSpeed(int ms)
    { //fare i vari controlli per le eccezzioni
        velocitaSpawn = ms;
    }
    
    /*
    protected void stopThread() //Ferma tutti i thread dei proiettili, i meteoriti e quello principale del pannello
    {
        
        int i=0;
        for (Meteoriti meteorite : meteoritis) {
            meteorite.stopThread();
            i++;
            System.out.println(i);
        }
        roberto.stopThread();
        mainThread.stop();
    }*/
    
    public void startThread()
    {
        for (Meteoriti meteorite : meteoritis) {
            meteorite.startThread();
        }
        roberto.startThread();
        mainThread = new Thread(this, "Gioco");
        mainThread.start();
    }
    
    public int getTotM()    {
        return totM;
    }
    
    //Ritorna se il gioco è in pausa o meno
    public boolean getPause(){
        return isPaused;
    }
    
    //Cambia lo stato di pausa del gioco
    public void setGamePause(boolean l){
        isPaused = l;
    }
    
    public boolean gameStatus(){
        return GameOver;
    }
    
    public void setGameStatus(boolean l){
        GameOver = l;
    }
}

