import java.util.Scanner;
public class tax {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		double amount = scan.nextFloat();
		scan.close();
		double state = amount * 0.04;
		double county = amount * 0.02;
		double total = amount + state + county;
		System.out.printf("amount: %.2f, state: %.2f, county: %.2f, total: %.2f",amount, state, county, total);
	}
}
