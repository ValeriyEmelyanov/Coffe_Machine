import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrLen = scanner.nextInt();
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = scanner.nextInt();
        }

        int next = arr[0];
        int prev;
        for (int i = 0; i < arrLen - 1; i++) {
            prev = next;
            next = arr[i + 1];
            arr[i + 1] = prev;
        }
        arr[0] = next;

        for (int i : arr) {
            System.out.print(i + " ");
        }

    }
}