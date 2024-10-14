import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import lib.GDV5;
import lib.Util;

public class Power extends Rectangle{
    private long start;
    private Color color;
    private boolean alive = true;
    private int velocity;
    private int type;
    private double angle = 0;

    public Power(int x, int y) {
        super(x-15, y, 30, 30);
        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        start = System.currentTimeMillis();
        velocity = (int) (Math.random() * 3.5 + 1);
        type = Math.random() < 0.8 ? 1 : 0;
        if (type == 0) {
            width = 40;
            height = 40;
        }
    }

    public void update() {
        y += velocity;
        angle += 0.1;
        // color = Util.combine(color,  new Color((float) Math.random(), (float) Math.random(), (float) Math.random()), 1 - ((System.currentTimeMillis() - start) / 5000));
    }

    public void draw(Graphics2D pb) {
        pb.setColor(color);
        // pb.fillOval(x,y,width,height);
        if (type == 1) pb.fillRect(x, y, width, height);
        else {
            int rot[][] = Util.rotate(new int[] {x+width/2, (int) x, x+width}, new int[] {(int) ((y+height/2)  - width * Math.sqrt(3)/4), (int) ((y + height/2) + width * (Math.sqrt(3) / 4)), (int) (y + (height/2) + (width * Math.sqrt(3) / 4))}, angle, x+width/2, y+height/2);
            pb.fillPolygon(rot[0], rot[1], 3);
        }
        // System.out.println(type); 
    }

    public boolean alive() {
        // return System.currentTimeMillis() - start < timeout && alive;
        return (y < GDV5.getMaxWindowY() && alive);
    }

    public void collision(Paddle paddle, BallSp spawner) {
        if(paddle.getX() < x && (x < paddle.getX() + paddle.getWidth()) && paddle.getY() < y + height) {
            if (type == 1) spawner.spawn(x, y);
            else paddle.power();
            alive = false;
        }
    }
}
