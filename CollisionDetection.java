
import java.util.List;
import java.awt.*;

/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
**/

public class CollisionDetection implements Runnable //forse le collisioni bisogna gestirle all'interno della classe MyPanelGioco
{
    private MyPanelGioco pannello;
    private Thread thread;
    private List<Meteoriti> lista;
    private Rectangle boxcolliderSpaceship;
    private boolean GameOver = false;
    private boolean isPaused = false;
    
    public CollisionDetection(MyPanelGioco panelGioco)
    {
        setPannello(panelGioco);
    }
    
    //Thread che verifica in continuo le collisioni
    public void run()
    {
        while (!GameOver || !isPaused) //Gestire sto isPaused una volta finito tutto
        {
            //System.out.println(pannello.meteoritis.size());
            if (pannello.meteoritis.size() > 0) {
                // prende l'ultimo elemento della lista meteoritis
                Meteoriti lastMeteorite = pannello.meteoritis.get(pannello.meteoritis.size() - 1);
                Rectangle m = lastMeteorite.getBounds();
                boxcolliderSpaceship = pannello.roberto.getBounds();
                // verifica se è avvenuta la collisione
                if (boxcolliderSpaceship.intersects(m)) //Dovrei eliminare tutti i meteoriti o li metto in stato di pausa
                {
                    pannello.setGameStatus(true); //Imposta il gioco il gameover
                    pannello.verificaEliminati(); //Elimina gli ultimi meteoriti 
                    System.out.println("GameOver!!");
                }
            }
            
            try {
                Thread.sleep(800); //ferma il thread ogni 50 ms, intervallo di ascolto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            GameOver = pannello.gameStatus();
            //isPaused = pannello.getPause();
        }
    }
    
    public void setPannello(MyPanelGioco panelGioco) {
        pannello = panelGioco;
        //boxcolliderSpaceship = pannello.getBounds();
    }
    
    public void startThread(){
        thread = new Thread(this, "Collisioni");
        thread.start();
    }
    
    public void stopThread(){
        thread.stop();
    }
}