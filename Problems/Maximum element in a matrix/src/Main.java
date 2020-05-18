import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int rowSize = scanner.nextInt();
        final int colSize = scanner.nextInt();
        int[][] arr = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int maxVal = arr[0][0];
        int rowInd = 0;
        int colInd = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (arr[i][j] > maxVal) {
                    maxVal = arr[i][j];
                    rowInd = i;
                    colInd = j;
                }
            }
        }

        System.out.printf("%d %d", rowInd, colInd);
    }
}