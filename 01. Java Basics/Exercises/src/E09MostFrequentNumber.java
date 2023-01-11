import java.util.Arrays;
import java.util.Scanner;

public class E09MostFrequentNumber {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int num = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int tempNum = arr[i];
            int tempCount = 0;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == tempNum) {
                    tempCount++;
                    if (tempCount > count) {
                        num = tempNum;
                        count = tempCount;
                    }
                }
            }
        }

        System.out.println(num);
    }
}
