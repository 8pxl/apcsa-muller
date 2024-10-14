import java.util.Scanner;

public class fahrenheit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("enter temp: ");
        double fahrenheit = scanner.nextDouble();

        double celsius = (fahrenheit - 32) * 5/9;

        System.out.println("new temp: " + celsius);

        scanner.close();
    }
}   