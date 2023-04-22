
/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;

public class AscoltatoreEsterno implements ActionListener {
    private MyPanel p;
    private MyPanelGioco panelGioco;
    private MyPanelScore panelScore;
    private MyPanelMenu panelMenu;
    private Dimension p1;
    private Dimension p2;
    private Dimension p3;
    
    //Thread per le collisioni
    Thread threadNavicella;
    Thread threadProiettili;

    public AscoltatoreEsterno(MyPanel p)// Viene usato dalla pagina principale
    {
        this.p = p;
    }

    public AscoltatoreEsterno() {
    } // costruttore vuoto normale

    public AscoltatoreEsterno(MyPanel p, MyPanelGioco pg, MyPanelScore ps, MyPanelMenu pm) // usato dal MyPanelMenu
    {
        this.p = p;
        panelGioco = pg;
        panelScore = ps;
        panelMenu = pm;
    }

    public void actionPerformed(ActionEvent e) {
        // Controlli schermata principale
        if (e.getActionCommand().equals("New Game")) 
        {
            avviaGioco();
        }
        if (e.getActionCommand().equals("Chiudi")) 
        {
            System.exit(0);
        }

        // Controlli pannello del menù
        if (e.getActionCommand().equals("Pausa")) 
        {
            panelGioco.setGamePause(true);
            
            panelMenu.isGamePaused = true;
            panelMenu.pause.setVisible(false);
            panelMenu.resume.setVisible(true);
        }
        if (e.getActionCommand().equals("Riprendi")) //Forse ho trovato la deprecatezza del metodo stop()
        {
            panelGioco.setGamePause(false);
            
            panelMenu.resume.setVisible(false);
            panelMenu.isGamePaused = false;
            panelMenu.pause.setVisible(true);
        }
        if (e.getActionCommand().equals("Restart")) 
        {

        }
        if (e.getActionCommand().equals("Torna al menù principale")) 
        {
            backHome();
        }
    }

    public void backHome() // Funziona e consente di tornare al pannello principale
    {
        Container container = panelGioco.getParent(); // questo prende il contenitore che contiene tutti gli elementi
                                                      // della pagina e da qui si possono rimuovere e aggiungere pagine
        container.removeAll();
        panelGioco = null;
        panelScore = null;
        panelMenu = null;
        // spaceship = null;
        container.add(p);
        container.revalidate();
        container.repaint();
    }
    
    public void setPanelSize()
    {
        float dimX;
        int dimY;
        int sizeA;
        int sizeB;
        int sizeC;
        
        
        dimX=Toolkit.getDefaultToolkit().getScreenSize().width;
        dimY=Toolkit.getDefaultToolkit().getScreenSize().height;
        
        sizeA=(int)Math.nextUp((0.16)*dimX);
        sizeB=(int)Math.nextUp((0.2)*dimX);
        sizeC=(int)(Math.nextUp(0.64)*dimX);
        /*
        p1=new Dimension(dimY,sizeA);
        p2=new Dimension(dimY,sizeC);
        p3=new Dimension(dimY,sizeB);
        */      
        
        p1=new Dimension(sizeA,dimY);
        p2=new Dimension(sizeC,dimY);
        p3=new Dimension(sizeB,dimY);
        
        /*
        System.out.println(sizeA);
        System.out.println(sizeB);
        System.out.println(sizeC);
        */
        //panelScore.setPreferredSize(new Dimension(300, 1080));  
    }

    public void avviaGioco() // Metodo che che crea la nuova finestra quando si preme new game
    {   
        Container f = p.getParent();
        
        panelGioco = new MyPanelGioco();
        panelScore = new MyPanelScore(panelGioco);
        panelMenu = new MyPanelMenu(panelGioco, panelScore, p);
        //-Spaceship spaceship = new Spaceship(); 
        setPanelSize();
        panelScore.setPreferredSize(p1);  
        panelGioco.setPreferredSize(p2);  
        panelMenu.setPreferredSize(p3);
        
        // Rimouve il pannello contenente il menù principale
        f.remove(p);
        
        // Imposta il layout per aggiungere i 3 pannelli
        f.setLayout(new BorderLayout());
        
        // Aggiunge i 3 pannelli
        f.add(panelGioco, BorderLayout.CENTER);
        f.add(panelMenu, BorderLayout.EAST);
        f.add(panelScore, BorderLayout.WEST);
        
        //Crea e avvia la classe che sta ad ascoltare se si verificano collisioni
        CollisionDetection cd = new CollisionDetection(panelGioco);
        cd.startThread();
        
        
          
        f.revalidate();
        f.repaint();
    }
}
