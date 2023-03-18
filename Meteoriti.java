import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class Meteoriti extends JLabel implements ActionListener
{
    private JLabel lblMeteorite;
    private Point labelLocation; //Serve per prendere la posizione della Label
    private Timer timer;
    private int y = 0;
    private int deltaY;//DeltaY è la velocità con cui scende il meteorite
    private int posGenerazione;
    
    private Image met;

    public Meteoriti(int pos, int deltaY) 
    {
        setPosizioneGenerazione(pos);
        setDeltaY(deltaY);
        
        /*Inserimento e ridimensionamento dell'immagine*/
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("img/meteorite.png"));
            met = bufferedImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        }catch(IOException e) 
        { 
          e.printStackTrace();
        }
        
        lblMeteorite = new JLabel();
        lblMeteorite.setIcon(new ImageIcon(met));
        add(lblMeteorite);
        lblMeteorite.setLocation(posGenerazione, y);

        timer = new Timer(10, this);
        timer.start();
    }
    
    private void setPosizioneGenerazione(int pos){
        posGenerazione = pos;
    }

    private void setDeltaY(int d){
        deltaY= d;
    }
    
    public void actionPerformed(ActionEvent evt) {
        y += deltaY;
        labelLocation = lblMeteorite.getLocation();//Prende la posizione della Label
        
        Dimension dimensione = getSize(); // Ottenere la dimensione del pannello
        int altezza = dimensione.height;
        
        if (labelLocation.y > Toolkit.getDefaultToolkit().getScreenSize().height) //Controlla se la Label contenente il meteorite è andata fuori dallo schermo
        {
            remove(lblMeteorite);
            revalidate(); // Aggiorna il pannello per mostrare le modifiche
            repaint();    // Ridisegna il pannello per mostrare le modifiche
            timer.stop();   //Ferma il timer così smette di eseguire il codice
            System.out.println("Ho eliminato il meteorite");
        }
        lblMeteorite.setLocation(posGenerazione, y);
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Meteoriti(200, 1));
    }*/
}
