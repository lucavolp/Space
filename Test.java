/**
 * Speriam bene
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */


import java.awt.*;
import javax.swing.*;

public class Test {
    
    public static void main(String[] args) {
        MyFrame f=new MyFrame("SPACE SHOOTER");
        Container c=f.getContentPane();
        MyPanel p=new MyPanel();
        c.add(p); 
        f.setVisible(true);
    }
}
