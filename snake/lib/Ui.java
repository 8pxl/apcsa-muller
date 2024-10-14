package lib;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.geom.Path2D;

enum State {
    splash, game, loss, level
}

public class Ui {
    
    int size;
    int pad;
    int rows;   
    int flicker = 0;
    float a = 0;
    private int score = 1;
    private int level;
    public State state = State.splash;
    Color bg = new Color(98, 63, 49);
    private Font font = new Font("Cartograph CF", Font.BOLD, 60);

   //https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
    private void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }


    public Ui(int size, int rows, int pad) {
        this.size = size;
        this.pad = pad;
        this.rows = rows;
    }

    public int getVel(int curr, int row) {
        //set up if else statements for wasd keys
        int v = curr;
        if (GDV5.KeysPressed[KeyEvent.VK_W]) {
            v = -row;
        }
        if (GDV5.KeysPressed[KeyEvent.VK_A]) {
            v = -1;
        }
        if (GDV5.KeysPressed[KeyEvent.VK_S]) {
            v = row;
        }
        else if (GDV5.KeysPressed[KeyEvent.VK_D]) {
            v = 1;
        }
        if (v == -curr) return curr;
        return v;
    }

    private void background(Graphics2D win) {
        win.setColor(bg);
        win.fillRect(0, 0, GDV5.getMaxWindowX(), GDV5.getMaxWindowY());
    }

    private void drawGame(Graphics2D win) {
        int x = GDV5.getMaxWindowX();
        int y = GDV5.getMaxWindowY();
        int half = (rows * size)/2;
        int hcenter = GDV5.getMaxWindowX()/2 - half;
        int vcenter = GDV5.getMaxWindowY()/2 - half;
        win.setColor(Color.white);
        win.fillRect(hcenter-6, vcenter-6, size * rows+12, size*rows+12);
        Color c;
        for (int i = 0; i < (rows * rows); i ++) {
            if (i%2 == 0) c = new Color(167, 201, 87);
            else c = new Color(106, 153, 78);
            win.setColor(c);
            win.fillRect(hcenter + ((i % rows) * size), vcenter + ((i / rows) * size), size, size);
        }
        win.setColor(new Color(244, 214, 204));
        drawCenteredString(win, "Score: " + score, new Rectangle(x/2- 20, 40, 60, 50), font.deriveFont(50f));
        drawCenteredString(win, "Level: " + (level+1), new Rectangle(x/2- 20, y-40, 60, 50), font.deriveFont(50f));
    }

    private void drawSplash(Graphics2D win) {
        win.setFont(font);
        win.setColor(new Color(244, 214, 204));
        int x = GDV5.getMaxWindowX();
        int y = GDV5.getMaxWindowY();
        drawCenteredString(win, "Snake", new Rectangle(x/2 + 100, y-100, 400, 50), font.deriveFont(170f));
        drawCenteredString(win, "W", new Rectangle(40, 25, 50, 50), font.deriveFont(50f));
        drawCenteredString(win, "A", new Rectangle(10, 65, 50, 50), font.deriveFont(50f));
        drawCenteredString(win, "S", new Rectangle(40, 65, 50, 50), font.deriveFont(50f));
        drawCenteredString(win, "D", new Rectangle(70, 65, 50, 50), font.deriveFont(50f));
        Path2D path = new Path2D.Double();
        path.moveTo(0, y);
        path.curveTo(500, y, x-500, 0, x, 0);
        win.setStroke(new BasicStroke(10));
        win.draw(path);
        win.setColor(new Color(255, 255, 255));
        drawCenteredString(win, "[enter] to start", new Rectangle(108, 105, 50, 50), font.deriveFont(25f));
    }

    public void drawLoss(Graphics2D win) {
        drawGame(win);
        int x = GDV5.getMaxWindowX();
        int y = GDV5.getMaxWindowY();
        win.setColor(new Color(0f, 0f, 0f, a));
        win.fillRect(0, 0, x, y);
        win.setColor(new Color(244, 214, 204));
        drawCenteredString(win, "you lose! better luck next time!", new Rectangle(x/2, y/2-100, 50, 50), font.deriveFont(50f));
        drawCenteredString(win, "[enter] to start", new Rectangle(x/2, y/2, 50, 50), font.deriveFont(25f));
        a = (float) Math.min(1, a + 0.0005);
    }

    public void drawLevel(Graphics2D win) {
        // drawGame(win);
        int x = GDV5.getMaxWindowX();
        int y = GDV5.getMaxWindowY();

            win.setColor(new Color(0f, 0f, 0f, a));
            win.fillRect(0, 0, x, y);

            if (flicker % 2 == 0) {
                win.setColor(new Color(230, 184, 156));
            }
            else {win.setColor(new Color(126, 196, 207));}
            drawCenteredString(win, "you've reached level " + level + "!", new Rectangle(x/2, y/2-100, 50, 50), font.deriveFont(50f));
            drawCenteredString(win, "[enter] to continue", new Rectangle(x/2, y/2, 50, 50), font.deriveFont(25f));
            a = (float) Math.min(0.6, a + 0.0005);
            flicker ++;
            // try {
            //     Thread.sleep(10l);
            // }
            // catch (InterruptedException e) {
            //     e.printStackTrace();
            // }

    }

    public void drawBoard(Graphics2D win) {
        background(win);
        switch (state) {
            case splash:
                if (GDV5.KeysPressed[KeyEvent.VK_ENTER]) state = State.game;
                drawSplash(win);
                break;
            case game:
                drawGame(win);
                break;
            case loss:
                if (GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
                    state = State.game;
                    score = 0;
                }
                drawLoss(win);
                level = 0;
                break;
            case level:
                drawGame(win);
                if (GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
                    state = State.game;
                }
                // drawLevel(win);
                break;
        }
    }

    public boolean isGame() {
        return state == State.game;
    }

    public boolean isLevel() {
        return state == State.level;
    }

    public void addScore() {
        score++;
        System.out.println(score);
        if (score % 5 == 0) {
            level++;
            a =0;
            state = State.level;
        }
    }

    public void loss() {
        a = 0;
        state = State.loss;
    }

    public int score() {
        return score;
    }

    public int level() {
        return level;
    }
}
