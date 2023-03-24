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
        
        MyPanelGioco panelGioco = new MyPanelGioco();
        MyPanelScore panelScore = new MyPanelScore(panelGioco);
        MyPanelMenu panelMenu = new MyPanelMenu(panelGioco);
        
        MyPanel p = new MyPanel(panelGioco, panelScore, panelMenu, frame);
        
        c.add(p);
        frame.setVisible(true);   
    }   
}
