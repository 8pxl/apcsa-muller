import java.util.Scanner;

public class eightball {
    public static void main(String[] args) {
        String[] responses = {"yes", "no", "maybe", "ask again later", "very doubtful", "certainly", "its unclear, better not tell you now"};
        Scanner scan = new Scanner(System.in);
        System.out.print("ask a question: ");
        String question = scan.nextLine();
        System.out.println(responses[(int)(Math.random() * 8.0)]);
    }
}

    