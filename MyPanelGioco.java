/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 * Lu, ma se rifai la spaceship come jlabel? dato che non avendo più layout in teoria quando repainta non dovrebbe spostarla al centro, e prima il movimento funzionava
*/


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.*;
import java.io.*;

public class MyPanelGioco extends JPanel implements Runnable
{
    
    private JLabel pippo;
    public AscoltatoreEsterno as;
    private Thread mainThread;
    private boolean GameOver = false;
    private boolean isPaused = false;
    
    //Variabili per i meteoriti
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    protected Random rand = new Random();
    protected Timer timerMet;
    public List<Meteoriti> meteoritis;
    
    
    //^^^^^^^^^^^^^^^^^^^^^^^
    //si ti ho copiato le freccette perché sono carine
    
    public int totM=0;      //totale di meteoriti spawnati
    public Timer timerGame;
    
    //Variabili per la navicella
    public Spaceship spaceship;
    private JLabel spaceshipLabel;
    private int spaceshipX, spaceshipY;
    private int spaceshipSpeed;
    private JLabel pluto;
    public int saveX=999999;
    public int saveY=999999;
    public int pos[];
    //^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    
    //Test
    //Navicella funzionante
    MovingLabel roberto;

    
    public MyPanelGioco() 
    {
        super();
        setLayout(null);
        //setFocusable(true);
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvMETEORITIvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        velocitaMeteoriti = 7;
        velocitaSpawn = 1000; //millisecondi
        meteoritis = new ArrayList<Meteoriti>();
        mainThread = new Thread(this, "Gioco");
        mainThread.start();
        totM = 0;
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        //Label con la navicella
        roberto = new MovingLabel();
        add(roberto);
        
        /*
        try {
          this.setContentPane(
            new JLabel(new ImageIcon(ImageIO.read(new File("your_file.jpeg")))));
        } catch (IOException e) {};*/
    }
    
    public void run()
    {
        while(!GameOver)
        {
            Meteoriti meteorite = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
            meteorite.setBounds(0 , 0, 40, 40);
            totM++; //contatore di meteoriti utilizzato per il punteggio
            add(meteorite); //lo aggiunge al pannello
            meteoritis.add(0, meteorite); //lo aggiunge alla lista, aggiunge in coda
            revalidate();
            repaint();
            
            verificaEliminati(); 
            
            try {
                Thread.sleep(velocitaSpawn); //ferma il thread ogni velocitaSpawn, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            /*
            Projectile pr;
            projectiles=new ArrayList<Projectile>();
            pr= new Projectile(800,800,15);
            projectiles.add(pr);
            */
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
                System.out.println("Elemento "+ i + " in teoria eliminato");
            }
        }
    }
    
    public void editSpawnSpeed(int ms)
    { //fare i vari controlli per le eccezzioni
        velocitaSpawn = ms;
    }
    
    public void stopThread()
    {
        for (Meteoriti meteorite : meteoritis) {
            meteorite.stopThread();
        }
        mainThread.stop();
        isPaused = true;
        //MyPanelScore.stop();
    }
    
    public void startThread()
    {
        for (Meteoriti meteorite : meteoritis) {
            meteorite.startThread();
        }
        mainThread = new Thread(this, "Gioco");
        mainThread.start();
    }
    
    public int getTotM()
    {
        return totM;
    }
    
    public boolean getPause(){
        return isPaused;
    }
    
    public void setGamePause(boolean l){
        isPaused = l;
    }
    
    public boolean gameStatus(){
        return GameOver;
    }
    
    public void setGameStatus(boolean l){
        GameOver = l;
    }
    
    public List<Meteoriti> getLista(){
        return meteoritis;
    }
}

