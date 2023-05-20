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
import java.util.Timer;
import javax.imageio.*;

public class MyPanelScore extends JPanel implements Runnable
{
    private JLabel punteggio;
    private MyPanelGioco pannelloGioco;
    private long pt;
    private int tUpdate;        //ms di aggiornamento e aggiunta punti
    private Font font;
    private Thread punti;
    private JButton btnStart;
    
    protected JLabel timer;
    private int tMin=0;
    private int tSec=0;
    private int minPrec = 0;
    private int incr; //Incremento di punti 
    
    //Sfondo
    private Image backgroundImage;
    
    //Lista score
    private DefaultListModel<String> model;
    private JList<String> scoreRecenti;
    private JScrollPane scrollPane;
    
    private JLabel user;
    
    private JLabel highScore;
    
    public MyPanelScore(MyPanelGioco p2)
    {
        super();
        this.pannelloGioco = p2;
        
        btnStart=new JButton();
        btnStart.setPreferredSize(new Dimension(1,1));

        try {
            backgroundImage = ImageIO.read(new File("img/sfondoLaterale1.png"));
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

        incr = 1;
        tUpdate = 1000;
        pt = 0;
        
        //Robe per la lista
        model = new DefaultListModel<>();
        scoreRecenti = new JList<>(model);
        
        //Creazione label
        timer= new JLabel("Tempo di Gioco 00:00");
        timer.setFont(font.deriveFont(20f));
        timer.setForeground(Color.WHITE);
        
        punteggio = new JLabel("SCORE 0"); 
        punteggio.setFont(font.deriveFont(25f));
        punteggio.setForeground(Color.WHITE);
        
        user = new JLabel();
        user.setFont(font.deriveFont(20f));
        user.setForeground(Color.WHITE);
        user.setHorizontalAlignment(JLabel.CENTER);
        
        highScore = new JLabel("HighScore: ");
        highScore.setFont(font.deriveFont(20f));
        highScore.setForeground(Color.WHITE);
        highScore.setHorizontalAlignment(JLabel.CENTER);
        
        //Layout
        setLayout(new GridBagLayout()); // imposta il layout GridBagLayout

        GridBagConstraints l = new GridBagConstraints();
        //add(btnStart);
        //btnStart.setBounds(1,1,0,0);
        l.gridy = 0;
        add(user, l);
        
        l.gridy = 1;
        add(highScore, l);
        
        l.weighty = 1;
        l.gridy = 2;
        l.anchor = GridBagConstraints.CENTER;
        add(punteggio, l);
        
        l.gridy = 3;
        
        l.anchor = GridBagConstraints.NORTH;
        add(timer, l); 
        
        
        startThread();
    }
    
    //Per l'immagine di sfondo
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
        
    public void run()
    {
        while(!pannelloGioco.gameStatus())
        {
            if(!pannelloGioco.getPause())
            {
                pt += incr;
                punteggio.setText("SCORE "+pt);
                
                tSec++;
                if(tSec>59)
                {
                    tSec=0;
                    tMin++;
                }
                if(tSec>9)
                    timer.setText("Tempo di Gioco "+tMin+" "+tSec);
                else
                    timer.setText("Tempo di Gioco "+tMin+" 0"+tSec);

                aumentaDifficolta();
            }
            
            try {
                Thread.sleep(tUpdate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        punti.interrupt();
    }
    
    public void aumentaDifficolta()
    {
        /*if(tMin > 10) //Dopo 10 min perdi per forza hahah
                    pannelloGioco.editSpawnSpeed(1);*/
                    
        if(pannelloGioco.spawnSpeed() >= 100)
            if(tSec % 20 == 0) //Ogni 20 secondi cambia la velocità di spawn
            {
                pannelloGioco.editSpawnSpeed(pannelloGioco.spawnSpeed() - 35); //Diminuisce di 35 millesimi
                pannelloGioco.roberto.incrVMov();   //aumenta il moltiplicatore di velocità di spostamento della navicella
                //System.out.println("20 sec");
            }
        
        if(tMin == 0 && tSec == 45) //Ai primi 45 sec aggiunge una vita al proiettile e diminuisce di 50ms
        {
            pannelloGioco.addVitaMeteoriti();
            pannelloGioco.roberto.editWaitShot(100);
        }
            
        if(tMin - 1 == minPrec) //Ogni minuto fa cose
        {
            //System.out.println("Aggiunta velocità");
            pannelloGioco.incrVelocitaMeteoriti(); //Aggiunge una vita al meteorite
            minPrec = tMin; //Per scorrere il minuto
            incr += 2;
        }
        
        if(tMin % 2 == 0 && tMin != 0 && tSec == 0) //Ogni due minuti fa cose
        {
            //System.out.println("Aggiunta vita e diminuito tempo shot");
            pannelloGioco.roberto.editWaitShot(100); //Riduce di 100ms il tempo di attesa prima di sparare
            pannelloGioco.addVitaMeteoriti();
            pannelloGioco.roberto.incrProjectileSpeed(1);
            pannelloGioco.aumentaSpawn();
        }
    }
    
    public void caricaUser()
    {
        try {
            String nome = "";
            String savedScore = "";
            
            File file = new File("save/punteggi.txt"); // Specifica il percorso e il nome del file da leggere
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) 
            {
                String parts[] = line.split(";");
                nome = parts[0];
                savedScore = parts[1];
            }
            bufferedReader.close();
            
            user.setText(nome);
            highScore.setText(savedScore);
            
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante la lettura del file: " + e.getMessage());
        }
    }
    
    public void salvaHighScore() //Le score sono nella variabile pt
    {
        long vecchie = Long.parseLong(highScore.getText()); //highScore contiene anche la parola
        boolean vuoto = true;
        if(pt > vecchie) //Se l'utente ha fatto un nuovo record di score
        {
            try{
                File file = new File("save/punteggi.txt");
                //Lettura
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                
                
                String line;
                ArrayList<String> fileLine = new ArrayList<>();
                
                // Riposiziona il puntatore all'inizio del file
                //fileReader.reset();
                
                while ((line = bufferedReader.readLine()) != null) //Non entra mai in questo ciclo perchè dice che il file è null
                {
                    System.out.println("Lillo");
                    fileLine.add(line);//la mia lista contiene tutte le righe del file
                    vuoto = false;
                }
                bufferedReader.close();
                
                if(!vuoto)
                {
                    fileLine.remove(fileLine.size()-1);// = user.getText() + ";" + pt;
                    System.out.println("Non è vuoto ed elimino l'ultima linea");    
                }
                fileLine.add(user.getText() + ";" + pt);
                
                //Scrittura
                FileWriter fileWriter = new FileWriter(file); // true per aprire il file in modalità append
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                
                //Riscrive il file
                for(int i = 0; i < fileLine.size(); i++) //Carica tutta la lista nel file
                {
                    System.out.println(fileLine.get(i));
                    bufferedWriter.write(fileLine.get(i));
                    if(i+1 < fileLine.size()) //se non è all'ultima riga va a capo
                        bufferedWriter.newLine(); // Vai a una nuova riga
                }
                
                bufferedWriter.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void addScore(int score){
        pt += score;
    }
    
    public void startThread(){
        punti = new Thread(this, "Score");
        punti.start();
    }
}
