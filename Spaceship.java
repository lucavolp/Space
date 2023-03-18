import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Spaceship extends MyPanel2 implements ActionListener, KeyListener {
    private int x, y;
    private Timer timer;
    private int deltaX;
    private boolean moveLeft, moveRight;

    public Spaceship(int x, int y) {
        this.x = x;
        this.y = y;
        setPreferredSize(new Dimension(50, 50));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 50, 50);
    }

    public void actionPerformed(ActionEvent e) {
        if (moveLeft && x > 0) {
            x -= deltaX;
        }
        if (moveRight && x < getWidth() - 50) {
            x += deltaX;
        }
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft = true;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            moveLeft = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            moveRight = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spaceship Game");
        Spaceship spaceship = new Spaceship(200, 400);
        frame.add(spaceship);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
