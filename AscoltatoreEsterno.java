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
    
    public AscoltatoreEsterno(MyPanel p)//Viene usato dalla pagina principale
    {
        this.p = p;
        /*panelGioco = p2;
        panelScore = p3;
        panelMenu = p4;*/
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
            //change();
            avviaGioco();
        }
        if(e.getActionCommand().equals("Chiudi"))
        {
             System.exit(0);
        }
        
        //Controlli pannello del menù
        if(e.getActionCommand().equals("Pausa"))
        {
            panelGioco.stopTimer();
            panelGioco.meteoriti.stopTimer();
            //panelMenu.pause.(false);
            //System.out.println("Ho premuto il bottone");
        }
        if(e.getActionCommand().equals("Riprendi"))
        {
            panelMenu.resume.setEnabled(false);
            panelMenu.pause.setEnabled(true);
            panelGioco.timerGame.start();
            panelGioco.timerMet.start();
            panelGioco.meteoriti.startTimer();
        }
        if(e.getActionCommand().equals("Restart"))
        {
            
        }
        if(e.getActionCommand().equals("Torna al menù principale"))
        {
            backHome();            
        }
    }
    
    //Cambia pannello e lo switcha a quello contenente il gioco
    /*public void change()
    {
        Container f = p.getParent();
        //Rimouve il pannello contenente il menù principale
        f.remove(p);
        //Imposta il layout per aggiungere i 3 pannelli
        f.setLayout(new BorderLayout());
        //Aggiunge i 3 pannelli
        f.add(panelGioco, BorderLayout.CENTER);
        f.add(panelMenu, BorderLayout.EAST);
        f.add(panelScore, BorderLayout.WEST);
        
        f.revalidate();
        f.repaint();
        panelGioco.timerGame.start();
        panelGioco.timerMet.start();
    }*/
    
    
    public void backHome() //Funziona e consente di tornare al pannello principale
    {
        Container container = panelGioco.getParent(); // questo prende il contenitore che contiene tutti gli elementi della pagina e da qui si possono rimuovere e aggiungere pagine
        container.removeAll();
        panelGioco.stopTimer(); //dato che non li elimina richiama il metodo stopTimer() almeno smette di generare robe e contare punti
        panelGioco = null; //dovrebbe eliminare i pannelli però non li elimina haha siummm
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
        
        Container f = p.getParent();
        //Rimouve il pannello contenente il menù principale
        f.remove(p);
        //Imposta il layout per aggiungere i 3 pannelli
        f.setLayout(new BorderLayout());
        //Aggiunge i 3 pannelli
        f.add(panelGioco, BorderLayout.CENTER);
        f.add(panelMenu, BorderLayout.EAST);
        f.add(panelScore, BorderLayout.WEST);
        
        
        panelGioco.spaceship = new Spaceship();
        f.add(panelGioco.spaceship, BorderLayout.PAGE_END);
        panelGioco.spaceship.setFocusable(true);
        
        f.revalidate();
        f.repaint();
        panelGioco.timerMet.start();
    }
}
