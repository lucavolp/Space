import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Se ti può servire come esempio per lavorarci questa classe fa una label come navicella e si muove
 */

public class MovingLabel extends JLabel implements KeyListener {
    private static final long serialVersionUID = 1L;
    private int posX = 0;
    private int posY = 0;
    private Image sps;
    
    public MovingLabel() {
        super();
        addKeyListener(this);
        setFocusable(true);
        
        setPosizioneGenerazione();
        setBounds(posX, posY, 120, 120);
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("img/spaceship.png"));
            sps = bufferedImage.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        }catch(IOException e) 
        { 
          e.printStackTrace();
        }
        
        setIcon(new ImageIcon(sps));
    }
    
    private void setPosizioneGenerazione()//Setta la posizione al centro dello schermo al primo lancio della partita
    {
        int dimX=Toolkit.getDefaultToolkit().getScreenSize().width;//Prende la larghezza dello schermo
        int dimY=Toolkit.getDefaultToolkit().getScreenSize().height;//Prende l'altezza dello schermo
        
        //non mi funzionano perchè come posizione dello schermo mi prende 3000x1990
        posX=(dimX/2)-50;  //posiziona partenza al centro dello schermo
        posY=dimY; //posiziona un po' staccata dal fondo
        
        posX= 880;
        posY= 890;
    }
    
    //Gestione pressione tasti
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
                posX -= 8;
                setLocation(posX, posY);
                break;
            case KeyEvent.VK_RIGHT:
                posX += 8;
                setLocation(posX, posY);
                break;
        }
    }
    
    public void keyReleased(KeyEvent e) {
    }
    
    public void keyTyped(KeyEvent e) {
    }
    
    public Rectangle boundsNavicella()
    {
        return this.getBounds();
    }
}
