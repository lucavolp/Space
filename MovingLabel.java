import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

/**
 * Se ti può servire come esempio per lavorarci questa classe fa una label come navicella e si muove
 */

public class MovingLabel extends JLabel implements KeyListener 
{
    private int posX = 0;
    private int posY = 0;
    private Image sps;
    private int velocitaProiettili = 5;
    private MyPanelGioco pannello;
    private int dimX;
    private int dimY;
    public List<Projectile> proiettili;
    private long ultimoProiettile;
    
    public MovingLabel(MyPanelGioco p) 
    {
        super();
        addKeyListener(this);
        setFocusable(true);
        grabFocus();
        
        pannello = p;
        
        setPosizioneGenerazione();
        setBounds(posX, posY, 140, 140);
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("img/spaceship.png"));
            sps = bufferedImage.getScaledInstance(140, 140, Image.SCALE_DEFAULT);
        }catch(IOException e) 
        { 
          e.printStackTrace();
        }
        setIcon(new ImageIcon(sps));
        
        proiettili = new ArrayList<Projectile>();
        ultimoProiettile = System.currentTimeMillis();
    }
    
    private void setPosizioneGenerazione()//Setta la posizione al centro dello schermo al primo lancio della partita
    {
        dimX=Toolkit.getDefaultToolkit().getScreenSize().width;//Prende la larghezza dello schermo
        dimY=Toolkit.getDefaultToolkit().getScreenSize().height;//Prende l'altezza dello schermo
        
        //non mi funzionano perchè come posizione dello schermo mi prende 3000x1990
        posX=(dimX/2);  //posiziona partenza al centro dello schermo
        posY=dimY; //posiziona un po' staccata dal fondo
        
        /*
        posX= 880;
        posY= 890;*/
    }
    
    //Gestione pressione tasti
    public void keyPressed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        
        if(!pannello.gameStatus())
        if(!pannello.getPause()) //se il gioco non è in pausa allora la navicella prende gli input
            switch(keyCode) 
            {
                case KeyEvent.VK_LEFT, KeyEvent.VK_A: //Freccia sinistra
                    posX -= 8;//8 da sostituire con una variabile per la velocità di spostamento
                    move();
                    //setLocation(posX, posY);
                    break;
                    
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D : //Freccia destra
                    posX += 8;
                    move();
                    //setLocation(posX, posY);
                    break;
                    
                case KeyEvent.VK_UP, KeyEvent.VK_W: //Freccia su
                    posY -= 8;
                    move();
                    //setLocation(posX, posY);
                    break;
                    
                case KeyEvent.VK_DOWN, KeyEvent.VK_S: //Freccia giù
                    posY += 8;
                    move();
                    //setLocation(posX, posY);
                    break;
                    
                case KeyEvent.VK_SPACE: //Spazio
                    //Creazione nuovo proiettile
                    long currentTime = System.currentTimeMillis();
                    if(currentTime - ultimoProiettile >= 1000) //controlla che sia passato almeno 1000 millisecondi dalla generazione di quello precendente | possibile sostituzione con una variabile
                    {
                        nuovoProiettile();
                        ultimoProiettile = currentTime;
                    }
                    break;
            }
    }
    
    private void nuovoProiettile()
    {
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        
        //Crea un nuovo proiettile e lo aggiunge alla lista
        Projectile pnew = new Projectile(x, y, velocitaProiettili, pannello); //Gli passo anche questa classe così ogni volta che viene eliminato un proiettile lo elimina dalla lista
        proiettili.add(0, pnew);
        
        pnew.setBounds(0 , 0, 40, 40);
        pannello.add(pnew);
        
        /*
        //Fa partire i thread che eseguono i metodi per muovere il proiettile e verificare la collisione
        Runnable movimento = new Runnable(){
            public void run(){
                pnew.run();
            }
        };
        Thread threadMovimento = new Thread(movimento, "Movimento");
        threadMovimento.start();
        
        
        Runnable collisioniProiettili = new Runnable(){
            public void run(){
                pnew.collisioneMeteoriti();
            }
        };
        Thread threadCollisioni = new Thread(collisioniProiettili, "Collisioni");
        threadCollisioni.start();
        
        pnew.setIdThMovimento(threadMovimento.getId());
        pnew.setIdThCollisioni(threadCollisioni.getId());*/
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    public Rectangle boundsNavicella()
    {
        return this.getBounds();
    }
    
    public void verificaPEliminati()
    {
        for (int i = proiettili.size() - 1; i >= 0; i--) 
        {
            Projectile p = proiettili.get(i);
            if(p.getEliminato())
            {
                proiettili.remove(i);
            }
        }
    }
    
    //fa fare l'azione di movimento solo se si è all'interno dello spazio corretto
    private void move()
    {
        //System.out.println(getBounds().x);
        
        if((getBounds().x>0)&&(getBounds().x<dimX))
        {
            //System.out.println(getBounds().x);
            setLocation(posX, posY);
        }
        else if(getBounds().x==0);
        
        //RIFARE CON LA VELOCITA
            
        /*else if((spaceshipSpeed<0)&&(posX>=dimX-80))
            setLocation(posX, posY);
        else if((spaceshipSpeed>0)&&(posX==0))
            setLocation(posX, posY);     */
    }
    
    protected void stopThread()
    {
        for (Projectile p : proiettili) {
            p.stopThread();
        }
    }
    
    protected void startThread()
    {
        for (Projectile p : proiettili) {
            p.startThread();
        }
    }
}
