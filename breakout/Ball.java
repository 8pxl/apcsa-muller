// import java.awt.Color;
// import java.awt.Graphics2D;
import java.awt.*;

import lib.GDV5;
import lib.Vec2;

public class Ball extends Rectangle  {
    // private int score = 0;
    private boolean alive = true;
    private Vec2 vel = new Vec2(4,-4);

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics2D pb) {
        pb.setColor(Color.white);
        // pb.drawOval(x, y, width, height);
        // pb.setColor(C);
        pb.fillOval(x,y,width,height);
    }
    
    public void move() {
        x += vel.x;
        y -= vel.y;
    }

    public boolean checkCollision(int x, int y, int width, int height) {
        int tol = (int) vel.hypot();
        int nextX = this.x + (int) this.vel.x;
        int nextY = this.y + (int) this.vel.y;

        // Check for collision in the x-axis
        boolean xOverlap = (nextX < x + width + tol) && (nextX + this.width + tol > x);

        // Check for collision in the y-axis
        // boolean yOverlap = (nextY < y + height) && (nextY + this.height > y);
        boolean yOverlap;
        
        boolean dir;
        if (this.vel.y > 0) {
            dir = this.y > y;
            yOverlap = (nextY < tol + y + height);
        }
        else {
            dir = this.y < y;
            yOverlap = (nextY + this.height + tol > y);

        }
        if (xOverlap && yOverlap && dir) {
            // score ++;
        }
        return xOverlap && yOverlap && dir;
    }

    public void bounce(boolean yesy) {
        double mag = vel.hypot();
        double randVel = (Math.random() * (1.3 + 3 / 33.0)) - 0.02;
        double randAng = (Math.random() * 0.1) - 0.05;
        mag += randVel;
        double angle = Math.atan2(vel.y, vel.x);
        // angle += randAng;
        if (mag > 23) {
            mag = 23;
        }
        vel.y = (mag * Math.sin(angle));
        vel.x = (mag * Math.cos(angle));
        if (yesy) {
            vel.y = -vel.y;
        }
        else {
            vel.x = -vel.x;
        }
    }

    public void mbounce(boolean yesy) {
        if (yesy) {
            vel.y = -vel.y;
        }
        else {
            vel.x = -vel.x;
        }
    }

    public void bounds() {
        if(this.y < 0) {
            bounce(true);
        }

        if (this.y + width > GDV5.getMaxWindowY()) {
            // bounce(true);
            reset();
            alive = false;
        }

        if(this.x < 0) {
            bounce(false);
        }

        if (this.x + width > GDV5.getMaxWindowX()) {
            bounce(false);
        }
    }

    public void reset() {
        alive = true;
        x = GDV5.getMaxWindowX()/2;
        y = GDV5.getMaxWindowY()/2;
        double mag = 6.5;
        double angle = Math.random() * 3.14 / 4;
        final int dir = Math.random() < 0.5 ? 1 : -1;
        angle = (3 * 3.14 / 2) + (dir * angle) + dir * 3.14 /7;
        vel.x = Math.cos(angle) * mag;
        vel.y = Math.sin(angle) * mag;
    }
    // public int getScore() {
    //     return score;
    // }

    public boolean alive() {
        return alive;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void menu(int y) {
        if(this.y < 0) {
            mbounce(true);
        }

        if (this.y + width> y) {
            mbounce(true);
        }

        if(this.x < 0) {
            mbounce(false);
        }

        if (this.x + width > GDV5.getMaxWindowX()) {
            mbounce(false);
        }
    }
}
