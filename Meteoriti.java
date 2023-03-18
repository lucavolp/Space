import javax.swing.*;
import java.awt.event.*;
import java.awt.Point;

public class Meteoriti extends JFrame implements ActionListener
{
    private JLabel label;
    private Point labelLocation; //Serve per prendere la posizione della Label
    private Timer timer;
    private int y = 0;
    private int deltaY;//DeltaY è la velocità con cui scende il meteorite
    private int posGenerazione;

    public Meteoriti(int pos, int deltaY) {
        setPosizioneGenerazione(pos);
        setDeltaY(deltaY);
        
        label = new JLabel("Hello World!");
        add(label);
        label.setLocation(posGenerazione, y);

        timer = new Timer(10, this);
        timer.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);
    }
    
    private void setPosizioneGenerazione(int pos){
        posGenerazione = pos;
    }

    private void setDeltaY(int d){
        deltaY= d;
    }
    
    public void actionPerformed(ActionEvent evt) {
        y += deltaY;
        labelLocation = label.getLocation();//Prende la posizione della Label
        
        if (labelLocation.y > getHeight()) //Controlla se la Label contenente il meteorite è andata fuori dallo schermo
        {
            remove(label);
            revalidate(); // Aggiorna il pannello per mostrare le modifiche
            repaint();    // Ridisegna il pannello per mostrare le modifiche
            timer.stop();   //Ferma il timer così smette di eseguire il codice
        }
        label.setLocation(posGenerazione, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Meteoriti(200, 1));
    }
}
