import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class brick extends Rectangle{
    private Color color;
    private boolean exists = true;

    public brick(int x, int y, int width, int height, Color c) {
        super(x,y,width,height);
        color = c;
    }

    public boolean draw(Graphics2D pb) {
        if (exists) {
            pb.setColor(color);
            pb.fill(this);
        }
        return exists;
    }

    public boolean collision(Ball ball, Spawner Spawner, PowerSp powerSp) {
        if (exists) {
            if (ball.checkCollision(x, y, width, height)) {
                ball.bounce(true);
                Spawner.spawn(x+width/2, y+height/2, color);
                if(Math.random() < 0.6) {
                    powerSp.spawn(x+width/2, y+height/2);
                }
                exists = false;
                return true;
            }
        }
        return false;
    }
}
