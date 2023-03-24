/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MyPanelMenu extends JPanel
{
    private MyPanelGioco pannelloGioco;
    
    private JButton pause; // mette in pausa il gioco, ferma il timer e gli asteroidi
    private JButton resume; //toglie il gioco dalla pausa
    private JButton restart; // resetta il punteggio e ricomincia una nuova partita
    private JButton back; // torna al menù principale
    private AscoltatoreEsterno as;
    
    
    public MyPanelMenu(MyPanelGioco p)
    {
        super();
        
        pannelloGioco = p;
        
        as = new AscoltatoreEsterno(pannelloGioco);
        
        pause = new JButton("Pausa");
        pause.addActionListener(as);
        
        resume = new JButton("Riprendi");
        resume.addActionListener(as);
        
        restart = new JButton("Restart");
        restart.addActionListener(as);
        
        back = new JButton("Torna al menù principale");
        back.addActionListener(as);
        
        
        // Crea un nuovo GridBagLayout e imposta il layout del pannello su di esso
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        
        // Crea un oggetto GridBagConstraints per impostare le proprietà di posizionamento dei bottoni
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10); // Aggiunge uno spazio vuoto intorno ai bottoni
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        
        layout.setConstraints(pause, c);
        
        c.gridy = 1;
        layout.setConstraints(resume, c);
        
        c.gridy = 2;
        layout.setConstraints(restart, c);
        
        c.gridy = 3;
        layout.setConstraints(back, c);
        
        // Aggiunge i bottoni al pannello
        add(pause);
        add(resume);
        add(restart);
        add(back);
    }
}

