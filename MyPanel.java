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
import javax.imageio.*;

public class MyPanel extends JPanel //implements ActionListener 
{
    private MyFrame frame;
    private JLabel lillo;   //Label vuota per impaginazione
    private JLabel lillo2;  //Label vuota per impaginazione
    private JLabel lillo3;  //Label vuota per impaginazione
    private JLabel user;  //Label vuota per scritta nome utente
    public JButton change;
    private JButton chiudi;
    public AscoltatoreEsterno as;
    
    //Sfondo
    private Image backgroundImage;
    
    private Font font;
    
    private JTextField userInput;
    
    public MyPanel(MyFrame f)
    {
        try {
            backgroundImage = ImageIO.read(new File("img/backgroundHome.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("erorreImmagine!!!!!!!!!!!");
        }
        
        //Impostazione font Astro
        try
        {
            try
            { 
                font = Font.createFont(Font.TRUETYPE_FONT, new File("font/astro/Futuristic Font/Astro.ttf"));
            }
            catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
        catch (FontFormatException ffe)
        {
            ffe.printStackTrace();
        }
        
        frame = f;
        as= new AscoltatoreEsterno(this, frame);
        lillo = new JLabel("       ");
        lillo2 = new JLabel("       ");
        
        user = new JLabel("Nome utente ");
        user.setFont(font.deriveFont(15f));
        user.setForeground(Color.WHITE);
        
        userInput = new JTextField("");
        userInput.setOpaque(false);
        userInput.setBackground(null);
        userInput.setFont(font.deriveFont(15f));
        userInput.setForeground(Color.WHITE);
        
        lillo3 = new JLabel("       ");
        
        change = new JButton("New Game");
        change.addActionListener(as);
        change.setFont(font.deriveFont(20f));
        chiudi = new JButton("Chiudi");
        chiudi.addActionListener(as);
        chiudi.setFont(font.deriveFont(20f));
        
        //Impostazione layout
        setLayout(new GridBagLayout());
        
        lillo.setPreferredSize(new Dimension(300,50));
        lillo2.setPreferredSize(new Dimension(300,120));
        userInput.setPreferredSize(new Dimension(300,50));
        lillo3.setPreferredSize(new Dimension(300,50));
        change.setPreferredSize(new Dimension(300,50));
        chiudi.setPreferredSize(new Dimension(300,50));
        
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        add(lillo2, c);
        
        c.gridy = 1;
        add(user, c);
        c.gridx= 1;
        add(userInput, c);
        
        c.gridy = 2;       
        add(lillo3, c);
        
        c.gridy = 3;
        c.gridx = 0;
        add(change, c);
        
        c.gridy = 3;
        c.gridx = 1;
        add(lillo, c);
        
        c.gridy = 3;
        c.gridx = 2;
        add(chiudi, c);
        
        change.setDefaultCapable(false);
        chiudi.setDefaultCapable(false);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    
    public void salvaUser()
    {
        String name;
        
        name = userInput.getText();
        
        if(name.isEmpty())
            name = "iuser";
            
        try {
            File file = new File("save/punteggi.txt");

            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true); // Apri il file in modalità append
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.newLine(); // Vai a una nuova riga
                bufferedWriter.write(name);

                bufferedWriter.close();
                System.out.println("Punteggio salvato in append nel file esistente.");
            } else {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(name);

                bufferedWriter.close();
                System.out.println("Punteggio salvato in un nuovo file.");
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante il salvataggio del punteggio nel file: " + e.getMessage());
        }
        
        userInput.setText("");
    }
}