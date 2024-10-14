import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

public class Spawner {
    private Vector<Particle> particles = new Vector<>();

    public Spawner() {
        // center = new Vec2(x, y);
    }

    public void spawn(double x, double y, Color c) {
        for (int i = 0; i < Math.random() * 40 + 30; i++) {
            double r = 30 * Math.sqrt(Math.random());
            double theta = Math.random() * 2 * 3.141592;
            particles.add(new Particle((int) (x + r*Math.cos(theta)), (int) (y + r*Math.sin(theta)), c));
        }
    }

    public void draw(Graphics2D pb) {
        for (Particle particle: particles) {
            if (particle.alive()) {
                particle.draw(pb);
            }
        }
    }

    public void update() {
        for (Particle particle: particles) {
            if (particle.alive()) {
                particle.move();
            }
        }
    }
}
