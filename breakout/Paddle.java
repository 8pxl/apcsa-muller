import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import lib.GDV5;

public class Paddle extends Rectangle {
    public int keyL = KeyEvent.VK_A;
    public int keyR = KeyEvent.VK_D;
    public int score;
    public int paddleSpeed = 10;
    public int id;
    private long start;
    private boolean powered = false;
    private int iw;
    private int stacks = 0;


    public Paddle(int x, int y, int width, int height) {
        super(x,y,width, height);
        iw = width;
    }

    public void draw(Graphics2D p) {
        // (232, 72, 85)
        p.setColor(Color.white);
        if (powered) {
            int offset = (int) (Math.random() * 20 - 10);
            if (Math.random() < .3) {
                p.setColor(new Color(176, 41, 59));
            }
            else if (Math.random() > .6) {
                p.setColor(new Color(219, 213, 110));
            }
            else {
                p.setColor(new Color(20, 70, 160));
            }
            // p.setColor(new Color(offset));
            p.fillRect(x + (int) (Math.random() * 20 - 10),y + offset,width,height);
            // System.out.println(offset);
        }
        else {
            p.fillRect(x,y,width,height);
        }
        // System.out.println(dr);
        // p.fillRect(x,y,width,height);
    }

    public void move(BallSp spawner, boolean single) {
        if (!single) {
            if(GDV5.KeysPressed[keyL] && x > 0) {
                x -= paddleSpeed;
            }
            else if (GDV5.KeysPressed[keyR] && (x + this.width < GDV5.getMaxWindowX())) {
                x += paddleSpeed;
            }
        }
        else {
            int dist = spawner.getFirstX() - (x+width/2);
            int dir = spawner.getFirstX() > (x+width/2) ? 1 : -1;
            int vel = dir * Math.min(Math.abs(dist), paddleSpeed);
            x += vel;
        }

        if (System.currentTimeMillis() - start > 3000 && powered) {
            powered = false;
            stacks = 0;
            width = iw;
        }

        if(powered && ((System.currentTimeMillis() - start) % 500 < 15)) {
            for (int i = 0; i < stacks; i++) {
                spawner.spawn(x+ width/2, y- 30);
            }
        }
    }
    
    // public void single(int ball) {
    //     int dist = ball - (x+width/2);
    //     int dir = ball > (x+width/2) ? 1 : -1;
    //     int vel = dir * Math.min(Math.abs(dist), paddleSpeed);
    //     x += vel;
    // }

    public void collision(Ball ball) {
        if (ball.checkCollision(x, y, width, height)) {
            ball.bounce(true);
        }
    }

    public void power() {
        powered = true;
        width = Math.min(width+30, 250);
        stacks += 1;
        start = System.currentTimeMillis();
    }

    public void reset() {

    }
}
