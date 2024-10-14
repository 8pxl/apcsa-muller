package pong;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import pong.util.GDV5;
import pong.util.Vec2;

public class PongRunner extends GDV5 {
    enum gameState {
        menu,
        won,
        p1,
        p2,
        controls
    }

    private int paddleSize = 80;
    private Ball ball = new Ball(getMaxWindowX()/2, getMaxWindowY()/2, getMaxWindowX()/40, getMaxWindowX()/40);
    
    public Paddle p1 = new Paddle(20, getMaxWindowY()/2 - paddleSize/2, paddleSize/6, paddleSize, KeyEvent.VK_W, KeyEvent.VK_S, 1);
    public Paddle p2 = new Paddle(getMaxWindowX() - 20 - paddleSize/6, getMaxWindowY()/2 - paddleSize/2,paddleSize/6, paddleSize, KeyEvent.VK_UP, KeyEvent.VK_DOWN,2);
    public Ui ui = new Ui();
    public gameState state = gameState.menu;

    public static void main(String[] args) {
        PongRunner g = new PongRunner();
        g.start();    
    }

    @Override
    public void update() {
        state = ui.setStates(state, p1, p2);
        switch (state) {
            case controls: case menu:
                ui.update();
                p1.score = 0;
                p2.score= 0;
                return;
            case won: return;
            case p1:
                p1.single(ball.y + ball.height/2);
                break;
            case p2:
                p1.move();
                break;  
            
        }
        ball.move(); ball.bounds(p1, p2);
        p2.move();
        p1.collision(ball); p2.collision(ball);  
    }

    @Override
    public void draw(Graphics2D win) {
        ui.style(win);
        switch (state) {
            case controls:
            case menu:
                ui.displayMenu(win, state, p1.score, p2.score);
                break;
            
            case p2: case p1:
                ball.draw(win);
                p1.draw(win);
                p2.draw(win);
                ui.displayScore(p1.score, p2.score, win);
                break;

            case won:
                ui.displayMenu(win, state, p1.score, p2.score);
                break;
        }
    }
}
