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
    private JLabel score;
    
    public MyPanelScore()
    {
        score = new JLabel("Score");
        add(score);
    }
}
