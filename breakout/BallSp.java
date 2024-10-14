import java.awt.Graphics2D;
import java.util.Vector;

import lib.GDV5;

public class BallSp {
    private int lives = 3;
    private int score = 0;
    private Vector<Ball> balls = new Vector<>();
    public BallSp(Ball ball) {
        balls.add(ball);
    }

    public void spawn(int x, int y) {
        Ball temp = new Ball(0, 0, GDV5.getMaxWindowX()/40, GDV5.getMaxWindowX()/40);
        temp.reset();
        temp.setPos(x, y);
        balls.add(temp);
    }

    public void update(Paddle paddle) {
        int al = 0;
        for (Ball b: balls) {
            if (b.alive()) {
                al ++;
                b.move();
                b.bounds();
                paddle.collision(b);
                // System.out.println(b.x);
            }
            // System.out.println(al);
        }
        if(al == 0) {
            lives--;
            balls.get(0).reset();
        }
    }

    public void draw(Graphics2D pb) {
        for (Ball b: balls) {
            if (b.alive()) {
                b.draw(pb);
            }
        }
    }
    
    public void collision(brick[] bricks, Spawner sp, PowerSp psp) {
        for (brick b: bricks) {
            for (Ball ball: balls) {
                if(ball.alive()) {
                    if(b.collision(ball, sp, psp)) {
                        score ++;
                    }
                }
            }
        }
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void menu(int y) {
        for (Ball b: balls) {
            b.move();
            b.menu(y);
        }
    }

    public int getFirstX() {
        return (int) balls.get(0).getCenterX();
    }
}
