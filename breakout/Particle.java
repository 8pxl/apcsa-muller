import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Timer;

import lib.Util;
import lib.Vec2;

public class Particle extends Rectangle {
    private Vec2 vel;
    private Color color;
    private long start;
    public double timeout = Math.random() * 4000 + 6000;
    
    public Particle(int x, int y, Color c) {
        super(x, y, 6, 6);
        double mag = Math.random() * 4 + 2;
        double ang = Math.random() * 3.14 + 3.14;
        vel = new Vec2(mag * Math.cos(ang), -mag * Math.sin(ang));
        if(Math.random() < 0.65) {
            color = c;
        }
        else {
           color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        }
        start = System.currentTimeMillis();
    }

    public void move() {
        x += vel.x;
        y += vel.y;
        color = Util.combine(color,  new Color((float) Math.random(), (float) Math.random(), (float) Math.random()), 1 - ((System.currentTimeMillis() - start) / timeout));
    }

    public void draw(Graphics2D pb) {
        pb.setColor(color);
        // pb.drawOval(x, y, width, height);
        // pb.setColor(C);
        pb.fillOval(x,y,width,height);
    }

    public boolean alive() {
        return System.currentTimeMillis() - start < timeout;
    }
}
