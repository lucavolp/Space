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
    
    public GameOver() 
    {
        super();
        //System.out.println("GameOver dalla classe");
        this.setOpaque(false);
        setBackground(null);
        //this.setPreferredSize(new Dimension(1920, 1080));
        setLayout(new GridBagLayout());
        as = new AscoltatoreEsterno();
        
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("img/game-over.png"));
            go = bufferedImage.getScaledInstance(50, 55, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        spazio = new JLabel();
        spazio.setPreferredSize(new Dimension(100, 20));
        scritta = new JLabel();
        scritta.setIcon(new ImageIcon(go));
        
        replayButton = new JButton("Rigioca");
        replayButton.addActionListener(as);
        
        exitButton = new JButton("Home");
        exitButton.addActionListener(as);
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets=new Insets(15,15,15,15);    //dovrebbe mettere spazio fra gli elementi
        
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

    /* Se serve per provare il layout i bottoni e cose varie
    public static void main(String[] args) {
        JFrame frame = new JFrame("Esempio Game Over");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GameOver());
        frame.setSize(1920, 1080);
        frame.setVisible(true);
    }*/
}
