import java.util.List;
import java.awt.*;

/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

public class CollisionDetection extends MyPanelGioco implements Runnable //forse le collisioni bisogna gestirle all'interno della classe MyPanelGioco
{
    private Thread thread;
    private Spaceship spaceship;
    private List<Meteoriti> lista;
    private Rectangle boxcolliderSpaceship;
    
    public CollisionDetection(Spaceship ss, List<Meteoriti> meteoritis)
    {
        setSpaceship(ss);
        setLista(meteoritis);
        
        /*thread = new Thread(this, "Collisioni");
        thread.start();*/
        //System.out.println("Creato l'oggetto per le collisioni");
    }
    
    //Thread che verifica in continuo le collisioni
    public void run()
    {
        while (!super.gameStatus() || !super.isPaused) 
        {
            //System.out.println(lista.size());
            if (lista.size() > 0) {
                // prende l'ultimo elemento della lista meteoritis
                Meteoriti lastMeteorite = lista.get(lista.size() - 1);
                Rectangle m = lastMeteorite.getBounds();
                // verifica se è avvenuta la collisione
                if (boxcolliderSpaceship.intersects(m)) {
                    //System.out.println("Collisione avvenuta");
                    super.setGameStatus(true); //Imposta il gioco il gameover
                    //break; // esce dal ciclo una volta che la collisione è stata rilevata
                }
            }
            
            try {
                Thread.sleep(800); //ferma il thread ogni 50 ms, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("È game over");
    }
    
    public void setLista(List<Meteoriti> meteoritis) {
        lista = meteoritis;
        System.out.println("Sono nel metodo per inserire la lista");
    }
    
    public void setSpaceship(Spaceship s){
        spaceship = s;
        boxcolliderSpaceship = spaceship.getBounds();
    }
    
    public void startThread(){
        thread = new Thread(this, "Collisioni");
        thread.start();
    }
    
    public void stopThread(){
        thread.stop();
    }
}
