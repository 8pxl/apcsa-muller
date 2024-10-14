import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import lib.GDV5;
import lib.Ui;

public class breakout extends GDV5 {
    private int padding, bw, bh;
    private int bpc = 12; 
    private int rows = 5;
    private int paddleSize = 150;
    private Paddle paddle = new Paddle(getMaxWindowX()/2 - paddleSize/2, getMaxWindowY() - paddleSize/4, paddleSize, paddleSize/10);
    // private Ball ball = new Ball(getMaxWindowX()/2, getMaxWindowY()/2, getMaxWindowX()/40, getMaxWindowX()/40);
    private Ball ball = new Ball(getMaxWindowX()/2, getMaxWindowY()/2 - 300, getMaxWindowX()/40, getMaxWindowX()/40);
    private Ui ui = new Ui();
    private brick[] bricks = new brick[bpc * rows];
    private Spawner spawner = new Spawner();
    private PowerSp powerSp = new PowerSp();
    private BallSp ballSp = new BallSp(ball);
    private boolean inMenu = true;
    private int topDist = 50;
    private boolean single = false;

    private void setBrickSize() {
        padding = 4;
        // bpc = 10;
        // rows = 3;
        bw = (GDV5.getMaxWindowX() - ((bpc+1) * (padding))) / (bpc);
        bh = ((GDV5.getMaxWindowY() / 6) - ((rows+1) * padding)) / (rows);
    }
    public breakout() {
        setBrickSize();
    }
    public void bricks() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        int change = 9;
        boolean decreasing = false;
        // setBrickSize();
        for (int i = 0; i < bpc*rows; i++) {
            int w = i % bpc;
            int h = i / bpc;
            bricks[i] = new brick((bw * w) + (padding * (w+2)), topDist + h * bh + (h+1) * padding, bw, bh, new Color(r,g,b));
            if (r < 255 - change && !decreasing) {
                r+=change;
            }
            else {
                // r = 255;
                if (g < 255 - change) {
                    g+=change;
                }
                else {
                    g=255;

                    if (b < 255-change) {
                        b+=change;
                    }
                    else {
                        decreasing = true;
                        if(r > change)  {
                            r -= change;
                        }
                        else {
                            r = 0;
                            if (g > change) {
                                g -= change;
                            }
                            else {
                                g = 0;
                                if (b > change) {
                                    b-=change;
                                }
                            }
                        }


                    }
                }
            }
        }
    }

    public void reset() {
        rows = Math.min(14, rows + 1);
        bricks = new brick[bpc * rows];
        bricks();
    }
    public static void main(String[] args) {
        breakout g = new breakout();
        g.bricks();
        g.start();    
    }
    
    private void menu(BallSp ballSp) {
        ballSp.menu(GDV5.getMaxWindowY()/2 - 250);
        if (System.currentTimeMillis() % 1000 < 20) {
            ballSp.spawn(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2 - 300);
        }
    }

    private boolean loss(Ui ui, BallSp ballSp) {
        if(ballSp.getLives() == 0) {
            ui.setLoss();
            return (true);
        }
        return (false);
    }

    @Override
    public void update() {
        if (!inMenu) {
            paddle.move(ballSp, single);
            if(loss(ui, ballSp)) inMenu = true;
            ballSp.collision(bricks, spawner, powerSp);
            ballSp.update(paddle);
            spawner.update();
            powerSp.update(paddle, ballSp);
            if (GDV5.KeysPressed[KeyEvent.VK_W]) single = !single;
        } 
        else {menu(ballSp);}
    }
    /*
            if (GDV5.KeysPressed[KeyEvent.VK_W]) {
                ballSp.spawn((int) paddle.getCenterX(), (int) paddle.getY() - 10);
                paddle.power();
            }
     */
    @Override
    public void draw(Graphics2D win) {
        if (!inMenu){
            spawner.draw(win);
            int num = 0;
            for (brick b : bricks) {
                if(b.draw(win)) num++;
            }
            if(num == 0) reset();
            ball.draw();
            paddle.draw(win);
            ui.displayScore(ballSp.getScore(), ballSp.getLives(), win);
            powerSp.draw(win);
        }
        else {
            if(ui.menu(win)) {
                ball = new Ball(getMaxWindowX()/2, getMaxWindowY()/2, getMaxWindowX()/40, getMaxWindowX()/40);
                ballSp = new BallSp(ball);
                inMenu = !inMenu;
            }
        }
        ballSp.draw(win);
    }
}