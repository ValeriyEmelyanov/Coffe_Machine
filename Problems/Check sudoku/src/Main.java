import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int num = scanner.nextInt();
        final int size = num * num;
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        boolean success = checkRows(arr, size)
                && checkColumns(arr, size)
                && checkSquares(arr, size, num);

        System.out.println(success ? "YES" : "NO");
    }

    private static boolean checkRows(int[][] arr, int size) {
        for (int[] row : arr) {
            if (!checkRow(row, size)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkRow(int[] row, int size) {
        for (int i = 1; i <= size; i++) {
            boolean found = false;
            for (int value : row) {
                if (value == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumns(int[][] arr, int size) {
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 1; j <= size; j++) {
                boolean found = false;
                for (int[] row : arr) {
                    if (row[i] == j) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkSquares(int[][]arr, int size, int num) {
        for (int i = 0; i < size; i += num) {
            for (int j = 0; j < size; j += num) {
                for (int k = 1; k <= size; k++) {

                    boolean found = false;
                    for (int l = 0; l < num; l++) {
                        for (int m = 0; m < num; m++) {
                            if (arr[i + l][j + m] == k) {
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                    if (!found) {
                        return false;
                    }

                }
            }
        }
        return true;
    }
}