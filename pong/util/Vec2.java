package pong.util;

public class Vec2 {
   public double x; 
   public double y;
   public Vec2(double x, double y) {
    this.x = x;
    this.y = y;
   }

   public double hypot() {
      return (Math.sqrt(x*x + y*y));
   }

   public void add(Vec2 other) {
      x += other.x;
      y += other.y;
   }
}
