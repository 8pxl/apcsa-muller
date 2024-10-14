import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyEvent;

import lib.GDV5;

public class Ui {
    private Font font = new Font("Cartograph CF", Font.BOLD, 60);
    private boolean loss = false;
    public Ui() {} 

   //https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
   private void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
    FontMetrics metrics = g.getFontMetrics(font);
    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
    g.setFont(font);
    g.drawString(text, x, y);
    }

   public void displayScore(int score, int lives, Graphics2D win){
        win.setColor(Color.white);
        win.setFont(font);
        win.drawString("" + score, GDV5.getMaxWindowX() / 2 - 90, GDV5.getMaxWindowY() / 2);
        win.drawString("" + lives, GDV5.getMaxWindowX() / 2 + 60, GDV5.getMaxWindowY() / 2);
   }

   public boolean menu(Graphics2D win) {
        win.setColor(Color.white);
        win.setFont(font);
        int x = GDV5.getMaxWindowX();
        int y = GDV5.getMaxWindowY();
        Font small = new Font("Cartograph CF", Font.BOLD, 30);
        if (loss) {
            drawCenteredString(win, "you lose!!", new Rectangle(x/2 - 200, y/2 - 200, 400, 50), small);
            drawCenteredString(win, "press (enter) to continue", new Rectangle(x/2 - 200, y/2, 400, 30), small);
            win.fillRect(0, y/2-250, x, 5);
            
        }
        else {

            drawCenteredString(win, "breakout!", new Rectangle(x/2 - 200, y/2 - 200, 400, 50), font);
            drawCenteredString(win, "(enter) to start", new Rectangle(x/2 - 500, y/2, 300, 30), small);
            drawCenteredString(win, "(a), (d) to move", new Rectangle(x/2 + 200, y/2, 300, 30), small);

            win.fillRect(0, y/2-250, x, 5);
        }

        if (GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
            loss = false;
            return true;
        }

        return false;
   }

   public void setLoss() {
    loss = true;
   }
}
