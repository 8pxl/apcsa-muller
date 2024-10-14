import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Vector;

public class PowerSp {
    public PowerSp() {}
    private Vector<Power> powers = new Vector<>();
    private int numAlive = 0;
    public void spawn(int x, int y) {
        if (numAlive < 20) {
            powers.add(new Power(x,y));
        }
    }

    public void update(Paddle paddle, BallSp ballSp) {
        numAlive = 0;
        for (Power p: powers) {
            if (p.alive()) {
                numAlive ++;
                p.update();
                p.collision(paddle, ballSp);
            }
        }
    }

    public void draw(Graphics2D pb) {
        for (Power p: powers) {
            if (p.alive()) {
                p.draw(pb);
            }
        }
    }
}
