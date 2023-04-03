/**
 * Aggiungi qui una descrizione della classe Test
 * 
 * @author (Battistelli Kevin - Volpinari Luca)
 * @version (1.0)
*/

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Projectile {
    private Point2D position;
    private double velocity;
    private Rectangle2D bounds;

    public Projectile(double x, double y, double velocity) {
        this.position = new Point2D.Double(x, y);
        this.velocity = velocity;
        this.bounds = new Rectangle2D.Double(x, y, 5, 5); // set the bounds of the projectile
    }

    public void move() {
        position.setLocation(position.getX(), position.getY() - velocity); // move the projectile upwards
        bounds.setRect(position.getX(), position.getY(), 5, 5); // update the bounds of the projectile
    }

    public Rectangle2D getBounds() {
        return bounds;
    }

    public Point2D getPosition() {
        return position;
    }
}