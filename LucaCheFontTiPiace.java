import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class LucaCheFontTiPiace
{
    public static Color generaColoreCasuale() 
    {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r, g, b);
    }
    
    public static void main(String[] args) throws FontFormatException, java.io.IOException {
        // Crea un nuovo frame
        JFrame frame = new JFrame("Prova Font");

        // Imposta il layout del frame
        frame.setLayout(new GridLayout(10, 1));
        Font font = new Font("Arial", Font.BOLD, 16);
        // Crea 5 oggetti JLabel
        JLabel label1 = new JLabel("Prova questo font");
        JLabel label2 = new JLabel("Prova questo font");
        JLabel label3 = new JLabel("Prova questo font");
        JLabel label4 = new JLabel("Prova questo font");
        JLabel label5 = new JLabel("Prova questo font");
        JLabel label6 = new JLabel("Prova questo font");
        JLabel label7 = new JLabel("Prova questo font");
        JLabel label8 = new JLabel("Prova questo font");
        JLabel label9 = new JLabel("Prova questo font");

        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/nova/SYNNova-Normal.otf"));
        label1.setFont(font.deriveFont(20f));
        
        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/alien/TrueType/SFAlienEncounters.ttf"));
        label2.setFont(font.deriveFont(20f));
        
        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/pinscher/SHPinscher-Regular.otf"));
        label3.setFont(font.deriveFont(20f));
        
        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/silkscreen/slkscr.ttf"));
        label4.setFont(font.deriveFont(20f));
        
        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/pressStart/PressStart2P-Regular.ttf"));
        label5.setFont(font.deriveFont(20f));
        
        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/arcadeClassic/ARCADECLASSIC.TTF"));
        label6.setFont(font.deriveFont(20f));
        
        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/astro/Futuristic Font/Astro.ttf"));
        label7.setFont(font.deriveFont(20f));
        
        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/neuropolx/neuropol x rg.otf"));
        label8.setFont(font.deriveFont(20f));
        
        font = Font.createFont(Font.TRUETYPE_FONT, new File("font/orbitron/Orbitron-VariableFont_wght.ttf"));
        label9.setFont(font.deriveFont(20f));

        // Imposta il colore del testo per ogni JLabel
        label1.setForeground(generaColoreCasuale());
        label2.setForeground(generaColoreCasuale());
        label3.setForeground(generaColoreCasuale());
        label4.setForeground(generaColoreCasuale());
        label5.setForeground(generaColoreCasuale());
        label6.setForeground(generaColoreCasuale());
        label7.setForeground(generaColoreCasuale());
        label8.setForeground(generaColoreCasuale());
        label9.setForeground(generaColoreCasuale());

        // Aggiungi i JLabel al frame
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        frame.add(label6);
        frame.add(label7);
        frame.add(label8);
        frame.add(label9);

        // Imposta la dimensione del frame
        frame.setSize(400, 600);

        // Imposta il comportamento di chiusura del frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Visualizza il frame
        frame.setVisible(true);
    }
}