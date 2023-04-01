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

public class Meteoriti extends JLabel //implements ActionListener //da modificare e mettere i thread
{
    private Point labelLocation; //Serve per prendere la posizione della Label
    private Timer timer;
    private int y = 0;
    private int deltaY;//DeltaY è la velocità con cui scende il meteorite
    private int posGenerazione;
    private int posX;
    private Image met;
    
    private boolean eliminato = false; //variabile per vedere se il meteorite esiste 

    //private JLabel lblMeteorite;
    private Thread thread;
    
    public Meteoriti(int pos, int deltaY) //Gli passo la posizione dove generare il meteorite (random) e la velocità di cascata del meteorite
    {
        setPosizioneGenerazione(pos);
        setDeltaY(deltaY);
        
        //lblMeteorite = new JLabel();
        
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

        /*timer = new Timer(10, this);
        timer.start();*/
        
        thread = new Thread("Meteorite");
        thread.start();
        
    }
    //Fare un ciclo che scorre tutta la lista e ogni volta chiamare il metodo move() (dentro il metodo run nella classe primaria)
    //Label = getMeteorite() per ogni elemento così aggiorna il meteorite
    
    public void run() //Richiama metodo per far muovere il meteorite e gestisce se la Label va fuori dallo schermo
    {
        while(!eliminato)
        {
            move();
            
            labelLocation = this.getLocation();//Prende la posizione della Label
            if (labelLocation.y > Toolkit.getDefaultToolkit().getScreenSize().height - 400) //Controlla se la Label contenente il meteorite è andata fuori dallo schermo
            {
                Container parent = getParent(); //ottieni il pannello genitore
                parent.remove(this); //rimuovi il componente dal pannello
                parent.revalidate(); // Aggiorna il pannello per mostrare le modifiche
                parent.repaint();    // Ridisegna il pannello per mostrare le modifiche
                thread.stop();  //Stoppa il thread
                eliminato = true;   //Smette di fare il ciclo
            }
            
            repaint();
            try {
                Thread.sleep(10); //ferma il thread ogni 10millisecondi, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void move() //metodo per far muovere il meteorite
    {
        y += deltaY; //Incrementa la y della velocità con cui scende il meteorite
        this.setLocation(posGenerazione, y); //Sposta il meteorite
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
    
    public int getDimCol(){
        return posX; 
    }
    
    public void stopTimer(){
        timer.stop();
    }
    
    public void startTimer(){
        timer.start();
    }
}
