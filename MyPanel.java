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
        String name = userInput.getText();
        
        if(name.isEmpty())
            name = "iuser";
            
        try 
        {
            File file = new File("save/punteggi.txt");

            if (file.exists()) //Se la trova, prende i dati e li mette in una nuova linea
            {
                //Cerca nel file se il nome utente esiste già e quindi prende le sue top score
                //Crea un reader per leggere il contenuto del file
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                
                String line;
                ArrayList<String> fileLine = new ArrayList<>();
                boolean trovato = false;
                
                while ((line = bufferedReader.readLine()) != null)
                {
                    //if(line.equals(name) && !line.equals("iuser"))
                    fileLine.add(line);//la mia lista contiene tutte le righe del file
                }
                bufferedReader.close();
                
                for(int i = 0; i < fileLine.size(); i++)
                {
                    String[] parti = fileLine.get(i).split(";"); //Divide la stringa presa in due parti così da poter controllare il nome
                    if(parti[0].equals(name) && !name.equals("iuser")) //Significa che è stato trovato lo stesso utente
                    {
                        fileLine.add(fileLine.get(i)); //Aggiunge alla fine la riga con l'utente che è stata trovata
                        fileLine.remove(i);
                        trovato = true;
                        //System.out.println("Trovato");
                    }
                    
                    if(parti[0].equals("iuser"))
                        fileLine.remove(i);
                }
                
                if(!trovato)
                {
                    //System.out.println("Aggiunto");
                    fileLine.add(name + ";0");
                }
                
                //Scrive nel file il nome utente
                FileWriter fileWriter = new FileWriter(file); // true per aprire il file in modalità append
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                
                for(int i = 0; i < fileLine.size(); i++) //Carica tutta la lista nel file
                {
                    //if(!fileLine.equals("iuser")) //Se non è l'utente senza nome
                        bufferedWriter.write(fileLine.get(i));
                    if(i+1 < fileLine.size()) //se non è all'ultima riga va a capo
                        bufferedWriter.newLine(); // Vai a una nuova riga
                }

                bufferedWriter.close();
                //System.out.println("Punteggio salvato in append nel file esistente.");
            } 
            else 
            {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                
                bufferedWriter.write(name + ";0");

                bufferedWriter.close();
                
                //System.out.println("Punteggio salvato in un nuovo file.");
            }
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante il salvataggio del punteggio nel file: " + e.getMessage());
        }
        
        userInput.setText("");
    }
}