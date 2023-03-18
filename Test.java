/**
 * Speriam bene
 * Pushamoli raga
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;

public class Test {


    public static void main(String[] args) 
    {
        init();
    }
    
    
    
    public static MyFrame f;
    protected static MyPanel p;
    protected static MyPanel2 p2;

    public static void init()
    {
        f = new MyFrame("Main page");
        Container c = f.getContentPane();
        p = new MyPanel();
        c.add(p);
        f.setVisible(true);
        p2 = new MyPanel2();
    }
    
    public void change()
    {
        f.remove(p);
        f.add(p2);
        f.revalidate();
        f.repaint();
    }

   
}
