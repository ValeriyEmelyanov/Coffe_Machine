package machine;

import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int cups = 9;
    private static int money = 550;

    public static void main(String[] args) {
        boolean goOn = true;
        while (goOn) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = SCANNER.nextLine();
            System.out.println();

            switch (action) {
                case "buy":
                    chooseAndSellCoffee();
                    break;
                case "fill":
                    getsupplies();
                    break;
                case "take":
                    giveMoney();
                    break;
                case "remaining":
                    printStatus();
                    break;
                case "exit":
                    goOn = false;
                    break;
                default:
                    System.out.println("Invalid input");
            }
            System.out.println();
        }

    }

    private static void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(String.format("%d of water", water));
        System.out.println(String.format("%d of milk", milk));
        System.out.println(String.format("%d of coffee beans", beans));
        System.out.println(String.format("%d of disposable cups", cups));
        System.out.println(String.format("%s of of money", money == 0 ? "0" : "$" + money));
    }

    private static void chooseAndSellCoffee() {
        System.out.println(
                "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = SCANNER.nextLine();

        switch (choice) {
            case "1":
                sellCofee(Cofee.ESPRESSO);
                break;
            case "2":
                sellCofee(Cofee.LATTE);
                break;
            case "3":
                sellCofee(Cofee.CAPPUCHINO);
                break;
            case "back":
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    private static void sellCofee(Cofee cofee) {
        if (!checkSupplies(cofee)) {
            return;
        }

        System.out.println("I have enough resources, making you a coffee!");
        water -= cofee.waterPerCup;
        milk -= cofee.milkPerCup;
        beans -= cofee.beansPerCup;
        cups--;
        money += cofee.moneyPerCup;
    }

    private static boolean checkSupplies(Cofee cofee) {
        String supplies = "";
        if (cofee.waterPerCup > water) {
            supplies += "water, ";
        }
        if (cofee.milkPerCup > milk) {
            supplies += "milk, ";
        }
        if (cofee.beansPerCup > beans) {
            supplies += "cofee beans, ";
        }
        if (1 > cups) {
            supplies += "disposable cups, ";
        }

        if (!supplies.isEmpty()) {
            System.out.println(
                    String.format(
                            "Sorry, not enough %s!", supplies.substring(0, supplies.length() - 2)));
            return false;
        }

        return true;
    }

    private static void getsupplies() {
        System.out.println("Write how many ml of water do you want to add:");
        water += SCANNER.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += SCANNER.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        beans += SCANNER.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += SCANNER.nextInt();
    }

    private static void giveMoney() {
        System.out.println(String.format("I gave you $%d", money));
        money = 0;
    }

    private enum Cofee {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCHINO(200, 100, 12, 6);

        final int waterPerCup;
        final int milkPerCup;
        final int beansPerCup;
        final int moneyPerCup;

        Cofee(int waterPerCup, int milkPerCup, int beansPerCup, int moneyPerCup) {
            this.waterPerCup = waterPerCup;
            this.milkPerCup = milkPerCup;
            this.beansPerCup = beansPerCup;
            this.moneyPerCup = moneyPerCup;
        }
    }
}
