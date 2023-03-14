/**
 * Speriam bene
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */

import java.awt.*;

public class Test {

    public static void init()
    {
        MyFrame f = new MyFrame("Risposte");
        Container c = f.getContentPane();
        MyPanel p = new MyPanel();
        c.add(p);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        init();
    }
}
