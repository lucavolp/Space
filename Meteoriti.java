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

public class Meteoriti extends JLabel implements Runnable //da modificare e mettere i thread
{
    //Coordinata y del meteorite
    private int y = 0;
    //Coordinata x di spawn del meteorite
    private int posGenerazione;
    //Dimensione colonne dove spawnano i meteoriti
    private int posX;
    //Velocità con cui scende il meteorite
    private int deltaY;
    //Immagine che contiene il meteorite
    private Image met;
    //Variabile per vedere se il meteorite esiste 
    private boolean eliminato = false; 
    //Thread per il movimento del meteorite
    private Thread movimento;
    
    private String test = "Giuseppe";
    
    public Meteoriti(int pos, int deltaY) //Gli passo la posizione dove generare il meteorite (random) e la velocità di cascata del meteorite
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
        this.setLocation(posGenerazione, 0);
        
        //Thread per far muovere il meteorite
        movimento = new Thread(this, "Meteorite");
        movimento.start();
        
    }
    
    public void run() //Metodo chiamato dal thread per far muovere il meteorite e gestisce se va fuori dallo schermo
    {
        while(!eliminato)
        {
            move();
            
            Point labelLocation = this.getLocation();//Prende la posizione della Label
            //System.out.println(labelLocation.y);
            if (labelLocation.y > Toolkit.getDefaultToolkit().getScreenSize().height - 100) //Controlla se la Label contenente il meteorite è andata fuori dallo schermo
            {
                
                Container parent = getParent(); //ottieni il pannello genitore
                eliminato = true;   //Smette di fare il ciclo
                //System.out.println("Meteorite Eliminato");
                parent.remove(this); //rimuovi il componente dal pannello
                stopThread();  //Stoppa il thread
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
        //posGenerazione = (1920/2) - 100;                  //commentato sennò mi spawna meteoriti solo in una colonna a destra uno sopra l'altro senza spostarli (perche ho una dimensione dello schermo che non è 1920 penso)
    }
    
    public int getPosizioneGenerazione(){
        return posGenerazione;
    }
    
    private void setDeltaY(int d){
        deltaY= d;
    }
    
    public int getDimCol(){
        return posX; 
    }
    
    public void stopThread(){
        movimento.stop();
    }
    
    public void startThread(){
        movimento = new Thread(this, "Meteorite");
        movimento.start();
    }
    
    public boolean getEliminato(){
        return eliminato;
    }
    
    //Metodo per le collisioni
    public Rectangle boundsMeteorite(){
        return this.getBounds();
    }
    
    public String toString(){
        return "Debug!!";
    }
    
    public void setText(String s){
        test = s;
    }
    
    public String getTest(){
        return test;
    }
}
