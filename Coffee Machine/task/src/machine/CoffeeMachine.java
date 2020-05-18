package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;

    private State state;

    public CoffeeMachine() {
        this.state = State.CHOOSING_ACTION;
        promptAction();
    }

    public boolean next(String action) {
        switch (state) {
            case CHOOSING_ACTION:
                processAction(action);
                if (state == State.EXITING) {
                    return false;
                }
                break;
            case CHOOSING_COFEE:
                chooseAndSellCoffee(action);
                state = State.CHOOSING_ACTION;
                promptAction();
                break;
            case FILLING_WATER:
                water += Integer.parseInt(action);
                state = State.FILLING_MILK;
                promptMilk();
                break;
            case FILLING_MILK:
                milk += Integer.parseInt(action);
                state = State.FILLING_BEANS;
                promptBeans();
                break;
            case FILLING_BEANS:
                beans += Integer.parseInt(action);
                state = State.FILLING_CUPS;
                promptCups();
                break;
            case FILLING_CUPS:
                cups += Integer.parseInt(action);
                state = State.CHOOSING_ACTION;
                System.out.println();
                promptAction();
                break;
            default:
                state = State.CHOOSING_ACTION;
                promptAction();
        }
        return true;
    }

    private void promptAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private void promptCofee() {
        System.out.println("What do you want to buy?" +
                " 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }

    private void promptWater() {
        System.out.println("Write how many ml of water do you want to add:");
    }

    private void promptMilk() {
        System.out.println("Write how many ml of milk do you want to add:");
    }

    private void promptBeans() {
        System.out.println("Write how many grams of coffee beans do you want to add:");
    }

    private void promptCups() {
        System.out.println("Write how many disposable cups of coffee do you want to add:");
    }

    private void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(String.format("%d of water", water));
        System.out.println(String.format("%d of milk", milk));
        System.out.println(String.format("%d of coffee beans", beans));
        System.out.println(String.format("%d of disposable cups", cups));
        System.out.println(String.format("%s of of money", money == 0 ? "0" : "$" + money));
        System.out.println();
    }

    public void processAction(String action) {
        System.out.println();
        switch (action) {
            case "buy":
                state = State.CHOOSING_COFEE;
                promptCofee();
                break;
            case "fill":
                state = State.FILLING_WATER;
                promptWater();
                break;
            case "take":
                giveMoney();
                state = State.CHOOSING_ACTION;
                promptAction();
                break;
            case "remaining":
                printStatus();
                state = State.CHOOSING_ACTION;
                promptAction();
                break;
            case "exit":
                state = State.EXITING;
                break;
            default:
                System.out.println("Invalid input");
                promptAction();
        }
    }

    private void chooseAndSellCoffee(String action) {
        switch (action) {
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
                state = State.CHOOSING_COFEE;
                break;
            default:
                System.out.println("Invalid input");
        }
        System.out.println();
    }

    private void sellCofee(Cofee cofee) {
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

    private boolean checkSupplies(Cofee cofee) {
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

    private void giveMoney() {
        System.out.println(String.format("I gave you $%d", money));
        System.out.println();
        money = 0;
    }

    private enum State {
        CHOOSING_ACTION,
        CHOOSING_COFEE,
        FILLING_WATER,
        FILLING_MILK,
        FILLING_BEANS,
        FILLING_CUPS,
        EXITING;
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

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        while (coffeeMachine.next(scanner.nextLine())) {}
    }
}
