/**
 * Aggiungi qui una descrizione della classe GameOver
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

public class GameOver extends JPanel 
{
    private JLabel scritta, spazio;
    private JButton exitButton, replayButton;
    private Image go;
    private AscoltatoreEsterno as;
    private MyPanel pannelloHome;
    private MyFrame frame;
    private MyPanelMenu pM;
    //Sfondo
    private Image backgroundImage;
    
    private Font font;
    
    public GameOver(MyFrame f, MyPanel p, MyPanelMenu mM) 
    {
        super();
        //System.out.println("GameOver dalla classe");
        //this.setOpaque(false);
        setBackground(null);
        pannelloHome = p;
        frame = f;
        pM = mM;    //pm=MyPanelMenu
        if(pannelloHome == null)
            System.out.println("Ciao");
        //this.setPreferredSize(new Dimension(1920, 1080));
        setLayout(new GridBagLayout());
        as = new AscoltatoreEsterno(pannelloHome, frame, mM);
        
        /*try {
            BufferedImage bufferedImage = ImageIO.read(new File("img/game-over.png"));
            //System.out.println(Toolkit.getDefaultToolkit().getScreenSize().width + "x" + Toolkit.getDefaultToolkit().getScreenSize().height + "y");
            go = bufferedImage.getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //Apri sfondo
        try {
            backgroundImage = ImageIO.read(new File("img/game-over.png"));
        } catch (IOException e) {
            e.printStackTrace();
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
        
        spazio = new JLabel();
        scritta = new JLabel();
        //scritta.setIcon(new ImageIcon(go));
        replayButton = new JButton("Rigioca");
        replayButton.setFont(font.deriveFont(20f));
        //replayButton.setForeground(Color.WHITE);
        replayButton.addActionListener(as);
        
        exitButton = new JButton("Home");
        exitButton.setFont(font.deriveFont(20f));
        //exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(as);
        
        scritta.setPreferredSize(new Dimension(300,500));
        replayButton.setPreferredSize(new Dimension(300,50));
        spazio.setPreferredSize(new Dimension(300,50));
        exitButton.setPreferredSize(new Dimension(300,50));
        
        this.setBackground(new Color(192, 192, 192, 128));
        
        GridBagConstraints c = new GridBagConstraints();
        
        
        
        //c.insets=new Insets(15,15,15,15);    //dovrebbe mettere spazio fra gli elementi
        
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        add(scritta, c);
        c.gridwidth = 1;
        c.gridy = 1;
        add(replayButton, c);
        c.gridx = 1;
        add(spazio, c);
        c.gridx = 2;
        add(exitButton, c);
    }

    //Per l'immagine di sfondo
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
