import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import lib.GDV5;

public class Food {
    int pos;
    int rows;
    int size;
    int pad;
    BufferedImage img;

    public Food(int size, int rows, int pad) {
        this.rows = rows;
        this.size = size;
        this.pad = pad;
        try {
            img = ImageIO.read(new File("/Users/keijayhuang/Desktop/java/csa/snake/images/apple.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    
        spawn();
    }

    public void spawn() {
        pos = (int)(Math.random() * rows * rows);
    }

    public void spawn(Snake snake) {
        while (snake.in(pos)) pos = (int)(Math.random() * rows * rows);
    }

    public void draw(Graphics2D win) {
        int half = (rows * size)/2;
        int hcenter = GDV5.getMaxWindowX()/2 - half;
        int vcenter = GDV5.getMaxWindowY()/2 - half;
        win.setColor(new Color(47, 47, 47));
        // win.fillRect(hcenter + pos % rows * size, vcenter + pos / rows * size, size, size);
        win.drawImage(img, hcenter + pos % rows * size, vcenter + pos / rows * size, size, size, null);
    }

    public int get() {
        return pos;
    }    

}
