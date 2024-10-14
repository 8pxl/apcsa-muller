package test;
// public class Arrays {
//     public static void main(String[] args) {
//         String[] arr = {"ten", "fading", "post", "card", "thunder", "hinge", "trailing", "batting"};

//         for (String s: arr) {
//             if (s.substring(s.length()-3, s.length()).equals("ing")) {
//                 System.out.println(s);
//             }
//         }
//     }
// }



public class Arrays {
    private int[] itemsSold = {48, 50, 37, 62, 38, 70, 55, 37, 64, 60};
    private double[] wages;

    public double computeBonusThreshold(){
        int lo = itemsSold[0];
        int hi = 0;
        double sum = 0;
        for (int count: itemsSold) {
            lo = Math.min(lo, count);
            hi = Math.max(hi, count);
            sum += count;
        }
        sum -= (lo + hi);
        return sum / (itemsSold.length - 2);
    }

    public void computeWages(double fixedWage, double perItemWage) {
        double bonus = this.computeBonusThreshold();
        for (int i = 0; i < itemsSold.length; i++) {
            wages[i] = fixedWage + (itemsSold[i] * perItemWage);
            if (itemsSold[i] > bonus)  {
                wages[i] *= 1.1;
            }   
        }
    }

}