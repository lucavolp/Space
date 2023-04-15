
/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AscoltatoreEsterno implements ActionListener {
    private MyPanel p;
    private MyPanelGioco panelGioco;
    private MyPanelScore panelScore;
    private MyPanelMenu panelMenu;
    
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
        if (e.getActionCommand().equals("New Game")) {
            avviaGioco();
        }
        if (e.getActionCommand().equals("Chiudi")) {
            System.exit(0);
        }

        // Controlli pannello del menù
        if (e.getActionCommand().equals("Pausa")) {
            panelGioco.stopThread();
            panelMenu.isGamePaused = true;
            panelMenu.pause.setVisible(false);
            panelMenu.resume.setVisible(true);
            panelGioco.roberto.stopAllThread();
        }
        if (e.getActionCommand().equals("Riprendi")) {
            panelMenu.resume.setVisible(false);
            panelGioco.startThread(); // -----------------------------------------------------------da errore perche
                                      // startThread non so se si possa fare così| ora dovrebbe funzionare
            panelMenu.isGamePaused = false;
            panelMenu.pause.setVisible(true);
        }
        if (e.getActionCommand().equals("Restart")) {

        }
        if (e.getActionCommand().equals("Torna al menù principale")) {
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

    public void avviaGioco() // Metodo che che crea la nuova finestra quando si preme new game
    {   
        Container f = p.getParent();
        
        panelGioco = new MyPanelGioco();
        panelScore = new MyPanelScore(panelGioco);
        panelMenu = new MyPanelMenu(panelGioco, panelScore, p);
        //-Spaceship spaceship = new Spaceship();        
        
        // Rimouve il pannello contenente il menù principale
        f.remove(p);
        
        // Imposta il layout per aggiungere i 3 pannelli
        f.setLayout(new BorderLayout());
        
        // Aggiunge i 3 pannelli
        f.add(panelGioco, BorderLayout.CENTER);
        f.add(panelMenu, BorderLayout.EAST);
        f.add(panelScore, BorderLayout.WEST);
        
        // Aggiunge il pannello con la navicella
        //-f.add(spaceship, BorderLayout.PAGE_END);

        // Setta il focus sul pannello della navicella
        //spaceship.setFocusable(true);
        //spaceship.grabFocus();
        //panelGioco.setFocusable(true);
        //panelGioco.grabFocus();
        
        //Crea e avvia la classe che sta ad ascoltare se si verificano collisioni
        CollisionDetection cd = new CollisionDetection(panelGioco);
        cd.startThread();
        /*
        //Creo due Runnable differenti così riesco ad eseguire tramite due thread differenti due metodi della stessa classe
        Runnable runnableNavicella = new Runnable(){
            public void run(){
                cd.collisioniNavicella();
            }
        };
        threadNavicella = new Thread(runnableNavicella, "Collisioni navicella");
        threadNavicella.start();
        
        Runnable runnableProiettili = new Runnable(){
            public void run(){
                cd.collisioniProiettili();
            }
        };
        threadProiettili = new Thread(runnableProiettili, "Collisioni proiettili");
        threadProiettili.start();*/
        
        f.revalidate();
        f.repaint();
    }
}
