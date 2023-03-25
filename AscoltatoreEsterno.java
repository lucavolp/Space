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
    private MyFrame f;
    
    public AscoltatoreEsterno(MyPanel p, MyPanelGioco p2, MyPanelScore p3, MyPanelMenu p4, MyFrame f)//Viene usato dalla pagina principale
    {
        this.p = p;
        this.panelGioco = p2;
        this.panelScore = p3;
        this.panelMenu = p4;
        this.f = f;
    }
    
    public AscoltatoreEsterno(){}
    
    public AscoltatoreEsterno(MyPanelGioco p2)
    {
        this.panelGioco = p2;
    }
    
    public void actionPerformed(ActionEvent e)
    {   
        //Controlli schermata principale
        if(e.getActionCommand() == "New Game")
        {
            change();
        }
        if(e.getActionCommand() == "Chiudi")
        {
             System.exit(0);
        }
        
        //Controlli pannello del menù
        if(e.getActionCommand() == "Pausa")
        {
            panelGioco.timerGame.stop();
            panelGioco.timerMet.stop();
            panelGioco.meteoriti.stopTimer();
            //panelMenu.pause.(false);
            //System.out.println("Ho premuto il bottone");
        }
        if(e.getActionCommand() == "Riprendi")
        {
            panelGioco.timerGame.start();
            panelGioco.timerMet.start();
            panelGioco.meteoriti.startTimer();
        }
        if(e.getActionCommand() == "Restart")
        {
            
        }
        if(e.getActionCommand().equals("Torna al menù principale"))
        {
            backHome();
            System.out.println("Ho premuto il bottone");
        }
    }
    
    //Cambia pannello e lo switcha a quello contenente il gioco
    public void change()
    {
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
        //panelGioco.timerGame.stop();
    }
    
    
    public void backHome()
    {
        //Rimouve il pannello contenente il menù principale
        f.remove(panelGioco);
        
        f.add(p);
        
        f.revalidate();
        f.repaint();
        System.out.println("Ho premuto il bottone");
    }
}
