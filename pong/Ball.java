package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.Math;
import pong.util.Vec2;
import pong.util.GDV5;
import java.lang.Math;

public class Ball extends Rectangle{
    public Vec2 vel = new Vec2(5,2);
    
    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    
    public void draw(Graphics2D pb) {
        // pb.setColor(Color.white);
        // pb.drawOval(x, y, width, height);
        pb.setColor(new Color(32, 19, 53));
        pb.fillOval(x,y,width,height);
    }
    
    public void move() {
        x += vel.x;
        y -= vel.y;
    }
    
    public boolean checkCollision(int x, int y, int xbound, int ybound) {
        if (Math.abs((this.x + vel.x) - x) > Math.abs(this.x - x)) {
            return false;
        }
        return (Math.abs((this.x+ (this.x < x ? width: 0) ) - x) < xbound + Math.abs(vel.x) && (Math.abs((this.y + height/2) - y) < ybound + Math.abs(vel.y)));
            // vel.x = -vel.x;
            // vel.y = -vel.y;
    }
    public void bounce(boolean yesx, int add) {
        double mag = vel.hypot();
        double randVel = (Math.random() * (1.3 + add / 33.0)) - 0.02;
        // double randAng = (Math.random() * 0.1) - 0.05;
        mag += randVel;
        double angle = Math.atan2(vel.y, vel.x);
        // angle += randAng;`
        vel.y = (mag * Math.sin(angle));
        vel.x = (mag * Math.cos(angle));
        if (yesx) {
            vel.x = -vel.x;
        }
        else {
            vel.y = -vel.y;
        }
    }
    
    public void bounds(Paddle p1, Paddle p2) {
        if (this.x > GDV5.getMaxWindowX()) {
            p1.score++;
            this.reset();
        }
        if (this.x < 0) {
            p2.score++;
            this.reset();
        }

        if(this.y < 0) {
            bounce(false, 0);
        }

        if (this.y + width > GDV5.getMaxWindowY()) {
            bounce(false, 0);
        }
    }
    public void reset() {
        x = GDV5.getMaxWindowX()/2;
        y = GDV5.getMaxWindowY()/2;
        double mag = 6.5;
        double angle = Math.random() * 0.3 + 0.3;
        vel.x = -Math.signum(vel.x) * Math.cos(angle) * mag;
        vel.y = Math.sin(angle) * mag;
    }
}
