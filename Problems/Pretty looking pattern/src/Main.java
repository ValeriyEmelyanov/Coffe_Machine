import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int lineNumber = 4;
        char[][] arr = new char[lineNumber][];
        for (int i = 0; i < lineNumber; i++) {
            arr[i] = scanner.nextLine().toCharArray();
        }

        String result = "YES";
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr[i].length - 1; j++) {
                if (arr[i][j] == arr[i][j + 1]
                        && arr[i][j] == arr[i + 1][j]
                        && arr[i][j] == arr[i + 1][j + 1]) {
                    result = "NO";
                    break;
                }
            }
        }

        System.out.println(result);
    }
}