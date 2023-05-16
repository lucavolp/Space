/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

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
    private MyPanel pannelloHome;
    
    public AscoltatoreEsterno as;
    
    protected MyPanelScore pannelloScore;
    protected MyPanelMenu pannelloMenu;
    
    //Thread principale per far eseguire il metodo run dentro questa classe
    private Thread mainThread;
    private boolean GameOver;
    private boolean isPaused;
    
    //Variabili per i meteoriti
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    public List<Meteoriti> meteoritis;
    private int vitaMeteoriti;
    private int meteoritiSpawn;
    //^^^^^^^^^^^^^^^^^^^^^^^
    //si ti ho copiato le freccette perché sono carine
    
    public int totM;      //totale di meteoriti spawnati
    
    //Variabili per la navicella
    Spaceship roberto;
    //^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    //Sfondo
    private Image backgroundImage;
    
    public MyPanelGioco(MyFrame f, MyPanel p, MyPanelMenu mpm) 
    {
        super();
        this.requestFocus();
        setLayout(null);
        
        frame = f;
        pannelloHome = p;
        GameOver = false;
        isPaused = false;
        meteoritiSpawn = 1;
        pannelloMenu=mpm;
        
        try {
            backgroundImage = ImageIO.read(new File("img/background_game.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvMETEORITIvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        velocitaMeteoriti = 2;
        vitaMeteoriti = 1;
        velocitaSpawn = 2000; //millisecondi
        meteoritis = new ArrayList<Meteoriti>();
        totM = 0;
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        //Label con la navicella
        roberto = new Spaceship(this);
        add(roberto);
        
        mainThread = new Thread(this, "Space");
        mainThread.start();
        
        
        
        //questa cosa qui simula un click sulla finestra per provare a prendere il 
        //focus appena viene creato il pannello, stackoverflow lo consigliava ma mhh
        try {
            // Creazione dell'oggetto Robot
            Robot robot = new Robot();

            // Simulazione del click sulla finestra del programma Java
            robot.mouseMove(50, 50);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        this.requestFocus();
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
                for(int i = 0; i < meteoritiSpawn; i++) //Spawna meteoriti meteoritiSpawn volte
                {
                    Meteoriti meteorite = new Meteoriti(velocitaMeteoriti, vitaMeteoriti, this, frame, pannelloHome, pannelloMenu);
                    totM++; //contatore di meteoriti utilizzato per il punteggio
                    add(meteorite); //lo aggiunge al pannello
                    meteoritis.add(meteorite); //lo aggiunge alla lista, aggiunge in coda
                }
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
        mainThread.interrupt();
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
    
    public void startThread()
    {
        for (Meteoriti meteorite : meteoritis) {
            meteorite.startThread();
        }
        roberto.startThread();
        mainThread = new Thread(this, "Gioco");
        mainThread.start();
    }
    
    public int getTotM(){
        return totM;
    }
    
    public void setPanelScore(MyPanelScore s){
        pannelloScore = s;
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
    
    /**
     * Metodi per modificare la difficoltà
    **/
    
    public int spawnSpeed(){
        return velocitaSpawn;
    }
    
    public void editSpawnSpeed(int ms)
    { //fare i vari controlli per le eccezzioni
        velocitaSpawn = ms;
    }
    
    public void addVitaMeteoriti(){
        vitaMeteoriti++;
    }
    
    public void incrVelocitaMeteoriti(){
        velocitaMeteoriti += 1;
    }
    
    public void aumentaSpawn(){
        meteoritiSpawn ++;
    }
}

