package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static int[] buy(int[] res) {
        Scanner scan = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scan.next();
        if (choice.equals("back"))
            return res;
        switch (choice) {
            case "1":
                if (res[0] > 249 && res[2] > 15 && res[3] > 0) {
                    System.out.println("I have enough resources, making you a coffee!");
                    res[0] -= 250;
                    res[2] -= 16;
                    res[3]--;
                    res[4] += 4;
                } else {
                    if (res[0] < 250)
                        System.out.println("Sorry, not enough water!");
                    if (res[2] < 16)
                        System.out.println("Sorry, not enough coffee beans!");
                    if (res[3] < 1)
                        System.out.println("Sorry, not enough disposable cups!");
                }
                break;
            case "2":
                if (res[0] > 349 && res[1] > 74 && res[2] > 19 && res[3] > 0) {
                    System.out.println("I have enough resources, making you a coffee!");
                    res[0] -= 350;
                    res[1] -= 75;
                    res[2] -= 20;
                    res[3]--;
                    res[4] += 7;
                } else {
                    if (res[0] < 350)
                        System.out.println("Sorry, not enough water!");
                    if (res[1] < 75)
                        System.out.println("Sorry, not enough milk!");
                    if (res[2] < 20)
                        System.out.println("Sorry, not enough coffee beans!");
                    if (res[3] < 1)
                        System.out.println("Sorry, not enough disposable cups!");
                }
                break;
            case "3":
                if (res[0] > 199 && res[1] > 99 && res[2] > 11 && res[3] > 0) {
                    System.out.println("I have enough resources, making you a coffee!");
                    res[0] -= 200;
                    res[1] -= 100;
                    res[2] -= 12;
                    res[3]--;
                    res[4] += 6;
                } else {
                    if (res[0] < 200)
                        System.out.println("Sorry, not enough water!");
                    if (res[1] < 100)
                        System.out.println("Sorry, not enough milk!");
                    if (res[2] < 12)
                        System.out.println("Sorry, not enough coffee beans!");
                    if (res[3] < 1)
                        System.out.println("Sorry, not enough disposable cups!");
                }
        }
        return res;
    }

    static int[] fill (int[] res) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        res[0] += scan.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        res[1] += scan.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        res[2] += scan.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        res[3] += scan.nextInt();
        return res;
    }

    static int[] take (int[] res) {
        System.out.printf("I gave you $%d\n", res[4]);
        res[4] = 0;
        return res;
    }

    static void remaining (int[] res) {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", res[0]);
        System.out.printf("%d ml of milk\n", res[1]);
        System.out.printf("%d g of coffee beans\n", res[2]);
        System.out.printf("%d disposable cups\n", res[3]);
        System.out.printf("$%d of money\n", res[4]);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] res = {400, 540, 120, 9, 550};
        while (true) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            String choice = scan.next();

            if (choice.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (choice.equals("buy"))
                res = buy(res);
            else if (choice.equals("fill"))
                res = fill(res);
            else if (choice.equals("take"))
                res = take(res);
            else if (choice.equals("remaining"))
                remaining(res);
            else
                System.out.println("Incorrect action!");

        }
    }
}

