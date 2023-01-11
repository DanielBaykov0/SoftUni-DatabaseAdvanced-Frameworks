import java.util.Arrays;
import java.util.Scanner;

public class E08MaxSequenceOfIncreasingElements {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int lastNumber = arr[0];
        int currentLength = 1;
        int currentStart = 0;

        int bestLength = 1;
        int bestStart = 0;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > lastNumber) {

                currentLength++;

            } else {

                if (currentLength > bestLength) {
                    bestLength = currentLength;
                    bestStart = currentStart;
                }

                currentLength = 1;
                currentStart = i;
            }

            lastNumber = arr[i];
        }

        if (currentLength > bestLength) {
            bestLength = currentLength;
            bestStart = currentStart;
        }

        System.out.println(
                Arrays.toString(
                                Arrays.copyOfRange(arr, bestStart, bestStart + bestLength))
                        .replaceAll("[\\[\\],]", ""));
    }
}
