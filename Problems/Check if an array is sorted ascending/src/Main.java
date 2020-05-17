import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int arrLen = scanner.nextInt();
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = scanner.nextInt();
        }

        boolean isAsc = true;
        for (int i = 0; i < arrLen - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                isAsc = false;
                break;
            }
        }

        System.out.println(isAsc);
    }
}