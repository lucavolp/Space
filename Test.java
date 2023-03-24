/**
 * Speriam bene
 * Pushamoli raga
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;

public class Test 
{
    public static void main(String[] args) 
    {
        //Creazione del frame principale
        MyFrame f = new MyFrame("Space"); 
        Container c = f.getContentPane();
        
        MyPanelGioco p2 = new MyPanelGioco();
        
        MyPanel p = new MyPanel(p2, f);
        c.add(p);
        f.setVisible(true);        
    }   
}
