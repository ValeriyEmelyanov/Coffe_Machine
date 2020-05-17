import java.util.*;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int compNumber = scanner.nextInt();
        final int[] incomes = new int[compNumber];
        for (int i = 0; i < compNumber; i++) {
            incomes[i] = scanner.nextInt();
        }
        final int[] taxPercents = new int[compNumber];
        for (int i = 0; i < compNumber; i++) {
            taxPercents[i] = scanner.nextInt();
        }

        double tax;
        double maxTax = 0;
        int serialNumber = -1;
        for (int i = 0; i < compNumber; i++) {
            if ((tax = incomes[i] * taxPercents[i] / 100.0) > maxTax) {
                maxTax = tax;
                serialNumber = i + 1;
            }
        }

        System.out.println(serialNumber);
    }
}