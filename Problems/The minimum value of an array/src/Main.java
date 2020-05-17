import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int arrLength = scanner.nextInt();
        final int[] arr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arr[i] = scanner.nextInt();
        }

        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            if (i < min) {
                min = i;
            }
        }

        System.out.println(min);
    }
}