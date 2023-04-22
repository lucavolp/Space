/**
 * Aggiungi qui una descrizione della classe GameOver
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel
{
    
    public GameOver()
    {
        super();
        this.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setOpaque(false);
        
        JLabel pippo = new JLabel("pippo");
        add(pippo);
    }
}
