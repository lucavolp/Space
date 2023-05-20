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
    private JButton btnStart;
    
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
        
        btnStart=new JButton("ciaoooooooooooooooo");
        //btnStart.setPreferredSize(new Dimension(1,1));
        
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
        
        btnStart.setBounds(0,0,1,1);
        
        mainThread = new Thread(this, "Space");
        mainThread.start();
        
        add(btnStart);
        //System.out.println(btnStart.getLocationOnScreen().x+btnStart.getLocationOnScreen().y);
        /*
        try {
            // Creazione dell'oggetto Robot
            Robot robot = new Robot();

            // Simulazione del click sulla finestra del programma Java
            robot.mouseMove(btnStart.getLocationOnScreen().x,btnStart.getLocationOnScreen().y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        */
        //System.out.println(pannelloMenu.back.getLocation().x);
        btnStart.setDefaultCapable(false);
        
        //questo blocco di codice ti dice che componente ha il focus
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_TYPED) {
                    Component focusedComponent = manager.getFocusOwner();
                    //System.out.println("Focused component: " + focusedComponent);
                }
                return false;
            }
        });
        
        
        try {
            // Creazione dell'oggetto Robot
            Robot robot = new Robot();

            // Simulazione del click sulla finestra del programma Java
            robot.mouseMove(this.getBounds().x,0);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        /*
        try {
            // Creazione dell'oggetto Robot
            Robot robot = new Robot();

            // Simulazione del click sulla finestra del programma Java
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            //robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }*/
        
        
        
        //se non prende sto focus gli do fuoco io
        
        
        roberto.requestFocusInWindow();
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

