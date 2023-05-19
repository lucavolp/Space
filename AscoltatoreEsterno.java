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

    public AscoltatoreEsterno(MyFrame f, MyPanel p, MyPanelGioco pg, MyPanelScore ps, MyPanelMenu pm) // usato dal MyPanelMenu
    {
        frame = f;
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
            replay();
        }
        if (e.getActionCommand().equals("Torna alla home")) 
        {
            backHome();
        }
        
        // Controlli pannello GameOver
        if (e.getActionCommand().equals("Home")) {
            GOBackToHome();                     
        }
        if (e.getActionCommand().equals("Rigioca")) {
            GOReplay();                     
        }
    }

    public void backHome() // Funziona e consente di tornare al pannello principale
    {
        Container container = panelMenu.getParent(); // questo prende il contenitore che contiene tutti gli elementi della pagina e da qui si possono rimuovere e aggiungere pagine
        container.removeAll();
        panelGioco.setGameStatus(true); 
        panelScore.salvaHighScore();
        panelGioco = null;
        panelScore = null;
        panelMenu = null;
        
        container.add(p);
        container.revalidate();
        container.repaint();    
    }
    
    public void replay()
    {
        panelMenu.back.doClick();
        p.change.doClick();
    }
    
    
    //Metodi invocati dal pannello GameOver
    public void GOBackToHome() // Funziona e consente di tornare al pannello principale
    {
        /*
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
        }*/
        panelMenu.back.doClick();
    }
    
    public void GOReplay()
    {
        panelMenu.restart.doClick();
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
        p.salvaUser();
        
        Container f = p.getParent();
        
        panelMenu = new MyPanelMenu(frame, p); //certo che è null
        panelGioco = new MyPanelGioco(frame, p, panelMenu);
        panelScore = new MyPanelScore(panelGioco);
        
        panelMenu.setPanelScore(panelScore);
        panelMenu.setPanelGioco(panelGioco);
        
        //Passa al MyPanelGioco il pannello con le Score così da poterlo gestire
        panelGioco.setPanelScore(panelScore);
        
        setPanelSize();
        panelScore.setPreferredSize(p1);  
        panelGioco.setPreferredSize(p2);  
        panelMenu.setPreferredSize(p3);
        
        panelScore.caricaUser();
        
        // Rimouve il pannello contenente il menù principale
        f.remove(p); //Questa f è null
        //p=null;
        //System.out.println(p.isVisible());
        // Imposta il layout per aggiungere i 3 pannelli
        f.setLayout(new BorderLayout());
        
        // Aggiunge i 3 pannelli
        
        f.add(panelMenu, BorderLayout.EAST);
        f.add(panelScore, BorderLayout.WEST);
        f.add(panelGioco, BorderLayout.CENTER);
          
        f.revalidate();
        f.repaint();
        
        
        /*
        panelMenu.pause.doClick();
        
        try {
            // Creazione dell'oggetto Robot
            Robot robot = new Robot();

            // Simulazione del click sulla finestra del programma Java
            robot.mouseMove(0,0);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        
        
        panelMenu.resume.doClick();
        //System.out.println("x: "+panelMenu.pause.getLocationOnScreen().x+"\ny: "+panelMenu.pause.getLocationOnScreen().y);
        //panelMenu.resume.doClick();*/
        
        panelGioco.roberto.requestFocusInWindow();
    }
}
