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
import java.util.ArrayList;

public class Spaceship extends JPanel
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
    private int saveX;
    private int saveY;
    
    private JLabel imm;
    
    public Spaceship() 
    {
        //this.setLocation(posX, posY);
        setPosizioneGenerazione();
        imm=new JLabel();
        
        
        //Inserimento e ridimensionamento dell'immagine
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("img/spaceship.png"));
            sps = bufferedImage.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        }catch(IOException e) 
        { 
          e.printStackTrace();
        }
        
        imm.setIcon(new ImageIcon(sps));
        
        add(imm);
        imm.setLocation(posX, posY);
        
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
                    System.out.println("Sinistra");     //DEBUG
                    spaceshipX += spaceshipSpeed;
                    move();
                    imm.setLocation(posX, posY);
                    saveX=posX;
                    saveY=posY;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    spaceshipSpeed = incr;
                    System.out.println("Destra");     //DEBUG
                    spaceshipX += spaceshipSpeed;
                    move();
                    imm.setLocation(posX, posY);
                    saveX=posX;
                    saveY=posY;
                } else if (keyCode == KeyEvent.VK_UP) {
                    //List<Projectile> projectiles = new ArrayList<Projectile>();       //chatgpt dice di fare così e con la classe in piu
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
        posY=dimY; //posiziona un po' staccata dal fondo
        
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
    
    public void riposiziona()
    {
        setLocation(saveX, saveY);
    }
}
    
    

