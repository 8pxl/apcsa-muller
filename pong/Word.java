package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import pong.util.GDV5;
import pong.util.Vec2;

public class Word {
    private Vec2 vel;
    public String text;
    private Vec2 pos;
    private Font font;
    private Vec2 dim;

    private void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
     
    public Word(Vec2 vel, Vec2 pos, Vec2 dimensions, String text, Font font) {
        this.vel = vel;
        this.pos = pos;
        dim = dimensions;
        this.text = text;
        this.font = font;
    }
    
    public void move() {
        pos.add(vel);
    }

    public void bounce(boolean yesx) {
        double mag = vel.hypot();
        // double randVel = (Math.random());
        // double randAng = (Math.random() * 0.1) - 0.05;
        // mag += randVel;
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
    public void bounds() {
        if (pos.x + dim.x > GDV5.getMaxWindowX()) {
            bounce(true);
        }
        if (pos.x < 0) {
            bounce(true);
        }

        if(pos.y< 0) {
            bounce(false);
        }

        if (pos.y + dim.y > GDV5.getMaxWindowY()) {
            bounce(false);
        }
    }

    // public void 
    public void draw(Graphics2D win) {
        // win.setColor(Color.BLACK);
        // win.fillRect(((int)pos.x), ((int)pos.y), ((int)dim.x), ((int)dim.y));
        win.setColor(Color.white);
        drawCenteredString(win, text, new Rectangle(((int)pos.x), ((int)pos.y), ((int)dim.x), ((int)dim.y)), font);
    }
}
