/**
 * Speriam bene
 * 
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
    public static MyFrame f2;

    public static void init()
    {
        f = new MyFrame("Main page");
        Container c = f.getContentPane();
        MyPanel p = new MyPanel();
        c.add(p);
        f.setVisible(true);

        f2= new MyFrame("Space shooter");
        Container c2= f2.getContentPane();
        MyPanel2 p2= new MyPanel2();
        c2.add(p2);
        f2.setVisible(false);
    }

   
}
