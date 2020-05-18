package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    private State state;

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        this.state = State.CHOOSING_ACTION;
        promptAction();
    }

    public boolean next(String action) {
        switch (state) {
            case CHOOSING_ACTION:
                processAction(action);
                break;
            case CHOOSING_COFEE:
                sellCoffee(action);
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

    public boolean isProcessing() {
        return state != State.EXITING;
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

    private void sellCoffee(String action) {
        if (action.equals("back")) {
            System.out.println();
            return;
        }

        Cofee cofee;
        try {
            cofee = Cofee.of(action);
            if (!checkSupplies(cofee)) {
                return;
            }

            System.out.println("I have enough resources, making you a coffee!");
            water -= cofee.waterPerCup;
            milk -= cofee.milkPerCup;
            beans -= cofee.beansPerCup;
            cups--;
            money += cofee.moneyPerCup;

        } catch (Exception e) {
            System.out.println("Invalid input");
        }

        System.out.println();
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
                    String.format("Sorry, not enough %s!",
                            supplies.substring(0, supplies.length() - 2)));
            System.out.println();
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
        ESPRESSO("1", 250, 0, 16, 4),
        LATTE("2", 350, 75, 20, 7),
        CAPPUCHINO("3", 200, 100, 12, 6);

        final String id;
        final int waterPerCup;
        final int milkPerCup;
        final int beansPerCup;
        final int moneyPerCup;

        Cofee(String id, int waterPerCup, int milkPerCup, int beansPerCup, int moneyPerCup) {
            this.id = id;
            this.waterPerCup = waterPerCup;
            this.milkPerCup = milkPerCup;
            this.beansPerCup = beansPerCup;
            this.moneyPerCup = moneyPerCup;
        }

        public static Cofee of(String id) {
            for (Cofee cofee : values()) {
                if (cofee.id.equals(id)) {
                    return cofee;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        while (coffeeMachine.isProcessing()) {
            coffeeMachine.next(scanner.nextLine());
        }
    }
}
