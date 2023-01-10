import java.util.Scanner;

public class E04VowelOrDigit {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String symbol = scanner.nextLine();

        if (Character.isDigit(symbol.charAt(0))) {
            System.out.println("digit");
        } else if ("aeoui".contains(symbol)) {
            System.out.println("vowel");
        } else {
            System.out.println("other");
        }
    }
}
