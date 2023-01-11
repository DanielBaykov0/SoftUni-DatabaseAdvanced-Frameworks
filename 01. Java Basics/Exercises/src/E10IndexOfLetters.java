import java.util.Scanner;

public class E10IndexOfLetters {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[] arr = scanner.nextLine().replaceAll("\\s+", "").toCharArray();

        for (char c : arr) {
            System.out.println(c + " -> " + (c - 'a'));
        }
    }
}
