import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int arrLen = scanner.nextInt();
        final int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = scanner.nextInt();
        }
        final int m = scanner.nextInt();
        final int n = scanner.nextInt();

        boolean result = false;
        for (int i = 0; i < arrLen - 1; i++) {
            if (arr[i] == m && arr[i + 1] == n
                    || arr[i] == n && arr[i + 1] == m) {
                result = true;
                break;
            }
        }

        System.out.println(result);
    }
}