/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class AscoltatoreEsterno implements ActionListener
{
    private MyPanel p;
    private MyPanelGioco panelGioco;
    private MyPanelScore panelScore;
    private MyPanelMenu panelMenu;
    private Spaceship spaceship;
    
    
    public AscoltatoreEsterno(MyPanel p)//Viene usato dalla pagina principale
    {
        this.p = p;
    }
    
    public AscoltatoreEsterno(){}   //costruttore vuoto normale
    
    public AscoltatoreEsterno(MyPanel p, MyPanelGioco pg, MyPanelScore ps, MyPanelMenu pm)  //usato dal MyPanelMenu
    { 
        this.p = p;
        panelGioco = pg;
        panelScore = ps;
        panelMenu = pm;
    }
    
    public void actionPerformed(ActionEvent e)
    {   
        //Controlli schermata principale
        if(e.getActionCommand().equals("New Game"))
        {
            avviaGioco();
        }
        if(e.getActionCommand().equals("Chiudi"))
        {
             System.exit(0);
        }
        
        //Controlli pannello del menù
        if(e.getActionCommand().equals("Pausa"))
        {
            panelGioco.stopThread();
            panelMenu.isGamePaused=true;
            panelMenu.pause.setVisible(false);
            panelMenu.resume.setVisible(true);
        }
        if(e.getActionCommand().equals("Riprendi"))
        {
            panelMenu.resume.setVisible(false);
            //panelGioco.startThread(); -----------------------------------------------------------da errore perche startThread non so se si possa fare così
            panelMenu.isGamePaused=false;
            panelMenu.pause.setVisible(true);
        }
        if(e.getActionCommand().equals("Restart"))
        {
            
        }
        if(e.getActionCommand().equals("Torna al menù principale"))
        {
            backHome();            
        }
    }    
    
    public void backHome() //Funziona e consente di tornare al pannello principale
    {
        Container container = panelGioco.getParent(); // questo prende il contenitore che contiene tutti gli elementi della pagina e da qui si possono rimuovere e aggiungere pagine
        container.removeAll();
        panelGioco = null;
        panelScore = null;
        panelMenu = null;
        container.add(p);
        container.revalidate();
        container.repaint();
    }
    
    public void avviaGioco() //La funzione che ti dicevo che crea la nuova finestra quando si preme new game
    {
        panelGioco = new MyPanelGioco();
        panelScore = new MyPanelScore(panelGioco);
        panelMenu = new MyPanelMenu(panelGioco, panelScore, p);
        spaceship = new Spaceship();
        
        Container f = p.getParent();
        //Rimouve il pannello contenente il menù principale
        f.remove(p);
        //Imposta il layout per aggiungere i 3 pannelli
        f.setLayout(new BorderLayout());
        //Aggiunge i 3 pannelli
        f.add(panelGioco, BorderLayout.CENTER);
        f.add(panelMenu, BorderLayout.EAST);
        f.add(panelScore, BorderLayout.WEST);
        //Aggiunge il pannello con la navicella
        f.add(spaceship, BorderLayout.PAGE_END);
        
        spaceship.setFocusable(true);
        spaceship.grabFocus();
        if(spaceship.isFocusOwner())
            System.out.println("focus");

        f.revalidate();
        f.repaint();
    }
}
