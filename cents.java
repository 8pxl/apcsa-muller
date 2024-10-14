import java.util.Scanner;

public class cents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("cents: ");
        int cents = scanner.nextInt();

        int quarters = cents / 25;
        cents %= 25;

        int dimes = cents / 10;
        cents %= 10;

        int nickels = cents / 5;
        cents %= 5;

        int pennies = cents;

        System.out.println("quaters: " + quarters);
        System.out.println("dimes: " + dimes);
        System.out.println("nickles: " + nickels);
        System.out.println("pennies: " + pennies);

        scanner.close();
    }
}
