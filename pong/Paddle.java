package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import pong.util.GDV5;
import pong.util.Vec2;

public class Paddle extends Rectangle {
    public int keyUp;
    public int keyDown;
    public int score;
    public int paddleSpeed = 15;
    public int id;
    private int chargeKey;
    private int charge;

    public Paddle(int x, int y, int width, int height, int keyUp, int keyDown, int id) {
        super(x,y,width, height);
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.id = id;
        if(id == 1) {
            chargeKey = KeyEvent.VK_Q;
        }
        if(id == 2) {
            chargeKey = KeyEvent.VK_SLASH;
        }
    }

    public void draw(Graphics2D p) {
        // (232, 72, 85)
        double perc = charge / 150.0;
        double dr = -18 * perc;
        double dg = -183 * perc;
        double db = -131 * perc;
        // System.out.println(dr);
        p.setColor(new Color(250 + (int)(dr), 255 + (int)(dg), 216 + (int)(db)));
        p.fillRect(x,y,width,height);
    }

    public void move() {
        if (GDV5.KeysPressed[chargeKey]) {
            charge = Math.min(150, charge+1);
        }
        else {
            charge = Math.max(0, charge - 1);
            if(GDV5.KeysPressed[keyUp] && y > 0) {
                y -= paddleSpeed;
            }
            else if (GDV5.KeysPressed[keyDown] && y+this.height < GDV5.getMaxWindowY()) {
                y += paddleSpeed;
            }
        }
    }
    
    public void single(int ball) {
        int dist = ball - (y+height/2);
        int dir = ball > (y+height/2) ? 1 : -1;
        int vel = dir * Math.min(Math.abs(dist), paddleSpeed);
        y += vel;
;
    }

    public void collision(Ball ball) {
        if (ball.checkCollision(x+ (ball.x > x ? width : 0), y+height/2, 3, height/2 + 6)) {
            ball.bounce(true, charge);
            charge = 0;
        }
    }
    public int win() {
        if (score >= 5) {
            return id;
        }
        return 0;
    }
}
