import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CircleMove extends JPanel implements ActionListener {
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    private static final int CIRCLE_DIAMETER = 50;
    private static final int CIRCLE_SPEED = 5;
    
    private int circleY;
    private Timer timer;
    
    public CircleMove() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.WHITE);
        circleY = 0;
        timer = new Timer(10, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(SCREEN_WIDTH/2 - CIRCLE_DIAMETER/2, circleY, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }
    
    public void actionPerformed(ActionEvent e) {
        circleY += CIRCLE_SPEED;
        if (circleY > SCREEN_HEIGHT - CIRCLE_DIAMETER) {
            timer.stop();
        }
        repaint();
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Move");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CircleMove());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
