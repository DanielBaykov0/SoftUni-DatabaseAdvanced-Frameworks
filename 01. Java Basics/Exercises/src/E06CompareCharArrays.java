import java.util.Scanner;

public class E06CompareCharArrays {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        char[] firstChar = scanner.nextLine().replaceAll("\\s+", "").toCharArray();
        char[] secondChar = scanner.nextLine().replaceAll("\\s+", "").toCharArray();

        int length = Math.min(firstChar.length, secondChar.length);

        for (int i = 0; i < length; i++) {
            if (firstChar[i] > secondChar[i]) {
                System.out.println(secondChar);
                System.out.println(firstChar);
                break;
            } else if (firstChar[i] < secondChar[i]) {
                System.out.println(firstChar);
                System.out.println(secondChar);
                break;
            } else {
                if (length == secondChar.length && i == length - 1) {
                    System.out.println(secondChar);
                    System.out.println(firstChar);
                } else if (length == firstChar.length && i == length - 1) {
                    System.out.println(firstChar);
                    System.out.println(secondChar);
                } else {
                    continue;
                }
            }
        }
    }
}
