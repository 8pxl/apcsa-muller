package lib;

import java.awt.Color;

public class Util {
    public static Color combine(Color c1, Color c2, double alpha) {
        int r = (int) (alpha * c1.getRed()   + (1 - alpha) * c2.getRed());
        int g = (int) (alpha * c1.getGreen() + (1 - alpha) * c2.getGreen());
        int b = (int) (alpha * c1.getBlue()  + (1 - alpha) * c2.getBlue());
        return new Color(r, g, b);
    }

    public static int[][] rotate(int[] x, int[] y, double angle, int cx, int cy) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        int rot[][] = {{0,0,0}, {0,0,0}};
        for (int i = 0; i < 3; i++) {
            rot[0][i] = (int) (cx + (((x[i] - cx) * cos) - ((y[i] - cy) * sin)));
            rot[1][i] = (int) (cy + ((x[i] - cx) * sin) + ((y[i] - cy) * cos));
        }
        // System.out.println(x.length);
        // System.out.println(y.length);
        return rot;
    }
}
