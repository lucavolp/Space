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
        MyFrame frame = new MyFrame("Space"); 
        Container c = frame.getContentPane();
        
        
        
        MyPanel p = new MyPanel();
        
        c.add(p);
        frame.setVisible(true);   
    }   
}
