public class Point2D
{
   private int x;
   private  int y;

   public Point2D() {}

   public Point2D(int x,int y)
   {
      this.x = x;
      this.y = y;
   }
  // other methods
}

public class Point3D extends Point2D
{
   public int z;
 public Point3D(int x, int y)
     {
        this.x = x;
        this.y = y;
        this.z = 0;
     }
   // other code
}
