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

import java.util.List;
import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Spaceship extends JPanel implements KeyListener
{
    private Point labelLocation; 
    private Timer timer;
    private Timer timer2;
    public int posY= 0;   //posizione Y attuale spaceship
    public int posX= 0;   //posizione X attuale spaceship 
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
    
    
    private List<Projectile> projectiles;
    
    
    public Spaceship() 
    {
        //this.setLocation(posX, posY);
        super();
        setPosizioneGenerazione();
        imm=new JLabel();
        
        
        //setLayout(null);
        
        
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
        
        
        
        /*
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) 
            {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) 
                {
                    spaceshipSpeed = -(incr);
                    System.out.println("Sinistra");     //DEBUG
                    spaceshipX += spaceshipSpeed;
                    move();
                    imm.setLocation(posX, posY);
                    saveX=posX;
                    saveY=posY;
                } else if (keyCode == KeyEvent.VK_RIGHT) 
                {
                    spaceshipSpeed = incr;
                    System.out.println("Destra");     //DEBUG
                    spaceshipX += spaceshipSpeed;
                    move();
                    imm.setLocation(posX, posY);
                    saveX=posX;
                    saveY=posY;
                } else if (keyCode == KeyEvent.VK_UP) {
                    //Projectile projectiles = new Projectile(posX,posY,15);       //chatgpt dice di fare così e con la classe in piu
                }
                System.out.println("Destra"); 
            }

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
                    spaceshipSpeed = 0;
                }
            }
        });
        //this.setFocusable(true);
        */
       
        this.setFocusable(true);


        this.addKeyListener(this); 
        
    }
    
    private void setPosizioneGenerazione()//Setta la posizione al centro dello schermo al primo lancio della partita
    {
        
        
        dimX=Toolkit.getDefaultToolkit().getScreenSize().width;//Prende la larghezza dello schermo
        dimY=Toolkit.getDefaultToolkit().getScreenSize().height;//Prende l'altezza dello schermo
        
        posX=(dimX/2)-50;  //posiziona partenza al centro dello schermo
        posY=dimY; //posiziona un po' staccata dal fondo
        
    }
    
    private void move()    //fa fare l'azione di movimento solo se si è all'interno dello spazio corretto
    {
        if((posX>0)&&(posX<dimX-80))
            posX+=spaceshipSpeed;
        else if((spaceshipSpeed<0)&&(posX>=dimX-80))
            posX+=spaceshipSpeed;
        else if((spaceshipSpeed>0)&&(posX==0))
            posX+=spaceshipSpeed;            
    }

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
    
    
    
     public void keyTyped(KeyEvent e) {
        imm.setBounds(0,0,80,80);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        
        Projectile pr;
        projectiles=new ArrayList<Projectile>();
        
        
        
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pr= new Projectile(posX,posY,15);
            projectiles.add(pr);
            setVisible(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Freccia Giu");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            spaceshipSpeed = -(incr);
            //System.out.println("Sinistra");     //DEBUG
            spaceshipX += spaceshipSpeed;
            move();
            imm.setBounds(posX, posY,100,100);
            saveX=posX;
            saveY=posY;
            //imm.setBounds(0,0,80,80);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            spaceshipSpeed = incr;
            //System.out.println("Destra");     //DEBUG
            spaceshipX += spaceshipSpeed;
            move();
            imm.setBounds(posX, posY,100,100);
            saveX=posX;
            saveY=posY;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
            spaceshipSpeed = 0;        
    }
    
    public double getPX()
    {
        return (double)posX;
    }
    
    public double getPY()
    {
        return (double)posY;
    }
    
    //metodo che mi serve per le collisioni
    public Rectangle boundsNavicella()
    {
        return imm.getBounds();
    }
    /*
    public void setText(String s){
        test = s;
    }
    
    public String getTest(){
        return test;
    }*/
}
    

