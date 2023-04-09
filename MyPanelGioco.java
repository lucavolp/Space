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


public class MyPanelGioco extends JPanel implements Runnable
{
    
    private JLabel pippo;
    public AscoltatoreEsterno as;
    private Thread mainThread;
    private boolean GameOver = false;
    protected boolean isPaused = false;
    
    //Variabili per i meteoriti
    private int velocitaMeteoriti;
    private int velocitaSpawn;
    protected Random rand = new Random();
    protected Timer timerMet;
    protected List<Meteoriti> meteoritis;
    
    
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
    
    
    
    //private List<Projectile> projectiles;

    
    public MyPanelGioco() 
    {
        super();
        setLayout(null);
        
        //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvMETEORITIvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv        
        velocitaMeteoriti = 7;
        velocitaSpawn = 90; //millisecondi
        meteoritis = new ArrayList<Meteoriti>();
        mainThread = new Thread(this, "Gioco");
        mainThread.start();
        totM = 0;
        //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^METEORITI^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
    }
    
    public void run()
    {
        while(!GameOver)
        {
            Meteoriti meteorite = new Meteoriti((rand.nextInt(10) + 1), velocitaMeteoriti);
            meteorite.setBounds(0 , 0, 40, 40);
            totM++; //contatore di meteoriti utilizzato per il punteggio
            add(meteorite); //lo aggiunge al pannello
            meteoritis.add(meteorite); //lo aggiunge alla lista, aggiunge in coda
            revalidate();
            repaint();
            
            for (int i = 0; i < meteoritis.size(); i++) {
                Meteoriti m = meteoritis.get(i);
                if(m.getEliminato())
                {
                    meteoritis.remove(i);
                }
            }
            
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
    
    /*
    public void collisionDetection() 
    {
        while (!GameOver) {
            if (meteoritis.size() > 0) {
                // prende l'ultimo elemento della lista meteoritis
                Meteoriti lastMeteorite = meteoritis.get(meteoritis.size() - 1);
    
                // verifica se è avvenuta la collisione
                if (lastMeteorite.getBounds().intersects(spaceship.getBounds())) {
                    System.out.println("Collisione avvenuta");
                    GameOver = true;
                    //break; // esce dal ciclo una volta che la collisione è stata rilevata
                }
            }
        }
    }
    */
    
    public void editSpawnSpeed(int ms)
    { //fare i vari controlli per le eccezzioni
        velocitaSpawn = ms;
    }
    
    public void repaintCenter() //Non serve più giusto?
    {
        int centerX = getWidth();
        int centerY =  getHeight();
        Rectangle repaintRect = new Rectangle(0, centerY, centerX, centerY+150);
        
        repaint(repaintRect);
    }
    
    public void stopThread()
    {
        for (Meteoriti meteorite : meteoritis) {
            meteorite.stopThread();
            //System.out.println(meteorite.toString());
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

