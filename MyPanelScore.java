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

public class MyPanelScore extends JPanel
{
    private MyPanelGioco pannelloGioco;
    private JLabel punteggio;
    
    public MyPanelScore(MyPanelGioco p2)
    {
        super();
        
        pannelloGioco = p2;
        
        punteggio = new JLabel("0");
        add(punteggio);
    }
}
