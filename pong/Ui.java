package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import pong.PongRunner.gameState;
import pong.util.GDV5;
import pong.util.Vec2;

public class Ui {
   public Ui() {} 
   private int winner;
   private gameState prev;
   private gameState last;
   private int time = 1000;
   Word[] words = {
      new Word(new Vec2(3,2), new Vec2(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2), new Vec2(100,30), "1p (1)", new Font("Cartograph CF", Font.BOLD + Font.ITALIC, 40)),
      new Word(new Vec2(-3,1), new Vec2(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2), new Vec2(100,30), "2p (2)", new Font("Cartograph CF", Font.BOLD + Font.ITALIC, 40)),
      new Word(new Vec2(4,-2), new Vec2(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2), new Vec2(200,30), "controls (3)", new Font("Cartograph CF", Font.BOLD + Font.ITALIC, 40)),
   };

   Word[] conWords = {
      new Word(new Vec2(3,2), new Vec2(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2), new Vec2(400,30), "P1: 'W' + 'S'", new Font("Cartograph CF", Font.BOLD + Font.ITALIC, 40)),
      new Word(new Vec2(-3,1), new Vec2(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2), new Vec2(400,30), "P2: 'Up' + 'Down'", new Font("Cartograph CF", Font.BOLD + Font.ITALIC, 40)),
      new Word(new Vec2(4,-2), new Vec2(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2), new Vec2(450,30), "Charge: 'Q' or '/'", new Font("Cartograph CF", Font.BOLD + Font.ITALIC, 40)),
   };

   //https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
   private void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font) {
      FontMetrics metrics = g.getFontMetrics(font);
      int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
      int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
      g.setFont(font);
      g.drawString(text, x, y);
   }

   public void displayScore(int s1, int s2, Graphics2D win){
      Font font = new Font("Cartograph CF", Font.BOLD, 60);
      win.setColor(Color.white);
      win.setFont(font);
        win.drawString("" + s1, GDV5.getMaxWindowX() / 2 - 50, 40);
        win.drawString("" + s2, GDV5.getMaxWindowX() / 2 + 30, 40);
   }

   public void displayMenu(Graphics2D win, gameState state, int s1, int s2) {
      Font font = new Font("Cartograph CF", Font.BOLD, 80);
      int x = GDV5.getMaxWindowX();
      int y = GDV5.getMaxWindowY();
      win.setColor(Color.white);
      win.setFont(font);
      switch (state) {
         case menu:
            drawCenteredString(win, "pong.", new Rectangle(x/2-200, y/2 - 60, 400, 120), font);
            win.setColor(new Color(101, 97, 108, 95));
            win.setColor(Color.white);
            for (Word word: words) {
               word.draw(win);
            }
            break;
         case won: 
            drawCenteredString(win, "Player " + winner + " wins!" , new Rectangle(x/2-200, y/2-60, 400, 120), font);drawCenteredString(win, "(Press enter to restart)" , new Rectangle(x/2-200, y/2 + 40, 400, 120), new Font("Cartograph CF", Font.ITALIC + Font.BOLD, 30));
            break;
         case controls:
            // win.setColor(new Color(101, 97, 108, 95));
            win.setColor(Color.white);
            for (Word word: conWords) {
               word.draw(win);
            }
         break;
      }  
   }

   public void style(Graphics2D win) {
      win.setColor((new Color(51, 92, 103)));
      win.fillRect(0, 0, GDV5.getMaxWindowX(), GDV5.getMaxWindowY());
   }

   public void update() {
      for (Word word: words) {
         word.move();
         word.bounds();
      }

      for(Word word: conWords) {
         word.move();
         word.bounds();
      }
   }

   public gameState setStates(gameState curr, Paddle p1, Paddle p2) {
      if (curr != prev) {
         last = prev;
      }
      prev = curr;
      if(GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
         return(gameState.menu);
      }
      switch (curr) {
         case menu:
            if (GDV5.KeysPressed[KeyEvent.VK_1]) {
               return (gameState.p1);
            }
            if (GDV5.KeysPressed[KeyEvent.VK_2]) {
               return (gameState.p2);
            }
            if (GDV5.KeysPressed[KeyEvent.VK_3]) {
               return (gameState.controls);
            }
            break;   
         case p1: case p2:
            if(GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
               return(gameState.menu);
            }
            int q = p1.win();
            if (q != 0) {
               winner = q;
               return gameState.won;
            }
            q = p2.win();
            if (q != 0) {
               winner = q;
               return gameState.won;
            }
            break;
         case won:
            if(GDV5.KeysPressed[KeyEvent.VK_ENTER]) {
               p1.score = 0;
               p2.score = 0;
               return last;
            }
            break;
         default:
            return(curr);
      } 
      return(curr);
   }

   public void won() {
      // drawCenteredString(null, null, null, null);
   }
}
