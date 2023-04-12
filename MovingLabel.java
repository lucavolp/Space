import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.ArrayList;

/**
 * Se ti può servire come esempio per lavorarci questa classe fa una label come navicella e si muove
 */

public class MovingLabel extends JLabel implements KeyListener 
{
    private int posX = 0;
    private int posY = 0;
    private Image sps;
    private int velocitaProiettili = 10;
    
    public List<Projectile> proiettili;
    
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
        
        proiettili = new ArrayList<Projectile>();
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
    public void keyPressed(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();
        
        switch(keyCode) 
        {
            case KeyEvent.VK_LEFT: //Freccia sinistra
                posX -= 8;//8 da sostituire con una variabile per la velocità di spostamento
                setLocation(posX, posY);
                break;
                
            case KeyEvent.VK_RIGHT: //Freccia destra
                posX += 8;
                setLocation(posX, posY);
                break;
                
            case KeyEvent.VK_SPACE: //Spazio
                //Creazione nuovo proiettile
                nuovoProiettile();
                break;
        }
    }
    
    private void nuovoProiettile()
    {
        int x = this.getLocation().x;
        int y = this.getLocation().y;
        
        //Crea un nuovo proiettile e lo aggiunge alla lista
        Projectile pnew = new Projectile(x, y, velocitaProiettili, this); //Gli passo anche questa classe così ogni volta che viene eliminato un proiettile lo elimina dalla lista
        proiettili.add(0, pnew);
        
        Container parent = getParent(); // ottieni il pannello genitore
        pnew.setBounds(0 , 0, 40, 40);
        parent.add(pnew);
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
}
