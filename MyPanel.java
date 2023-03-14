/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
 */



import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;

public class MyPanel extends JPanel implements ActionListener
{
    private JTextField txt1;
    private JTextField txtRis;
    private JLabel lblTit;
    private JButton btnGen;
    private JButton btnSave;
    private JButton btnRic;
    private JLabel lbl2;
    private JLabel lbl3,lbl4,lbl5,lbl6;
    private JTextField txt2;
    //private int nRand;
    
    
    
    public MyPanel()
    {
        super();
        
        setBackground(Color.BLACK);
        
        Border border=LineBorder.createBlackLineBorder();
        
        
        btnGen=new JButton("Genera");
        btnSave=new JButton("Salva");
        btnRic=new JButton("Ricarica");
        txt1=new JTextField("");
        txtRis=new JTextField("");
        lblTit=new JLabel("Generatore numeri Random");
        lbl2=new JLabel("N Max");
        
        btnGen.addActionListener(this);
        btnSave.addActionListener(this);
        btnRic.addActionListener(this);
        txt1.setSize(200,50);
        //CREAZIONE GOUP LAYOUT -----------------------------------------------------------------
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        
        //gap automatici
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        
        //COLONNE |||||||||||||||||||||||||||||
        //colonna 1
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(lblTit). 
                addComponent(lbl2).
                addComponent(txt1).
                addComponent(btnGen).
                addComponent(txtRis).
                addComponent(btnSave).
                addComponent(btnRic)
                );
                 
        
    
        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
         
               
        //RIGHE -----------------
        //riga 1
        vGroup.addGroup(layout.createParallelGroup().
                 addComponent(lblTit));
        //riga 2
        vGroup.addGroup(layout.createParallelGroup().
                 addComponent(lbl2));
        //riga 3
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).
                 addComponent(txt1));
        //riga 4
        vGroup.addGroup(layout.createParallelGroup().
                 addComponent(btnGen));
        //riga 5
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).
                 addComponent(txtRis));
        //riga 6
        vGroup.addGroup(layout.createParallelGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                   .addComponent(btnSave)
                   .addComponent(btnRic))
                 );
        //riga 7
        vGroup.addGroup(layout.createParallelGroup().
                 addComponent(btnRic));
        
                 
                 
                 
        //applica colonne
        layout.setHorizontalGroup(hGroup);
        //applica righe
        layout.setVerticalGroup(vGroup);

        //---------------------------------------------------------------------------------------
        
        txtRis.setEditable(false);
        
        
        
        add(lblTit);
        add(lbl2);
        add(txt1);
        add(txtRis);
        add(btnGen);
        add(btnSave);
        add(btnRic);
               
    }
    
    /*
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
    }*/
            
    
    public void actionPerformed (ActionEvent e)
    {
        /*if(e.getSource()==btnGen)
        {
            nRand=new Casuale();
            txtRis.setText(nRand.randInt(Integer.parseInt(txt1.getText()))+"");
        }*/
        if(e.getSource()==btnSave)
        {
            salvaValore(Integer.parseInt(txtRis.getText()));
        }
        if(e.getSource()==btnRic)
        {
            txtRis.setText(leggiValore()+"");
        }
    }
    
    public void salvaValore(int valore) 
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("valore.txt"));
            writer.write(Integer.toString(valore)); //scrive il valore come stringa nel file
            writer.close();
            txtRis.setText("Valore " + valore + " salvato con successo nel file " + "valore.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int leggiValore() 
    {
        int valore = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("valore.txt"));
            String line = reader.readLine();
            valore = Integer.parseInt(line); //converte la stringa letta dal file in un intero
            reader.close();
            System.out.println("Valore " + valore + " letto con successo dal file " + "valore.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valore;
    }

}