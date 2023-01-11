import java.util.Arrays;
import java.util.Scanner;

public class E07MaxSequenceOfEqualElements {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] intArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int start = 0;
        int len = 1;
        int digit = 0;

        for (int i = 1; i < intArr.length; i++) {
            if (intArr[i] == intArr[i - 1]) {
                len++;

                if (len > start) {
                    start = len;
                    digit = intArr[i];
                }
            } else {
                len = 1;
            }
        }

        for (int i = 0; i < start; i++) {
            System.out.print(digit + " ");
        }
    }
}
