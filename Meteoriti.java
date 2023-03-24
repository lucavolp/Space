/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;

public class Meteoriti extends JLabel implements ActionListener
{
    private Point labelLocation; //Serve per prendere la posizione della Label
    private Timer timer;
    private int y = 0;
    private int deltaY;//DeltaY è la velocità con cui scende il meteorite
    private int posGenerazione;
    private int posX;
    
    private Image met;
    
    public Meteoriti(int pos, int deltaY) 
    {
        setPosizioneGenerazione(pos);
        setDeltaY(deltaY);
        
        //Inserimento e ridimensionamento dell'immagine
        try
        {
            BufferedImage bufferedImage = ImageIO.read(new File("img/meteorite.png"));
            met = bufferedImage.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        }catch(IOException e) 
        { 
          e.printStackTrace();
        }
        
        this.setIcon(new ImageIcon(met));
        this.setLocation(posGenerazione, y);

        timer = new Timer(10, this);
        timer.start();
    }
    
    private void setPosizioneGenerazione(int pos)//Gestisce lo spawn del meteorite e lo divide nello schermo
    {
        
        posX = Toolkit.getDefaultToolkit().getScreenSize().width;//Prende la larghezza dello schermo
        posX /= 20;//Divide lo schermo in 20 parti
        int colonne = (int)posX * 5; //Le prime 5 parti le lascio per una colonna con i dati
        switch(pos)
        {
            case 1:
                posGenerazione = colonne;
                break;
            case 2:
                posGenerazione = colonne + posX;
                break;
            case 3:
                posGenerazione = colonne + posX*2;
                break;
            case 4:
                posGenerazione = colonne + posX*3;
                break;
            case 5:
                posGenerazione = colonne + posX*4;
                break;
            case 6:
                posGenerazione = colonne + posX*5;
                break;
            case 7:
                posGenerazione = colonne + posX*6;
                break;
            case 8:
                posGenerazione = colonne + posX*7;
                break;
            case 9:
                posGenerazione = colonne + posX*8;
                break;
            case 10:
                posGenerazione = colonne + posX*9;
                break;
        }
    }

    private void setDeltaY(int d){
        deltaY= d;
    }
    
    public void actionPerformed(ActionEvent evt) 
    {
        y += deltaY;
        labelLocation = this.getLocation();//Prende la posizione della Label
        
        this.setLocation(posGenerazione, y);
        Dimension dimensione = getSize(); // Ottenere la dimensione del pannello
        int altezza = dimensione.height;
        
        if (labelLocation.y > Toolkit.getDefaultToolkit().getScreenSize().height) //Controlla se la Label contenente il meteorite è andata fuori dallo schermo
        {
            Container parent = getParent(); //ottieni il pannello genitore
            parent.remove(this); //rimuovi il componente dal pannello
            revalidate(); // Aggiorna il pannello per mostrare le modifiche
            repaint();    // Ridisegna il pannello per mostrare le modifiche
            timer.stop();   //Ferma il timer così smette di eseguire il codice
            //System.out.println("Ho eliminato il meteorite");
        }
        
    }
    
    public int getDimCol()
    {
        return posX; 
    }
}
