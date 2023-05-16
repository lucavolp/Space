
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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class AscoltatoreEsterno implements ActionListener {
    private MyPanel p;
    private MyPanelGioco panelGioco;
    private MyPanelScore panelScore;
    private MyPanelMenu panelMenu;
    private MyFrame frame;
    private Dimension p1;
    private Dimension p2;
    private Dimension p3;

    public AscoltatoreEsterno(MyPanel ph, MyFrame f)// Viene usato dalla pagina principale
    {
        this.p = ph;
        frame = f;
    }
    
    public AscoltatoreEsterno(MyPanel ph, MyFrame f,MyPanelMenu pm)// Viene usato dalla pagina principale
    {
        this.p = ph;
        frame = f;
        panelMenu=pm;
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
            
            panelMenu.pause.setVisible(false);
            panelMenu.resume.setVisible(true);
        }
        if (e.getActionCommand().equals("Riprendi")) //Forse ho trovato la deprecatezza del metodo stop()
        {
            panelGioco.setGamePause(false);
            
            panelMenu.resume.setVisible(false);
            panelMenu.pause.setVisible(true);
        }
        if (e.getActionCommand().equals("Restart")) 
        {
            
        }
        if (e.getActionCommand().equals("Torna alla home")) 
        {
            backHome();
        }
        if(e.getActionCommand().equals("Home"))
        {
            backToHome();
        }
        // Controlli pannello GameOver
        if (e.getActionCommand().equals("Home")) 
        {
            //backHome();
            avviaGioco();
        }
    }

    public void backHome() // Funziona e consente di tornare al pannello principale
    {
        Container container = panelMenu.getParent(); // questo prende il contenitore che contiene tutti gli elementi della pagina e da qui si possono rimuovere e aggiungere pagine
        container.removeAll();
        panelGioco = null;
        panelScore = null;
        panelMenu = null;
        
        container.add(p);
        container.revalidate();
        container.repaint();    
    }
    
    public void backToHome() // Funziona e consente di tornare al pannello principale
    {
        try {
            // Creazione dell'oggetto Robot
            Robot robot = new Robot();

            // Simulazione del click sulla finestra del programma Java
            robot.mouseMove(this.panelMenu.back.getBounds().x+5, this.panelMenu.back.getBounds().x+5);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            System.out.println("ciao");
        } catch (AWTException e) {
            e.printStackTrace();
        }  
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
        
        sizeA=(int)(Math.nextUp((0.19)*dimX));
        sizeB=(int)(Math.nextUp((0.17)*dimX));
        sizeC=(int)(Math.nextUp(0.64)*dimX);
        
        p1=new Dimension(sizeA,dimY);
        p2=new Dimension(sizeC,dimY);
        p3=new Dimension(sizeB,dimY);
    }

    public void avviaGioco() // Metodo che che crea la nuova finestra quando si preme new game
    {   
        Container f = p.getParent();
        
        panelGioco = new MyPanelGioco(frame, p,panelMenu);
        panelScore = new MyPanelScore(panelGioco);
        panelMenu = new MyPanelMenu(panelGioco, panelScore, p);
        
        //Passa al MyPanelGioco il pannello con le Score così da poterlo gestire
        panelGioco.setPanelScore(panelScore);
        
        setPanelSize();
        panelScore.setPreferredSize(p1);  
        panelGioco.setPreferredSize(p2);  
        panelMenu.setPreferredSize(p3);
        
        // Rimouve il pannello contenente il menù principale
        f.remove(p); //Questa f è null
        
        // Imposta il layout per aggiungere i 3 pannelli
        f.setLayout(new BorderLayout());
        
        // Aggiunge i 3 pannelli
        f.add(panelGioco, BorderLayout.CENTER);
        f.add(panelMenu, BorderLayout.EAST);
        f.add(panelScore, BorderLayout.WEST);
        
          
        f.revalidate();
        f.repaint();
    }
}
