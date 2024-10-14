

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import lib.GDV5;

public class Snake{
    int size;
    int pad;
    int rows;   
    private ArrayList<Integer> snake;

    public Snake(int size, int rows, int pad, int start, int dir) {
        snake = new ArrayList<Integer>(Arrays.asList(start, start+dir, start+2*dir, start+3*dir));
        this.size = size;
        this.pad = pad;
        this.rows = rows;
    }
    
    public void move(int vel, boolean grow) {
        snake.add(snake.get(snake.size()-1) + vel);
        if (!grow) snake.remove(0);
    }

    public boolean collision(Snake snake2) {
        int head = snake.get(snake.size()-1);
        int second = snake.get(snake.size()-2);
        if (head < 0 || head > (rows * rows)-1) return true;
        for (int i = 0; i < snake.size()-2; i++) {
            if (head == snake.get(i) || head == snake2.get(i)) return true;
        }
        return ((Math.abs(head - second) == 1) && head / rows != second / rows);
    }

    public boolean grow(int food) {
        return(snake.get(snake.size()-1) == food);
    }
    
    public boolean in(int a) {
        for (int i: snake) {
            if (i == a) return true;
        }
        return false;
    }
    public void draw(Graphics2D win) {
        int half = (rows * size)/2;
        int hcenter = GDV5.getMaxWindowX()/2 - half;
        int vcenter = GDV5.getMaxWindowY()/2 - half;
        for (int i: snake) {
            if (i == snake.get(snake.size()-1)) win.setColor(new Color(188, 71, 73));
            else win.setColor(new Color(242, 232, 207));
            win.fillRect(hcenter + ((i % rows) * size), vcenter + ((i / rows) * size), size, size);
            if (i == snake.get(snake.size()-1)) win.setColor(new Color(209, 102, 102));
            else win.setColor(new Color(56, 102, 65));
            win.fillRect((i % rows) * size + hcenter + 5, (i / rows) * size+5+vcenter, size-10, size-10);
        }
    }
    public int get(int i ) {
        return snake.get(i);
    }

}
