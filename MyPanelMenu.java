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
    private JLabel pause;
    
    public MyPanelMenu()
    {
        pause = new JLabel("Pause");
        add(pause);
    }
}
