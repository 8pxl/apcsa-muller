import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;

import battleship.Game;
import lib.GDV5;
import lib.Sound;
import lib.Ui;


public class Game extends GDV5 {
    int frameCount = 0;
    int rows = 11;
    int gridSize = rows * rows;
    int vel = rows;
    int pad = GDV5.getMaxWindowX()/100;
    int grid = GDV5.getMaxWindowX()/22;
    int speed = 9;
    int level = 0;
    int prev = 0;

    String[] sounds = new String[] {"audio/background.wav", "audio/eat.wav", "audio/boo.wav"};
    Sound sound = new Sound(sounds, this);

    Snake snake = new Snake(grid, rows, pad, (rows/2) * rows,1);
    Snake snake2 = new Snake(grid, rows, pad, (rows/2) * rows + rows-1,-1);
    lib.Ui ui = new Ui(grid, rows, pad);    
    Food food = new Food(grid, rows, pad);


    public static void main(String[] args) { 
        Game g = new Game();
        g.start();
    }
    
    public Game(){sound.loop(0);}
    
    @Override
    public void update() {
        vel = ui.getVel(vel, rows);
        if (frameCount > speed-level && ui.isGame()) {
            frameCount = 0;
            boolean a = snake.grow(food.get());
            snake.move(vel, a);
            snake2.move(-vel, a);
            if (snake.collision(snake2) || ui.level() != level ) {
                level = ui.level();
                if (snake.collision(snake2)) {
                    sound.play(2);
                    ui.loss();
                    level = 0;
                }
                snake = new Snake(grid, rows, pad, (rows/2) * rows,1);
                snake2 = new Snake(grid, rows, pad, (rows/2) * rows + rows-1,-1);
                vel = 11;
            }
            if (a) {
                food.spawn(snake);
                sound.play(1);
                ui.addScore();
            }
        }
        frameCount++;
    }

    @Override
    public void draw(Graphics2D win) {
        ui.drawBoard(win);
        if(ui.isGame() || ui.isLevel()) {
            food.draw(win);
            snake.draw(win);
            snake2.draw(win);
        }
        if (ui.isLevel()) {
            ui.drawLevel(win);
        }
    }
}
