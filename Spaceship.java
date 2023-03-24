/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0) / ormai troppe
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class Spaceship extends JLabel
{
    private Point labelLocation; 
    private Timer timer;
    private Timer timer2;
    private int posY= 0;   //posizione Y attuale spaceship
    private int posX= 0;   //posizione X attuale spaceship 
    private int spaceshipSpeed = 0;
    private int dimX;   //dimensione X schermo
    private int dimY;   //dimensione Y schermo
    private int spaceshipX;
    private int spaceshipY;
    private Image sps;
    private int incr = 5;    
    private int msecInVel=10000;
    
    public Spaceship() 
    {
        this.setLocation(posX, posY);
        setPosizioneGenerazione();
        
        
        
        //Inserimento e ridimensionamento dell'immagine
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("img/spaceship.jpg"));
            sps = bufferedImage.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        }catch(IOException e) 
        { 
          e.printStackTrace();
        }
        
        this.setIcon(new ImageIcon(sps));
        
        
        this.setLocation(posX, posY);
        //timer di frequenza di ascolto degli input
        timer = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
            }            
        });
        timer.start();
        
        
        
        
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    spaceshipSpeed = -(incr);
                    //System.out.println("Sinistra");     //DEBUG
                    spaceshipX += spaceshipSpeed;
                    move();
                    setBounds(posX, posY,80,80);
                    //saveX=posX;
                    //saveY=posY;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    spaceshipSpeed = incr;
                    //System.out.println("Destra");     //DEBUG
                    spaceshipX += spaceshipSpeed;
                    move();
                    setLocation(posX, posY);
                    //saveX=posX;
                    //=posY;
                }
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                    spaceshipSpeed = 0;
                }
            }
        });
        this.setFocusable(true);
        
    }
    
    private void setPosizioneGenerazione()//Setta la posizione al centro dello schermo al primo lancio della partita
    {
        
        
        dimX=Toolkit.getDefaultToolkit().getScreenSize().width;//Prende la larghezza dello schermo
        dimY=Toolkit.getDefaultToolkit().getScreenSize().height;//Prende la larghezza dello schermo
        
        posX=(dimX/2)-40;  //posiziona partenza al centro dello schermo
        posY=dimY-200; //posiziona un po' staccata dal fondo
        
    }
    
    private void move()
    {
        if((posX>0)&&(posX<dimX-80))
            posX+=spaceshipSpeed;
        else if((spaceshipSpeed<0)&&(posX>=dimX-80))
            posX+=spaceshipSpeed;
        else if((spaceshipSpeed>0)&&(posX==0))
            posX+=spaceshipSpeed;            
    }
    
    //ogni secInVel aumenta la velocità di movimento [incr] di 1 (aumenta la fluidità in accordo con più meteoriti spawnati)
    private void incrVelocita()
    {
        timer2 = new Timer(msecInVel, new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt) 
            {
               incr++;
               System.out.println(incr);
            }                    
        });
        timer2.start();
    }
    
}
    
    

